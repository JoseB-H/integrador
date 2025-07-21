package com.paguina.web.servicios;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareasProgramadas {

    // Ejecuta cada 30 minutos
    @Scheduled(cron = "0 */30 * * * *")
    public void tareaCada30Minutos() {
        System.out.println("⏰ Ejecutando tarea cada 30 minutos...");
        // Aquí podrías llamar a un método de servicio, por ejemplo:
    }

    // Ejecuta todos los días a las 2 AM
    @Scheduled(cron = "0 0 2 * * *")
    public void backupDiario() {
        System.out.println("📦 Ejecutando backup diario a las 2 AM...");
    }
}
