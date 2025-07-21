package com.paguina.web.servicios;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class TareasProgramadasService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TareasProgramadasService.class);

    // Ejecuta cada día a las 2:00 am
    @Scheduled(cron = "0 0 2 * * *")
    public void tareaDeMantenimiento() {
        logger.info("🛠 Ejecutando tarea automática de mantenimiento");
        // Aquí puedes hacer limpieza en DB, logs, etc.
    }
}
