package ru.itm.servdbupdate.controllers;

import com.esotericsoftware.kryo.kryo5.Kryo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.entity.tables.act.Act;
import ru.itm.servdbupdate.entity.tables.equipment.Equipment;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.repository.CommonRepository;
import ru.itm.servdbupdate.repository.act.ActRepository;
import ru.itm.servdbupdate.repository.equipment.EquipmentRepository;
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
    private ActRepository actRepository;
    private EquipmentRepository equipmentRepository;

    private List<DBModelContainer> dbModelContainerList = new ArrayList<>();

    @Autowired
    public void setTablesService(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @Autowired
    public void setActRepository(ActRepository actRepository) {
        this.actRepository = actRepository;
    }

    @Autowired
    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
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

        /**Если есть что обновлять, обновляем*/
//        if(!map.isEmpty()){
//            sendUpdatedTablesToBK(ip, newTablesList);
//        }

        return map;
    }

    /**
     * Отправка обновлений на bk
     * @param ip    куда отправляем
     * @param newTablesList список таблиц для обновления
     */
//    private void sendUpdatedTablesToBK(String ip, List<TableVersion> newTablesList) {
//        System.out.println("Отправка " + newTablesList.size() + " таблиц на бk " + ip + " для обновления");
//        newTablesList.stream().forEach(t -> {
//            //находим таблицу по имени
//            String s = t.getTableName().toLowerCase();
//            switch (s){
//                case "acts" ->  {
//                    List<byte[]> listByteArray = new ArrayList<>();
//                    Iterable<Act> actList= actRepository.findAll();
//                    for (Act a:actList){
//                        System.out.println(a.toStringShow());
//                        listByteArray.add(KryoSerializer.serialize(a));
//                    }
//
//                    dbModelContainerList.add(new DBModelContainer(listByteArray, "acts"));
//
//                    if(actList.spliterator().estimateSize()<=0){
//                        logger.info("Act не найдена");
//                    }
//
//                    System.out.println("-----------------------------------------------------");
//
//                    listByteArray.stream().forEach(a->{
//                        Act deserialize = (Act) KryoSerializer.deserialize(a);
//                        System.out.println(deserialize.toStringShow());
//                    });
//
//                }
//                case "equipment" ->  {
//                    Optional<Equipment> optionalEquipment = equipmentRepository.findById(t.getId());
//                    if(optionalEquipment.isEmpty()){
//                        logger.info("Equipment не найдена");
//                    }
//                    else {
//                        logger.info("Equipment присутствует");
//                    }
//                }
//            }
//            //обновляем
//            //серрилизуем
//            //в table_version записываем актуальный номер версии
//        });
//    }

    /**
     * Получить список с версиями таблиц
     */
    @GetMapping("/gettabversions")
    public List<TableVersion> listTables(){
        return tablesService.findAll();
    }

    @GetMapping("/update/{table}")
    public DBModelContainer findTablesYoungerThanThis(
            //@RequestBody DBModelContainer dbModelContainer,
            @PathVariable String ip,
            @PathVariable String table) {
        logger.info("findTablesYoungerThanThis starts -> " + table);

        List<byte[]> listByteArray = new ArrayList<>();

        table = table.toLowerCase();
        switch (table) {
            case "acts" -> {
                actRepository.findAll().forEach(act ->{
                    System.out.println(act.toStringShow());
                    listByteArray.add(KryoSerializer.serialize(act));
                });


            }
            default -> {
                return null;
            }

        }

        logger.info("act серилизован и отправлен на бк");
        return new DBModelContainer(listByteArray, table);
    }



}
