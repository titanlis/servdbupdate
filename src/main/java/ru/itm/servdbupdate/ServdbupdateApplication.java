/**
 * @brief Серверный сервис обновления таблиц.
 * @file ServdbupdateApplication.java
 * @authors Andrei Timofeev
 * @date 15.08.2022
 * @details Сервис для обновления таблиц из списка TableVersion на бортовом компьютере.
 * @todo {Передача с бк на сервер. Архивация. Облачный конфиг.}
 */
package ru.itm.servdbupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServdbupdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServdbupdateApplication.class, args);
    }

}
