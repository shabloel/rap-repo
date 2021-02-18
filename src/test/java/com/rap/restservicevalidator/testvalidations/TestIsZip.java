package com.rap.restservicevalidator.testvalidations;

import com.rap.restservicevalidator.service.IsZipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author christiaan.griffioen on feb, 2021
 */
@SpringBootTest
public class TestIsZip {

    private final IsZipService<File> isZipService;

    @Autowired
    public TestIsZip(IsZipService<File> isZipService) {
        this.isZipService = isZipService;
    }

    @Test
    void testUnzip(){
        File file = new File("/test/resources/berichten/upa-valid-zip/upa-valid.zip");
        assert(isZipService.isZip(file));
    }
}
