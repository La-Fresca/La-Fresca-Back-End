package org.lafresca.lafrescabackend.Services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileService {

    private final String filePath = "./logs/log-detail.txt"; // Specify the file path here

    // Method to write to the file without overwriting content
    public void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine(); // Add a new line after writing
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read content from the file
    public String readFromFile() {
        StringBuilder content = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
//            System.out.println("Reading Lines");
            for (String line : lines) {
//                System.out.println(line);
                content.append(line).append(System.lineSeparator());
            }
//            System.out.println("Lines read");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
