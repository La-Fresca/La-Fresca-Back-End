package org.lafresca.lafrescabackend.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SystemLogService {
    private final String filePath = "./logs/log-detail.txt"; // Specify the file path here
    private final String LOG_FILE_PATH = "logs/application.log";

    // Method to write to the file without overwriting content
    public void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine(); // Add a new line after writing
            System.out.println("Log Content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read content from the file
    public List<String> readFromFile() {
        StringBuilder content = new StringBuilder();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(filePath));
//            System.out.println("Reading Lines");
            for (String line : lines) {
//                System.out.println(line);
                content.append(line).append(System.lineSeparator());
            }
//            System.out.println("Lines read");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Retrieve all human-readable system logs";
        writeToFile(logmessage);
        log.info(logmessage);

        return lines;
    }

    public List<String> readAdvancedSystemLogs(){
        System.out.println("Reading Log files");
        StringBuilder logs = new StringBuilder();
        List<String> advancedLogs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.append(line).append("\n");
                advancedLogs.add(line);
            }
        } catch (IOException e) {
            log.error("Error reading log file: {}", e.getMessage());
            throw new IllegalStateException("Error reading log file: " + e.getMessage());
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime now = LocalDateTime.now();

        String logmessage = now + " " + username + " " + "Retrieve all advanced system logs";
        writeToFile(logmessage);
        log.info(logmessage);

        return advancedLogs;
    }
}
