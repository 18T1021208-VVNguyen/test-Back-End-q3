package org.example.question_3.service.impl;

import org.example.question_3.service.FilesStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FilesStorageServiceImpl  implements FilesStorageService {
    private final Path root = Paths.get("src/main/resources/static/uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    @Override
    public String saveFile(MultipartFile file) {
        try {
            Path filePath = this.root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(),filePath , StandardCopyOption.REPLACE_EXISTING );
            return subFileUrl(filePath);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }

    }

    private String subFileUrl(Path filePath){
        int startIndex = filePath.toString().indexOf("\\uploads");
        return filePath.toString().substring(startIndex);
    }
}
