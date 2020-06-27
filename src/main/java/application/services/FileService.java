package application.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileService {
    @Value("${upload.path}")
    private String uploadPath;
    private String createdFileName;

    public String getUploadPath() {
        if(uploadPath == null){
            uploadPath = "/D:/uploads";
        }
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getCreatedFileName() {
        return createdFileName;
    }

    public void setCreatedFileName(String createdFileName) {
        this.createdFileName = createdFileName;
    }

    public void uploadFile(MultipartFile file){
        try {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(getUploadPath());
                //create directory if not exist
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(getUploadPath() + "/" + resultFileName));
                setCreatedFileName(resultFileName);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
