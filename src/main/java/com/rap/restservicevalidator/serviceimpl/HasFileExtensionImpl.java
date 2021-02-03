package com.rap.restservicevalidator.serviceimpl;

import com.rap.restservicevalidator.service.HasFileExtensionService;
import com.rap.restservicevalidator.utils.MultiPartToFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author christiaan.griffioen on feb, 2021
 */
@Service
public class HasFileExtensionImpl implements HasFileExtensionService<MultipartFile> {

    @Override
    public boolean isXML(MultipartFile multipartFile){

        try {
            File file = MultiPartToFile.multipartToFile(multipartFile, "input");

            return Files.list(Paths.get(file.toURI()))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml"))
                    .count() == 1;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
