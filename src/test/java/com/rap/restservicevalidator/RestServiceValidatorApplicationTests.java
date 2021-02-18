package com.rap.restservicevalidator;

import com.rap.restservicevalidator.controllers.ValidatorController;
import com.rap.restservicevalidator.model.PensioenAangifteResponse;
import com.rap.restservicevalidator.service.ValidatorService;
import com.rap.restservicevalidator.utils.MultiPartToFile;
import com.rap.restservicevalidator.utils.ZipFile;
import jdk.nashorn.internal.ir.annotations.Ignore;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@SpringBootTest
class RestServiceValidatorApplicationTests {
	private static final String SOURCE_FOLDER = "src/test/resources/berichten/unzipped";
	private static final String DESTINATION_FOLDER = "src/test/resources/berichten";

	private final ValidatorController controller;
	private final ValidatorService<MultiPartToFile> validatorService;

	private static final String PATH = "src/test/resources/berichten/unzipped/upa-valid";

	@Autowired
	public RestServiceValidatorApplicationTests(ValidatorController controller, ValidatorService<MultiPartToFile> validatorService) {
		this.controller = controller;
		this.validatorService = validatorService;
	}

	@Autowired
	WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Test
	void validateValdiationMethod() throws SAXException, IOException, XMLStreamException {
		//assertEquals(true, validatorService.validate(new File("src/test/resources/berichten/unzipped/upa-valid/UPA_Regulier_2021_upa.xml")));
	}

	public void uploadCorrectZipfileTest() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		Path path = Paths.get("src/test/resources/berichten/upa-valid-zip/upa-valid.zip");
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

	public void uploadZipFileWithTxtFile() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		Path path = Paths.get("src/test/resources/berichten/upa-no-xml-file-zip/upa-no-xml-file.zip");
		MockMultipartFile file = new MockMultipartFile(
				"file"
				,"upa-no-xml-file.zip"
				, "application/zip"
				, path.toString().getBytes());

		mockMvc.perform(multipart("/api/upa")
				.file(file)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public void uploadZipFileWithInvalidXml() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		Path path = Paths.get("src/test/resources/berichten/upa-invalid-xml-zip/upa-invalid-xml-zip.zip");
		MockMultipartFile file = new MockMultipartFile(
				"file"
				,"upa-invalid-xml-zip.zip"
				, "application/zip"
				, path.toString().getBytes());

		mockMvc.perform(multipart("/api/upa")
				.file(file)
				.characterEncoding("UTF-8"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static void zip(String sourceFolder, String destinationFolder) throws IOException {
		FileOutputStream fos = new FileOutputStream(destinationFolder);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(sourceFolder);
		ZipFile.zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
	}
}
