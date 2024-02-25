package org.example.question_3.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FilesStorageService {
    public void init();

    public String saveFile(MultipartFile file);
}
