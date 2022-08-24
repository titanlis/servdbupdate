package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.repository.equipment.EquipmentRepository;
import ru.itm.servdbupdate.serivce.TablesService;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Контроллер для работы с Init сервисом БК
 */
@RestController
@RequestMapping("/api/v1/init")
public class InitController {
    private static Logger logger = LoggerFactory.getLogger(InitController.class);
    private TablesService tablesService;    //контакты с бд postgresql

    @Autowired
    public void setTablesService(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    /**
     * Получить из базы по серийному номеру ip бк
     * @param sn серийник
     * @return ip ip адрес бк
     */
    @GetMapping(value = "/{sn}/getip")
    public String getIp(@PathVariable String sn) {
        AtomicReference<String> ip = new AtomicReference<>();
        logger.info("Init request. Get ip for \'" + sn + '\'');
        /*Подключаемся к репозиторию equipment.equipment*/
        EquipmentRepository equipmentRepository = RepositoryFactory.getEquipmentRepository();
        if(equipmentRepository!=null){
            /*Находим серийник и сохраняем ip*/
            equipmentRepository.findAll().forEach(entityObject -> {
                String s = entityObject.getMt_sn();
                if( s!=null && s.equals(sn)){
                    ip.set(entityObject.getMt_ip());
                }
            });
            if(ip.get()!=null){
                logger.info(ip.get() + " - ip for " + sn);
            }
            else{
                logger.info("null" + " - ip for " + sn);
            }
        }
        else{
            logger.info("ip for " + sn + " not found");
        }
        return ip.get();
    }

}
