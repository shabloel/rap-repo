package com.rap.restservicevalidator.testvalidations;

import com.rap.restservicevalidator.service.IsZipService;
import com.rap.restservicevalidator.utils.MultiPartToFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author christiaan.griffioen on feb, 2021
 */

public class TestIsZip {

    private final IsZipService isZipService;
    //private final File upaFileStandard = new File();

    @Autowired
    public TestIsZip(IsZipService isZipService) {
        this.isZipService = isZipService;
    }

    @Test
    void testUnzip(File file){

    }
}
