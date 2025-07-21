package com.paguina.web.PruebaDeSalud;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean funcionando = true;

        if (funcionando) {
            return Health.up().withDetail("estado", "Todo en orden ✅").build();
        } else {
            return Health.down().withDetail("estado", "Problema detectado ❌").build();
        }
    }
}
