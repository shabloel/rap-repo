package com.rap.restservicevalidator;

import com.rap.service.XmlValidator;
import com.rap.utils.ZipFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class RestServiceValidatorApplicationTests {
	private static final String SOURCE_FOLDER = "src/test/resources/berichten/upa-valid";
	private static final String OUTPUT_ZIP_FILE = "src/test/resources/upa-valid.zip";
	@Autowired
	private ValidatorController controller;

	private static final String PATH = "src/test/resources/berichten";

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Test
	public void uploadCorrectZipfileTest() throws Exception {
		//zip();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		Path path = Paths.get("src/test/resources/upa-valid.zip");
		MockMultipartFile file = new MockMultipartFile(
			"file"
			,"upa-valid.zip"
			, "application/zip"
			, path.toString().getBytes());

		mockMvc.perform(multipart("/api/upa")
			.file(file)
			.characterEncoding("UTF-8"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void validateValdiationMethod() throws SAXException, IOException, XMLStreamException {
	    Path path = FileSystems.getDefault().getPath(PATH, "UPA_Regulier_2021_upa.xml");
        assertEquals(true, XmlValidator.validate(new File("src/test/resources/berichten/UPA_Regulier_2021_upa.xml")));
	}

	public static void zip() throws IOException {
		FileOutputStream fos = new FileOutputStream(OUTPUT_ZIP_FILE);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(SOURCE_FOLDER);
		ZipFile.zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
	}
}
