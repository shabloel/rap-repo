package com.rap.restservicevalidator;

import com.rap.berichten.responsebericht.AdministratieveEenheid;
import com.rap.berichten.responsebericht.Bericht;
import com.rap.berichten.responsebericht.PensioenAangifteResponse;
import com.rap.service.XmlValidator;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileSystemUtils;
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
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController()
@RequestMapping("/api")
public class ValidatorController {

    private static Logger logger = LogManager.getLogger(ValidatorController.class);

    private XmlValidator xmlValidator;
    private final AtomicLong idBer = new AtomicLong();
    private String respStr;
    private static final String FILE_BASE_PATH = "src/main/java/com/rap/upload";

    @PostMapping(value="/upa", produces = MediaType.APPLICATION_XML_VALUE)
    public PensioenAangifteResponse processUPAPost(@RequestParam("file")MultipartFile file)throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String rootFolder = null;

        if(!Files.exists(Paths.get(FILE_BASE_PATH))){
            Files.createDirectories(Paths.get(FILE_BASE_PATH));
        }

        logger.info("The directory name: " + rootFolder);
        logger.info("The directory name with extension:" + fileName);
        logger.info("Contenttype: " + file.getContentType());
        logger.info("Size: " + file.getSize());
        logger.info("Basepath for file upload: " + FILE_BASE_PATH);

        try{
            unpackZipfile(file);
        }
        catch(IOException | ZipException e){
            e.printStackTrace();
        }

        //check 1: is het binnenkomende bestand zip?
        if(!isZip(file)){
            respStr = "Not a zipfile";
        }else{
            rootFolder = fileName.substring(0, fileName.lastIndexOf("."));
        }

        //check 2: bevat het binnenkomende bestand een xml file?
        if(!isXML(FILE_BASE_PATH +"/"+rootFolder)){
            respStr = "Directory does not contain xml file";
        }

        //check 4: validate xml file
        logger.info("complete path for validation: " + getFilename(FILE_BASE_PATH +"/"+rootFolder).get(0));
        if(!XmlValidator.validate(new File(getFilename(FILE_BASE_PATH +"/"+rootFolder).get(0)))){
            respStr = "Not a valid XML file";
        }else{
            respStr = "VALID";
        }
        FileSystemUtils.deleteRecursively(new File("src/main/java/com/rap/upload/"));
        return xmlResponse(respStr);
    }

    private PensioenAangifteResponse xmlResponse(String respStr){
        Bericht bericht = new Bericht(respStr, idBer.getAndIncrement());
        AdministratieveEenheid administratieveEenheid = new AdministratieveEenheid("012345678L01", LocalDateTime.of(2021,03,15,10,52,54), ZonedDateTime.now());
        PensioenAangifteResponse pensioenAangifteResponse = new PensioenAangifteResponse(bericht, administratieveEenheid);
        return pensioenAangifteResponse;
    }

    private static boolean isZip(MultipartFile f) {
        return f.getContentType().equalsIgnoreCase("application/zip");
    }

    private static boolean isXML(String loc)throws IOException{
        return Files.list(Paths.get(loc))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".xml"))
                .count()==1;
    }

    private static List<String> getFilename(String path){
        List<String> result = null;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

             result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString())
                     .filter(x -> x.endsWith(".xml"))
                     .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void unpackZipfile(MultipartFile file)throws IOException, ZipException{
        File zip = File.createTempFile(UUID.randomUUID().toString(),"temp");
        // save file to temp
        FileOutputStream o = new FileOutputStream(zip);
        IOUtils.copy(file.getInputStream(), o);
        o.close();

        //unzip file
        ZipFile zipfile = new ZipFile(zip);
        zipfile.extractAll(FILE_BASE_PATH);
        zip.delete();
    }
}
