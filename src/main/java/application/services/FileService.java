package application.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(MultipartFile file);
    void setCreatedFileName(String createdFileName);
    String getCreatedFileName();
}
