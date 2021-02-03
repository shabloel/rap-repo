package com.rap.restservicevalidator.controllers;

import com.rap.restservicevalidator.model.PensioenAangifteResponse;
import com.rap.restservicevalidator.service.ResponseCreatorService;
import com.rap.restservicevalidator.service.UpaService;
import com.rap.restservicevalidator.service.ValidatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Controller()
@RequestMapping("/api")
public class ValidatorController {

    private static Logger logger = LogManager.getLogger(ValidatorController.class);

    private final UpaService<PensioenAangifteResponse, MultipartFile> upaService;

    @Autowired
    public ValidatorController(UpaService<PensioenAangifteResponse, MultipartFile> upaService) {
        this.upaService = upaService;
    }

    @PostMapping(value="/upa", produces = MediaType.APPLICATION_XML_VALUE)
    public PensioenAangifteResponse processUPAPost(@RequestParam("file")MultipartFile multipartFile)throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        logger.info("The directory name with extension:" + fileName);
        logger.info("Contenttype: " + multipartFile.getContentType());
        logger.info("Size: " + multipartFile.getSize());

        return upaService.processUPA(multipartFile);
    }

}
