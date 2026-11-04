package com.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * El reloj es una dependencia más: inyectarlo permite fijar la hora en los tests.
 * Ejemplo de @Bean: Clock es una clase del JDK y no se puede anotar con @Component.
 */
@Configuration
public class RelojConfig {

    @Bean
    public Clock reloj() {
        return Clock.systemUTC();
    }
}
