package com.rap.restservicevalidator;

import com.rap.berichten.responseberichten.AdministratieveEenheid;
import com.rap.berichten.responseberichten.Bericht;
import com.rap.berichten.responseberichten.PensioenAangifteResponse;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/api")
public class ValidatorController {

    private static Logger logger = LogManager.getLogger(ValidatorController.class);
    private final AtomicLong counter = new AtomicLong();
    @Value("${destination.dir}")
    private String fileBasePath;

    @PostMapping(value="/upa", produces = MediaType.APPLICATION_XML_VALUE)
    public PensioenAangifteResponse processUPAPost(@RequestParam("file")MultipartFile file)throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        logger.info("The complete filename:" + fileName);
        logger.info("Contenttype: " + file.getContentType());
        logger.info("Size: " + file.getSize());

        /**
         * Create directory if it doesn't exist
         */
        Path path = Paths.get(fileBasePath);
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        File zip = File.createTempFile(UUID.randomUUID().toString(),"temp");

        try{
            /**
             * save file to temp
             */
            FileOutputStream o = new FileOutputStream(zip);
            IOUtils.copy(file.getInputStream(), o);
            o.close();

            /**
             * unizp file from temp by zip4j
             */
            ZipFile zipfile = new ZipFile(zip);
            zipfile.extractAll(fileBasePath);

            /**
             * Alternative without zip4j
             */
            //Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        }
        catch(IOException | ZipException e){
            e.printStackTrace();
        } finally{
            /**
             * delete temp file
             */
            zip.delete();
        }
        return xmlMessage();
    }

    private PensioenAangifteResponse xmlMessage(){
        Bericht bericht = new Bericht("VALID", counter.getAndIncrement());
        AdministratieveEenheid administratieveEenheid = new AdministratieveEenheid("012345678L01", LocalDateTime.of(2021,03,15,10,52,54), ZonedDateTime.now());
        PensioenAangifteResponse pensioenAangifteResponse = new PensioenAangifteResponse(bericht, administratieveEenheid);
        return pensioenAangifteResponse;
    }


}
