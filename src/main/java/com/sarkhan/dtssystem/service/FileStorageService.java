package com.sarkhan.dtssystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStorageService {
    String saveFile(MultipartFile file) throws IOException;
    List<Boolean> deleteFiles(List<String> fileNames) ;
    public boolean deleteFile(String fileName);
    List<String> saveFiles(List<MultipartFile> files) throws IOException;
}
