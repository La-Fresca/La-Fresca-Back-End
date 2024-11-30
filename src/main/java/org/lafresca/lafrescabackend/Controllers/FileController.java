package org.lafresca.lafrescabackend.Controllers;

import org.lafresca.lafrescabackend.Services.FileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // API to write to the file
    @PostMapping("/write")
    public String writeToFile(@RequestParam String content) {
        fileService.writeToFile(content);
        return "Content written to file.";
    }

    // API to read the file content
    @GetMapping("/read")
    public String readFromFile() {
        return fileService.readFromFile();
    }
}
