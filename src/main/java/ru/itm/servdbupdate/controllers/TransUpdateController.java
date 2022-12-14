package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.entity.tables.equipment.Equipment;
import ru.itm.servdbupdate.entity.tables.trans.*;
import ru.itm.servdbupdate.kryo.CompressObject;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.loggers.ItmServerLogger;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.repository.trans.*;

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
    public ItmServerLogger itmServerLogger;     //внедряем логгер для трафика
    @Autowired
    public void setItmServerLogger(ItmServerLogger itmServerLogger) {
        this.itmServerLogger = itmServerLogger;
    }

    public TransUpdateController(){};

    /**
     * Получаем массив со сжатой gzip мэпой MultiValueMap<String, Integer> с именами всех таблиц для проверки обновлений
     * и их версиями.
     * @param arrayTablesToUpdate
     * @return 1 обновление записано в базу, 0 если нет
     */
    @PostMapping(value = "/update")
    public int updateTransTime(@RequestBody byte[] arrayTablesToUpdate){
        //System.out.println("size="+arrayTablesToUpdate.length);
//        Integer returnInfo = 0;
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

                        /**Если в записи таблицы есть в принципе id техники, то выводим его в лог*/
                        Long equipIdTrans = transForName.getEquipIdTrans();
                        if(equipIdTrans!=null){
                            /**Получили из нее id техники, нашли ее в постгресе, вывели имя в лог*/
                            String equipmentName = ((Equipment)RepositoryFactory.getRepo("equipment")
                                    .findById(equipIdTrans).get()).getEquip();
                            logger.info("Equipment : " + equipmentName );
                            itmServerLogger.writeIn("{\"equipment\":\"" + equipmentName + "\"}", 0);
                        }
                        else{   //если id техники нет, то в лог напишем об этом
                            logger.info("Equipment : unknown");
                            itmServerLogger.writeIn("Equipment : unknown", 0);
                        }
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
                        logger.info("save " + names);
                        saveTransList(names, commonRepository, transEntityList);
                    }
                    else{
                        logger.info("not save " + names);
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
            case "trans_coord"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransCoord((TransCoord) t))));}
            case "trans_sensor"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransSensor((TransSensor) t))));}
            case "trans_keys_cycle"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransKeysCycle((TransKeysCycle) t))));}
            case "trans_keys_drilling"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransKeysDrilling((TransKeysDrilling) t))));}
            case "trans_network"->{ transEntityList.stream().forEach(t->list.add((AbstractEntity) (new TransNetwork((TransNetwork) t))));}
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
            case "trans_fuel"->{return ((TransFuelRepository)commonRepository).countAllByEquipIdAndTimeRead(transRow.getEquipIdTrans(), transRow.getTime())==0L;}
            case "trans_coord"->{return ((TransCoordRepository)commonRepository).countAllByEquipIdAndEquipTime(transRow.getEquipIdTrans(), transRow.getTime())==0L;}
            case "trans_sensor"->{return ((TransSensorRepository)commonRepository).countAllByEquipIdAndTimeRead(transRow.getEquipIdTrans(), transRow.getTime())==0L;}
            case "trans_keys_cycle"->{return ((TransKeysCycleRepository)commonRepository).countByIdTransCycleAndIdTransSensorAndSensorDataTypeId(transRow.getFirstID(), transRow.getSecondID(), transRow.getThirdID())==0L;}
            case "trans_keys_drilling"->{return ((TransKeysDrillingRepository)commonRepository).countByIdHoleAndIdTransDrillingAndIdTransSensor(transRow.getFirstID(), transRow.getSecondID(), transRow.getThirdID())==0L;}
            case "trans_network"->{return ((TransNetworkRepository)commonRepository).countAllByEquipIdAndEquipTime(transRow.getEquipIdTrans(), transRow.getTime())==0L;}
            default -> { return false; }
        }
    }

}