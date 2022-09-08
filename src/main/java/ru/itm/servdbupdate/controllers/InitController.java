package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import ru.itm.servdbupdate.config.OSType;
import ru.itm.servdbupdate.config.SystemConfig;
import ru.itm.servdbupdate.entity.MessageInterface;
import ru.itm.servdbupdate.kryo.KryoSerializer;
import ru.itm.servdbupdate.loggers.ItmServerLogger;
import ru.itm.servdbupdate.repository.RepositoryFactory;
import ru.itm.servdbupdate.repository.equipment.EquipmentRepository;
import ru.itm.servdbupdate.serivce.TablesService;
import ru.itm.servdbupdate.utils.NetworkUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Контроллер для работы с Init сервисом БК
 */
@RestController
@RequestMapping("/api/v1/init")
public class InitController {
    private static Logger logger = LoggerFactory.getLogger(InitController.class);
    private TablesService tablesService;    //контакты с бд postgresql
    private static ItmServerLogger itmServerLogger = null;

    @Value("${OS}")
    private String os;

    @Value("${itm.logger.path}")
    private String itmLoggerPath;

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
    public MessageInterface getIp(@PathVariable String sn) {
        AtomicReference<String> ip = new AtomicReference<>();
        AtomicReference<String> mac = new AtomicReference<>();
        logger.info("Init request. Get ip for \'" + sn + '\'');
        itmServerLogger.writeIn(sn+"/getip",0);
        /*Подключаемся к репозиторию equipment.equipment*/
        EquipmentRepository equipmentRepository = RepositoryFactory.getEquipmentRepository();
        if(equipmentRepository!=null){
            /*Находим серийник и сохраняем ip*/
            equipmentRepository.findAll().forEach(entityObject -> {
                String s = entityObject.getMt_sn();
                if( s!=null && s.equals(sn)){
                    ip.set(entityObject.getMt_ip());
                    mac.set(entityObject.getMt_mac());
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

        MessageInterface messageInterface = new MessageInterface(ip.get(), mac.get());
        itmServerLogger.writeOut(messageInterface.toString(),0);

        return messageInterface;
    }


    /**
     * Автозапуск после создания контекста
     */
    @EventListener(ApplicationReadyEvent.class)
    private void startIni(){
        if(os==null || os.equals("none")){
            os = NetworkUtils.getOSName().toLowerCase(); //получаем имя операционки
        }
        SystemConfig.setOsType(os);
        logger.info("OS = \'" + os + '\'');

        if(itmLoggerPath==null || itmLoggerPath.equals("none")){
            switch(SystemConfig.getOsType()){
                case LINUX -> { SystemConfig.setLoggerPath("./log/"); }
                case WINDOWS -> { SystemConfig.setLoggerPath("\\log\\"); }
                default ->  { SystemConfig.setLoggerPath(""); }
            }
            itmLoggerPath = SystemConfig.getLoggerPath();
        }
        else {
            SystemConfig.setLoggerPath(itmLoggerPath);
        }
        logger.info("Itm log path = \'" + SystemConfig.getLoggerPath() + '\'');

        itmServerLogger = new ItmServerLogger();
    }

}
