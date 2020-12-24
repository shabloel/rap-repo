package com.rap.service;

import org.springframework.stereotype.Service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class XmlValidatorService {

    /** The validator. */
    private static Validator validator;

    /**
     * Inits the validator.
     *
     * @throws org.xml.sax.SAXException
     *              SAX exception
     */
    public static void init() throws SAXException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("xsd/upa_2021_request.xsd"));
        validator = schema.newValidator();
    }


    /**
     * Validate.
     *
     * @param xml
     *            the xml
     * @return true, if successful
     * @throws SAXException
     *             the SAX exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static boolean validate(String xml) throws SAXException, IOException {

        boolean returnValue = false;

        if (validator == null) {
            init();
        }

        try {
            validator.validate(new StreamSource(new StringReader(xml)));
            returnValue = true;
        } catch (SAXException | IOException exception) {
            throw exception;
        }

        return returnValue;
    }

    /**
     * Validate.
     *
     * @param path
     *            the path
     * @return true, if successful
     * @throws SAXException
     *             the SAX exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static boolean validate(Path path) throws SAXException, IOException {

        String xml = new String(Files.readAllBytes(path));

        return validate(xml);
    }

    /**
     * Gets the validator.
     *
     * @return the validator
     * @thr	ows SAXException the SAX exception
     */
    public static Validator getValidator() throws SAXException{

        if (validator == null) {
            init();
        }
        return validator;
    }

}
