package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.serivce.TablesService;
import ru.itm.servdbupdate.udp.DBModelContainer;

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
     * Метод принимает версии таблиц с бк для обновления.
     * Проверяет на новые версии и возвращает список таблиц, которые нужно обновить.
     * @param bkMapVersions MultiValueMap<String, Integer> имя и версия на bk
     * @param ip    адрес с которого прилетел запрос
     * @return map MultiValueMap<String, Integer> имя и версия таблиц для обновления
     */
    @PostMapping(value = "/gettabversions")
    public MultiValueMap<String, Integer> findTablesYoungerThanThis(
            @RequestBody MultiValueMap<String, Integer> bkMapVersions,
            @PathVariable String ip){

        System.out.println("прилетел запрос с " + ip);

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
    public List<TableVersion> listTables(){
        return tablesService.findAll();
    }

    /**
     * Поиск таблицы в базе по имени.
     * @param ip входящий ip
     * @param tableName имя таблицы
     * @return DBModelContainer Сообщение {список данных из таблицы в kryo (байты), имя таблицы}
     */
    @GetMapping("/update/{tableName}")
    public DBModelContainer findTablesYoungerThanThis(
            @PathVariable String ip,
            @PathVariable String tableName) {

        List<byte[]> listByteArray = new ArrayList<>();
        tableName = tableName.toLowerCase();

        try{
            logger.info(tableName + " ищем репозиторий");
            CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
            if(commonRepository!=null){
                commonRepository.findAll().forEach(entityObject -> listByteArray.add(KryoSerializer.serialize(entityObject)));
                logger.info(tableName + " серилизован и отправлен на бк");
            }
            else{
                logger.info(tableName + " репозиторий не найден");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return new DBModelContainer(listByteArray, tableName);
    }

}
