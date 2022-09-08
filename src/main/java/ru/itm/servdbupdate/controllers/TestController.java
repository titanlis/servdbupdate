package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.entity.TableVersion;
import ru.itm.servdbupdate.serivce.TablesService;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * Класс для "ручной" поверки некоторых методов сервиса
 * @todo Из релиза убрать.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    private String port;

    @Autowired
    private TablesService tablesService;

    /**
     * Вывод всех таблиц с версиями
     */
    @GetMapping("/getalltables")
    public List<TableVersion> stopPage(){
        logger.info("Check :"+ port + "/test/getalltables");
        List<TableVersion> tableVersions = tablesService.findAll();
        for(TableVersion t:tableVersions){
            //System.out.println(t.toStringShort());
            logger.info(t.toStringShort());
        }
        return tableVersions;
    }


    /**
     * Найти все таблицы версии которых новее данных
     * @return newTablesList список обновленных таблиц
     * @warning Ненужный метод, в релизе удалить.
     * Только для теста поиска новых тоблиц. Создает список таблиц с рэндомиными версиями
     * и ищет соответствие в базе.
     */
    @GetMapping("/findtables")
    public List<TableVersion> findTablesYoungerThanThisDemo(){
        logger.trace("/test/findtables");
        List<TableVersion> tableVersions = createExampleTab();
        for(TableVersion t:tableVersions){
            //System.out.println(t.toStringShort());
            logger.info(t.toStringShort());
        }
        //System.out.println("--------------------------------------------------------------------------");
        logger.info("--------------------------------------");
        List<TableVersion> tableVersionsNew = tablesService.findTablesYoungerThanThis(tableVersions);
        for(TableVersion t:tableVersionsNew){
          //  System.out.println(t.toStringShort());
            logger.info(t.toStringShort());

        }
        return tableVersionsNew;
    }


    /**
     * Найти все таблицы версии которых новее данных
     * @param incomingTableList список исходных таблиц (пришедших на проверку)
     * @return newTablesList список обновленных таблиц
     */
    @PostMapping(value = "/findtables")
//    List<TableVersion> findTablesYoungerThanThis(List<TableVersion> incomingTableList){
    public String findTablesYoungerThanThis(@RequestBody MultiValueMap<String, Integer> bkMapVersions){//String incomingTableList){
        String mapVersions = bkMapVersions.toString();

        //System.out.println(mapVersions);
        logger.info(mapVersions);
        //logger.info("Запрос прилетел : " + incomingTableList.getBody());
        //return tablesService.findTablesYoungerThanThis(incomingTableList);
        return "Сервер работает над запросом";
    }



    /**
     * На основе таблиц из базы создадим список с другими версиями.
     * Случайно добавим к версии 1 или отнимем 1. Или не изменим.
     * @return tableVersionsNew Список измененных таблиц.
     * @warning Ненужный метод, в релизе удалить.
     */
    private List<TableVersion> createExampleTab(){
        List<TableVersion> tableVersions = tablesService.findAll();
        List<TableVersion> tableVersionsNew = new LinkedList<TableVersion>();
        /*Случайно поменяем версии в таблицах или не изменим их*/
        for(int i=0; i<tableVersions.size(); i++){
            int rnd = (int)(Math.random()*3);
            TableVersion tv = new TableVersion(tableVersions.get(i));
            switch (rnd){
                case 0:
                    tv.setTableVersion(tableVersions.get(i).getTableVersion()-1);
                    break;
                case 1:
                    tv.setTableVersion(tableVersions.get(i).getTableVersion()+1);
                    break;
                default:
                    tv.setTableVersion(tableVersions.get(i).getTableVersion());
            }
            tableVersionsNew.add(tv);
        }

        /*Добавим несуществующую таблицу*/
        TableVersion t = new TableVersion();
        t.setId(999L);
        t.setTableName("random");
        t.setTableVersion(66);
        t.setUpdateDateTime(Calendar.getInstance());
        tableVersionsNew.add(t);

        return tableVersionsNew;
    }
}
