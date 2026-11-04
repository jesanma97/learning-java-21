package com.tienda.config;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Endpoint de demostración: enseña qué configuración está activa según el perfil.
 */
@RestController
public class InfoController {

    record Info(String tienda, String moneda, BigDecimal envioGratisDesde, List<String> perfilesActivos) {}

    private final TiendaProperties tienda;
    private final Environment environment;

    public InfoController(TiendaProperties tienda, Environment environment) {
        this.tienda = tienda;
        this.environment = environment;
    }

    @GetMapping("/info")
    public Info info() {
        return new Info(tienda.nombre(), tienda.moneda(), tienda.envioGratisDesde(),
                List.of(environment.getActiveProfiles()));
    }
}
