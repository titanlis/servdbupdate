package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.kryo.CompressObject;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.loggers.ItmServerLogger;
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
    private static Logger logger = LoggerFactory.getLogger(DBUpdateController.class);
    private static ItmServerLogger itmServerLogger;

    private TablesService tablesService;    //контакты с бд postgresql

    @Autowired
    public void setTablesService(TablesService tablesService) {
        this.tablesService = tablesService;
    }


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
            itmServerLogger.writeIn(String.format("http://server_host:port/api/v1/%s/gettabversions\";\"%s",ip, bkMapVersions.toString()),0);
            MultiValueMap<String, Integer> tableOutboxMessage = findTablesYoungerThanThis(bkMapVersions, ip);
            itmServerLogger.writeOut(tableOutboxMessage.toString(),0);
            return CompressObject.writeCompressObject(tableOutboxMessage);
        } catch (IOException e) {
            //System.err.println(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            //System.err.println(e.getMessage());
            logger.error(e.getMessage());
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
        itmServerLogger.writeIn(String.format("http://server_host:port/api/v1/%s/gettabversions",ip), 0);
        List<TableVersion> list = tablesService.findAll();
        itmServerLogger.writeOut(list.toString(),0);
        return list;
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

        itmServerLogger.writeIn(String.format("http://server_host:port/api/v1/%s/update/%s",ip, tableName), 0);
        List<byte[]> listByteArray = new ArrayList<>();
        List<String> listEntity = new ArrayList<>();
        tableName = tableName.toLowerCase();

        try{
            logger.info(tableName + " searching for a repository");
            CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
            if(commonRepository!=null){
                commonRepository.findAll().forEach(entityObject -> {
                    listByteArray.add(KryoSerializer.serialize(entityObject));
                    listEntity.add(entityObject.toString());
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

            itmServerLogger.writeOut(listEntity.toString(), 0);
            return CompressObject.writeCompressObject(listByteArray);
        } catch (IOException e) {
            logger.error("It fails to compress the list of byte arrays for table \"" + tableName + "\".");
            //System.err.println("It fails to compress the list of byte arrays for table \"" + tableName + "\".");
        }
        return null;
    }

    /**
     * Автозапуск после создания контекста
     */
    @EventListener(ApplicationReadyEvent.class)
    private void startIni(){
        itmServerLogger = new ItmServerLogger();
    }

}
