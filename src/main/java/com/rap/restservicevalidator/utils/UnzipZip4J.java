package com.rap.restservicevalidator.utils;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class UnzipZip4J {
    private static void unpackZipfile(MultipartFile file, String fileBasePath)throws IOException, ZipException {
        File zip = File.createTempFile(UUID.randomUUID().toString(),"temp");
        // save file to temp
        FileOutputStream o = new FileOutputStream(zip);
        IOUtils.copy(file.getInputStream(), o);
        o.close();
        //unzip file
        ZipFile zipfile = new ZipFile(zip);
        zipfile.extractAll(fileBasePath);
        zip.delete();
    }
}
