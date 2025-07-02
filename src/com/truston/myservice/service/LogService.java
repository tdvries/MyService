package com.truston.myservice.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.*;

public class LogService {


    private static final Logger logger = Logger.getLogger(LogService.class.getName());
    private static boolean running = true;

    public static void main(String[] args) {
        setupLogger();
        logger.info("Service started.");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Service shutting down.");
            running = false;
        }));

        while (running) {
            try {
                logger.info("Service is running...");
                Thread.sleep(10_000); // Log every 10 seconds
            } catch (InterruptedException e) {
                logger.warning("Service interrupted: " + e.getMessage());
            }
        }

        logger.info("Service stopped.");
    }

    private static void setupLogger() {
        try {
            Path logDir = Paths.get("c:/logs/truston/myservice");
            if (!Files.exists(logDir)) {
                Files.createDirectory(logDir);
            }

            FileHandler fh = new FileHandler("c:/logs/truston/myservice/service.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.INFO);
            logger.setUseParentHandlers(false); // Don't log to console

        } catch (IOException e) {
            System.err.println("Failed to setup logger: " + e.getMessage());
        }
    }
}
