package ru.itm.servdbupdate.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itm.servdbupdate.controllers.InitController;
import ru.itm.servdbupdate.utils.NetworkUtils;

import static ru.itm.servdbupdate.config.OSType.LINUX;

@Component
public class SystemConfig {
    private static Logger logger = LoggerFactory.getLogger(SystemConfig.class);

    @Value("${OS}")
    private String os;

    private static OSType osType = OSType.UNKNOWN;

    @Value("${itm.logger.path}")
    private static String loggerPath;

    public SystemConfig() {
        if(os==null || os.equals("none")){
            os = NetworkUtils.getOSName().toLowerCase(); //получаем имя операционки
        }
        setOsType(os);
        logger.info("OS = \'" + os + '\'');

        if(loggerPath==null || loggerPath.equals("none")){
            switch(getOsType()){
                case LINUX -> { setLoggerPath("./log/"); }
                case WINDOWS -> { setLoggerPath("\\log\\"); }
                default ->  { setLoggerPath(""); }
            }
            loggerPath = getLoggerPath();
        }
        logger.info("Itm log path = \'" + getLoggerPath() + '\'');

    }

    public static String getLoggerPath() {
        return loggerPath;
    }
    public static void setLoggerPath(String loggerPath) {
        SystemConfig.loggerPath = loggerPath;
    }

    public static OSType getOsType() {
        return osType;
    }
    public static void setOsType(OSType osType) {
        SystemConfig.osType = osType;
    }

    public static void setOsType(String osTypeStr) {
        switch (osTypeStr.toLowerCase()){
            case "linux" -> { osType = LINUX; }
            case "windows" -> { osType = OSType.WINDOWS; }
            case "windows 10" -> { osType = OSType.WINDOWS; }
            default -> { osType = OSType.UNKNOWN; }
        }
    }

    public static String getOsType(OSType osTypeStr) {
        switch (osTypeStr){
            case LINUX -> { return "linux"; }
            case WINDOWS -> { return "windows 10"; }
            default -> { return "none"; }
        }
    }



}
