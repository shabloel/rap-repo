package com.rap.restservicevalidator.serviceimpl;

import com.rap.restservicevalidator.service.IsZipService;
import com.rap.restservicevalidator.utils.UnzipFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author christiaan.griffioen on feb, 2021
 */
@Service
public class IsZipImpl implements IsZipService<MultipartFile> {

    private static final String FILE_BASE_PATH = "src/main/java/com/rap/upload";

    @Override
    public boolean isZip(MultipartFile file) {
        if(file.getContentType().equalsIgnoreCase("application/zip")) {
            try {
                UnzipFile.unzipFile(file, new File(FILE_BASE_PATH));
            }catch(IOException e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
