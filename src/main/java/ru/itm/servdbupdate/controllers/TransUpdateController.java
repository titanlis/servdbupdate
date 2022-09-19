package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.entity.tables.equipment.Equipment;
import ru.itm.servdbupdate.entity.tables.trans.Trans;
import ru.itm.servdbupdate.entity.tables.trans.TransFuel;
import ru.itm.servdbupdate.kryo.CompressObject;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.loggers.ItmServerLogger;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.repository.trans.TransFuelRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Контроллер для обновления таблиц trans_
 */
@RestController
@RequestMapping("/api/v1/trans")
public class TransUpdateController {
    private static Logger logger = LoggerFactory.getLogger(TransUpdateController.class);


    public ItmServerLogger itmServerLogger;

    @Autowired
    public void setItmServerLogger(ItmServerLogger itmServerLogger) {
        this.itmServerLogger = itmServerLogger;
    }

    public TransUpdateController(){

    };

    /**
     * Получаем массив со сжатой gzip мэпой MultiValueMap<String, Integer> с именами всех таблиц для проверки обновлений
     * и их версиями.
     * @param arrayTablesToUpdate
     * @return 1 обновление записано в базу, 0 если нет
     */
    @PostMapping(value = "/update")
    public int updateTransTime(@RequestBody byte[] arrayTablesToUpdate){
        //System.out.println("size="+arrayTablesToUpdate.length);
        Integer returnInfo = 0;
        try {
            Map<String, List<byte[]>> tablesMap = (Map<String, List<byte[]>>) CompressObject.readCompressObject(arrayTablesToUpdate);
            if(tablesMap.isEmpty()){
                logger.warn("Input data is empty.");
                return 0;
            }

            AtomicInteger num= new AtomicInteger(0);  //имя оборудования ищем только для 0

            /**Перебираем все таблицы*/
            tablesMap.forEach((names, listEntity) ->{
                /**Получаем репозиторий для записи в постгрес по имени таблицы*/
                CommonRepository commonRepository = RepositoryFactory.getRepo(names);
                /**Приходить должны только непустые таблицы*/
                if(listEntity.size()>0){
                    if(num.get() == 0){
                        num.getAndIncrement();
                        /**Десириализовали нулевую строку, для поиска имени оборудования*/
                        Trans transForName = (Trans) KryoSerializer.deserialize(listEntity.get(0));
                        /**Получили из нее id техники, нашли ее в постгресе, вывели имя в лог*/
                        String equipmentName = ((Equipment)RepositoryFactory.getRepo("equipment")
                                .findById(transForName.getEquipIdTrans()).get()).getEquip();
                        logger.info("Equipment : " + equipmentName );
                        itmServerLogger.writeIn("{\"equipment\":\"" + equipmentName + "\"}", 0);
                    }

                    List<AbstractEntity> transEntityList = new ArrayList<>();


                    /**Теперь разбираем каждую строку текущей таблицы*/
                    listEntity.stream().forEach(b-> {
                        AbstractEntity transRow = (AbstractEntity) KryoSerializer.deserialize(b);
                        if(isNeedWrite(names, commonRepository, (Trans) transRow)){
                            transEntityList.add(transRow);  //разобраться с id
                            logger.info("--> (write) -->" + transRow.toStringShow());
                            itmServerLogger.writeIn(transRow.toStringShow(), 1);
                        }
                        else{
                            logger.info("--> (no write) -->" + transRow.toStringShow());
                            itmServerLogger.writeIn(transRow.toStringShow(), 0);
                        }
                    });
                    if(!transEntityList.isEmpty()){
                        saveTransList(names, commonRepository, transEntityList);
                    }
                }
                else{
                    logger.warn(String.format("Input table \'%s\' is empty", names));
                }
            });
            itmServerLogger.writeOut("1", 0);
            return 1;
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }

        itmServerLogger.writeOut("0", 0);
        return 0;
    }

    /**
     * Записывает в таблицу names
     * @param names таблица
     * @param commonRepository репозиторий соответствующий этой таблице
     * @param transEntityList строки, котрые нужно записать
     */
    private void saveTransList(String names, CommonRepository commonRepository, List<AbstractEntity> transEntityList) {
        List<AbstractEntity> list = new ArrayList<>();
        switch (names){
            case "trans_fuel"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransFuel((TransFuel) t))));}
        }
        if(!list.isEmpty()) commonRepository.saveAll(list);
    }

    /**
     * Нужно ли добавлять строку transRow в таблицу names ?
     * @param names
     * @param commonRepository если в этом репозитории такая запись есть, то не надо писать иначе пишем
     * @param transRow
     * @return
     */
    private boolean isNeedWrite(String names, CommonRepository commonRepository, Trans transRow) {
        switch (names){
            case "trans_fuel"->{
                return ((TransFuelRepository)commonRepository).countAllByEquipIdAndTimeRead(transRow.getEquipIdTrans(), transRow.getTime())==0L;
            }
            default -> { return false; }
        }
    }


//    /**
//     * Тестовый ендпоинт
//     * @param tableName
//     * @return объект таблицы tableName с id==1
//     */
//    @GetMapping("/{tableName}")
//    public AbstractEntity getTable(@PathVariable String tableName) {
//        CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
//        if(commonRepository!=null){
//            Optional<AbstractEntity> opt = commonRepository.findById(1L);
//            if(!opt.isEmpty()){
//                logger.info("Find: " + opt.get().toStringShow());
//                return opt.get();
//            }
//            else{
//                return null;
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * Тестовый ендпоинт
//     * @param tableName
//     * @return все строки
//     */
//    @GetMapping("/all/{tableName}")
//    public List<AbstractEntity> getTables(@PathVariable String tableName) {
//        CommonRepository commonRepository = RepositoryFactory.getRepo(tableName);
//        List<AbstractEntity> abstractEntityList =  new ArrayList<>();
//
//        if(commonRepository!=null){
//            for (AbstractEntity abstractEntity : (Iterable<AbstractEntity>)commonRepository.findAll()) {
//                abstractEntityList.add(abstractEntity);
//            }
//        }
//        return abstractEntityList;
//    }
}