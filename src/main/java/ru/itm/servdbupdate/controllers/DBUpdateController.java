package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.kryo.CompressObject;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.serivce.TablesService;

import java.io.IOException;
import java.util.*;

/**
 * Основной контроллер для обновления базы БК
 */
@RestController
@RequestMapping("/api/v1/{ip}")
public class DBUpdateController {

    private TablesService tablesService;    //контакты с бд postgresql

    @Autowired
    public void setTablesService(TablesService tablesService) {
        this.tablesService = tablesService;
    }


    private static Logger logger = LoggerFactory.getLogger(DBUpdateController.class);

    /**
     * Получаем массив со сжатой gzip мэпой MultiValueMap<String, Integer> с именами всех таблиц для проверки обновлений
     * и их версиями.
     * @param arrayAllTable
     * @param ip
     * @return Массив со сжатой gzip мэпой MultiValueMap<String, Integer> с именами новых таблиц и их версиями
     */
    @PostMapping(value = "/gettabversions")
    public byte[] findNewTables(
            @RequestBody byte[] arrayAllTable,
            @PathVariable String ip){
        try {
            MultiValueMap<String, Integer> bkMapVersions = (MultiValueMap<String, Integer>)CompressObject.readCompressObject(arrayAllTable);
            MultiValueMap<String, Integer> tableOutboxMessage = findTablesYoungerThanThis(bkMapVersions, ip);
            return CompressObject.writeCompressObject(tableOutboxMessage);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    /**
     * Метод принимает версии таблиц с бк для обновления.
     * Проверяет на новые версии и возвращает список таблиц, которые нужно обновить.
     * @param bkMapVersions MultiValueMap<String, Integer> имя и версия на bk
     * @param ip    адрес с которого прилетел запрос
     * @return map MultiValueMap<String, Integer> имя и версия таблиц для обновления
     */
    public MultiValueMap<String, Integer> findTablesYoungerThanThis(
            MultiValueMap<String, Integer> bkMapVersions,
            String ip){

        logger.info("A request came in from the " + ip);

        /**Создается list с TableVersion на базе полученных пар "имя таблицы"-версия */
        List<TableVersion> bkTablesList = new LinkedList<>();

        Iterator<Map.Entry<String, Integer>> iterator = bkMapVersions.toSingleValueMap().entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Integer> pair = iterator.next();
            bkTablesList.add(new TableVersion(pair.getKey(), Integer.parseInt(String.valueOf(pair.getValue()))));
        }

        /** MultiValueMap<String, Integer> для пар которые нужно обновить*/
        MultiValueMap<String, Integer> map = new LinkedMultiValueMap();
        /**метод ищет таблицы для обновления в базе*/
        List<TableVersion> newTablesList = tablesService.findTablesYoungerThanThis(bkTablesList);
        newTablesList.stream().forEach(tab->{
            map.add(tab.getTableName(), tab.getTableVersion());     //создаем пары для отправки на bk
        });

        return map;
    }

    /**
     * Получить список с версиями таблиц
     */
    @GetMapping("/gettabversions")
    public List<TableVersion> listTables(@PathVariable String ip){
        return tablesService.findAll();
    }

    /**
     * Поиск таблицы в базе по имени.
     * @param ip входящий ip
     * @param tableName имя таблицы
     * @return byte[] список данных из таблицы в kryo (байты) сжатый gzip в массив байт
     */
    @GetMapping("/update/{tableName}")
    public byte[] findTablesYoungerThanThis(
            @PathVariable String ip,
            @PathVariable String tableName) {

        List<byte[]> listByteArray = new ArrayList<>();
        tableName = tableName.toLowerCase();

        try{
            logger.info(tableName + " searching for a repository");
            CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
            if(commonRepository!=null){
                commonRepository.findAll().forEach(entityObject -> {
                    listByteArray.add(KryoSerializer.serialize(entityObject));
                });
                logger.info(tableName + " is serialized and sent to the on-board computer.");
            }
            else{
                logger.info(tableName + " - repository was not found.");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        /**Сжатие сериализованной таблицы с помощью gzip*/
        try {
            return CompressObject.writeCompressObject(listByteArray);
        } catch (IOException e) {
            System.err.println("It fails to compress the list of byte arrays for table \"" + tableName + "\".");
        }
        return null;
    }

}
