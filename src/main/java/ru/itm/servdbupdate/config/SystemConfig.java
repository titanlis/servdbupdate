package ru.itm.servdbupdate.config;


import static ru.itm.servdbupdate.config.OSType.LINUX;

public class SystemConfig {
    private static OSType osType = OSType.UNKNOWN;
    private static String loggerPath = "none";

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
