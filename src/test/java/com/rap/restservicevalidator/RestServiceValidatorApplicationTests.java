package com.rap.restservicevalidator;

import com.rap.service.XmlValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@SpringBootTest
class RestServiceValidatorApplicationTests {

	private static final String PATH = "src/test/resources/berichten";

    /**
     * Valideer incident-melding bericht.
     *
     * @throws SAXException the sax exception
     * @throws IOException  the io exception
     */

	@Test
	void validateUPA_Regulier() throws SAXException, IOException, XMLStreamException {
	    Path path = FileSystems.getDefault().getPath(PATH, "UPA_Regulier_2021_upa.xml");
        assertEquals(true, XmlValidator.validate(new File("src/test/resources/berichten/UPA_Regulier_2021_upa.xml")));
	}

}
