/**
 * Корректная остановка сервиса. Реализуется с помощью Spring Actuator.
 */
package ru.itm.servdbupdate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Остановка сервиса.
 * @return Service is stopped : {"message":"Shutting down, bye..."}
 */
@RestController
public class ShutdownManager {

    private static Logger logger = LoggerFactory.getLogger(ShutdownManager.class);

    @Value("${management.server.port}")
    private String actuatorPort;

    @GetMapping("/exit")
    public String stopPage(){
        /*Endpoint для отключения */
        String url = "http://localhost:"+ actuatorPort +"/actuator/shutdown";

        /* Для срабатывания выключения через Spring Actuator мы должны сформировать POST HTTP
           запрос и отправить его на endpoint */
        // Http Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>("", headers);
        // Send request with POST method. Пост запрос на /actuator/shutdown
        String e = restTemplate.postForObject(url, requestBody, String.class);
        logger.info("Service is stopped : " + e);
        return "Service is stopped : " + e;
    }


}
