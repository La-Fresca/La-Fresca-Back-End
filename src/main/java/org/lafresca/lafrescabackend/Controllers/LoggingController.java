package org.lafresca.lafrescabackend.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);
    private static final String LOG_FILE_PATH = "logs/application.log"; // Adjust this path if necessary

    @GetMapping("/log")
    public String logMessage() {
        logger.info("This is an info log message.");
        logger.warn("This is a warning log message.");
        logger.error("This is an error log message.");
        System.out.println("Log messages written!");
        return "Log messages written!";
    }

    @GetMapping("/logs")
    public String getLogs() {
        System.out.println("Reading Log files");
        StringBuilder logs = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.error("Error reading log file: {}", e.getMessage());
            return "Error reading log file: " + e.getMessage();
        }
        return logs.toString();
    }
}
