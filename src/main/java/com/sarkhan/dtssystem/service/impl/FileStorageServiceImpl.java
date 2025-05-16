package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueName = timestamp + "_" + UUID.randomUUID() + fileExtension;

        Path destination = this.storageDirectory.resolve(uniqueName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return uniqueName;
    }
}
