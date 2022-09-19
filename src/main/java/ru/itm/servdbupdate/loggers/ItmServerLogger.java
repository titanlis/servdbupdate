package ru.itm.servdbupdate.loggers;

import org.springframework.stereotype.Component;
import ru.itm.servdbupdate.config.SystemConfig;
import java.io.*;
import java.time.LocalDateTime;

@Component
public class ItmServerLogger {
    private LocalDateTime today = null;
    private String fileName = "";
    private BufferedWriter logFile = null;

    private String trafficFileNamePref="itm_traffic_";

    public ItmServerLogger(){
        today = LocalDateTime.now();
        newFile();
        System.out.println("Constructor: " + fileName);

    }

    private String newFile(){
        return fileName=SystemConfig.getLoggerPath() + trafficFileNamePref + getDateString(today) + ".log";
    }

    private void open(){
        if(LocalDateTime.now().getDayOfMonth()!=today.getDayOfMonth()){
            newFile();
        }
        try {
            logFile = new BufferedWriter(new FileWriter(new File(fileName), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            if(logFile!=null) logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDateString(LocalDateTime localDateTime){
        return String.format("%04d%02d%02d",localDateTime.getYear(),localDateTime.getMonthValue(),localDateTime.getDayOfMonth());
    }

    private static String getTime(LocalDateTime localDateTime){
        return String.format("%02d:%02d:%02d",localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getSecond());
    }

    public synchronized void writeIn(String info, int postgresWrite){
        if(!isNow()){
            newFile();
        }
        try {
            open();
            logFile.write(getTime(LocalDateTime.now())+";IN;" + postgresWrite + ";\"" + info + "\"\n");
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeOut(String info, int postgresWrite){
        if(!isNow()){
            newFile();
        }
        try {
            open();
            logFile.write(getTime(LocalDateTime.now())+";OUT;" + postgresWrite + ";\"" + info + "\"\n");
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private boolean isNow() {
        return today.getDayOfMonth()==LocalDateTime.now().getDayOfYear();
    }

}
