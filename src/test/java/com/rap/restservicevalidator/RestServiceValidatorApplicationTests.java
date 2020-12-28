package com.rap.restservicevalidator;

import com.rap.service.XmlValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestServiceValidatorApplicationTests {

	@Autowired
	private ValidatorController controller;

	@Test
	public void contextLoads() throws Exception {
		assertEquals(true, controller != null);
	}

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
