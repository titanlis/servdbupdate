/**
 * @brief Серверный сервис обновления таблиц.
 * @file ServdbupdateApplication.java
 * @authors Andrei Timofeev
 * @date 15.08.2022
 * @details Сервис для обновления таблиц из списка TableVersion на бортовом компьютере.
 * @todo {Передача с бк на сервер. Архивация. Облачный конфиг.}
 */
package ru.itm.servdbupdate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServdbupdateApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServdbupdateApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ServdbupdateApplication.class, args);
        logger.info("Application is start.");
    }

}
