package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.kryo.CompressObject;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.RepositoryFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Контроллер для обновления таблиц trans_
 */
@RestController
@RequestMapping("/api/v1/trans")
public class TransUpdateController {
    private static Logger logger = LoggerFactory.getLogger(TransUpdateController.class);


    /**
     * Получаем массив со сжатой gzip мэпой MultiValueMap<String, Integer> с именами всех таблиц для проверки обновлений
     * и их версиями.
     * @param arrayTablesToUpdate
     * @return 1 обновление записано в базу, 0 если нет
     */
    @PostMapping(value = "/update")
    public int updateTransTime(@RequestBody byte[] arrayTablesToUpdate){
        System.out.println("size="+arrayTablesToUpdate.length);

        try {
            Map<String, List<byte[]>> tablesMap = (Map<String, List<byte[]>>) CompressObject.readCompressObject(arrayTablesToUpdate);
            System.out.println("tablesMap -> " + !tablesMap.isEmpty());
            tablesMap.forEach((names, listEntity) ->{
                System.out.println("Table: " + names);
                listEntity.stream().forEach(b->{
                    AbstractEntity abstractEntity = (AbstractEntity) KryoSerializer.deserialize(b);
                    System.out.println("\t"+abstractEntity.toStringShow());
                });
            });
            return 1;
        } catch (IOException e) {
            //System.err.println(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            //System.err.println(e.getMessage());
            logger.error(e.getMessage());
        }
        return 0;
    }



    /**
     * Тестовый ендпоинт
     * @param tableName
     * @return объект таблицы tableName с id==1
     */
    @GetMapping("/{tableName}")
    public AbstractEntity getTable(@PathVariable String tableName) {
        CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
        if(commonRepository!=null){
            Optional<AbstractEntity> opt = commonRepository.findById(1L);
            if(!opt.isEmpty()){
                logger.info("Find: " + opt.get().toStringShow());
                return opt.get();
            }
            else{
                return null;
            }
        }
        return null;
    }


    /**
     * Тестовый ендпоинт
     * @param tableName
     * @return все строки
     */
    @GetMapping("/all/{tableName}")
    public List<AbstractEntity> getTables(@PathVariable String tableName) {
        CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
        List<AbstractEntity> abstractEntityList =  new ArrayList<>();

        if(commonRepository!=null){
            for (AbstractEntity abstractEntity : (Iterable<AbstractEntity>)commonRepository.findAll()) {
                abstractEntityList.add(abstractEntity);
            }
        }
        return abstractEntityList;
    }
}