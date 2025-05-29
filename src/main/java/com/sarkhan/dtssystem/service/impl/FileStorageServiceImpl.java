package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path storageDirectory;

    public FileStorageServiceImpl() throws IOException {
        this.storageDirectory = Paths.get("uploads");
        if (!Files.exists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Boş fayl  yüklənə bilməz.");
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IOException("Fayl uzantısı tapılmadı.");
        }

        fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueName = timestamp + "_" + UUID.randomUUID() + fileExtension;

        Path destination = this.storageDirectory.resolve(uniqueName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueName;
    }

    @Override
    public List<String> saveFiles(List<MultipartFile> files) throws IOException {
        List<String> savedFileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String savedFileName = saveFile(file);
            savedFileNames.add(savedFileName);
        }
        return savedFileNames;
    }

    @Override
    public boolean deleteFile(String fileName) {
        try {
            Path filePath = this.storageDirectory.resolve(fileName);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Boolean> deleteFiles(List<String> fileNames) {
        List<Boolean> deletionResults = new ArrayList<>();
        for (String fileName : fileNames) {
            deletionResults.add(deleteFile(fileName));
        }
        return deletionResults;
    }
}
