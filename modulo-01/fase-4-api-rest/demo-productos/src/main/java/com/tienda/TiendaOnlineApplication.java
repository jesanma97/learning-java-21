package com.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TiendaOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaOnlineApplication.class, args);
    }
}
