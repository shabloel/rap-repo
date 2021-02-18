package com.rap.restservicevalidator.serviceimpl;

import com.rap.restservicevalidator.model.PensioenAangifteResponse;
import com.rap.restservicevalidator.service.HasFileExtensionService;
import com.rap.restservicevalidator.service.IsZipService;
import com.rap.restservicevalidator.service.ResponseCreatorService;
import com.rap.restservicevalidator.service.UpaService;
import com.rap.restservicevalidator.service.ValidatorService;
import com.rap.restservicevalidator.utils.MultiPartToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author christiaan.griffioen on feb, 2021
 */
@Service
public class UpaServiceImpl implements UpaService<PensioenAangifteResponse, MultipartFile> {

    private HasFileExtensionService hasFileExtensionService;
    private IsZipService<File> isZipService;
    private ValidatorService validatorService;
    private ResponseCreatorService<PensioenAangifteResponse> responseCreatorService;

    @Autowired
    public UpaServiceImpl(HasFileExtensionService hasFileExtensionService, IsZipService<File> isZipService, ValidatorService validatorService, ResponseCreatorService<PensioenAangifteResponse> responseCreatorService) {
        this.hasFileExtensionService = hasFileExtensionService;
        this.isZipService = isZipService;
        this.validatorService = validatorService;
        this.responseCreatorService = responseCreatorService;
    }

    @Override
    public PensioenAangifteResponse processUPA(final MultipartFile multipartFile) {
        File file = null;
        String respStr = null;

        try {
            file = MultiPartToFile.multipartToFile(multipartFile, "input");
        }catch(IOException e){
            e.printStackTrace();
        }

        if(!isZipService.isZip(file)){
            respStr = "Not a zipfile";

        }else if(hasFileExtensionService.isXML(multipartFile)){
            respStr = "Directory does not contain xml file";
        }else if(!validatorService.validate(file)){
            respStr = "Not a valid XML file";
        }else{
            respStr = "VALID";
        }
        return responseCreatorService.createXMLResponse(respStr);
    }
}
