package com.rap.restservicevalidator.serviceimpl;

import com.rap.restservicevalidator.service.IsZipService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author christiaan.griffioen on feb, 2021
 */
@Service
public class IsZipImpl implements IsZipService<File> {

    @Override
    public boolean isZip(File file) {
        int fileSignature = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            fileSignature = raf.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSignature == 0x504B0304 || fileSignature == 0x504B0506 || fileSignature == 0x504B0708;
    }
}
