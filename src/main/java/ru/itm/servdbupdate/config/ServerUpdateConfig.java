package ru.itm.servdbupdate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itm.servdbupdate.loggers.ItmServerLogger;

@Configuration
public class ServerUpdateConfig {
    @Bean
    public ItmServerLogger makeItmLogger(){
        return new ItmServerLogger();
    }
}
