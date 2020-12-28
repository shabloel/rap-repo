package com.rap.service;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
public class XmlValidator {

    /** The validator. */
    private static Validator validator;

    /**
     * Inits the validator.
     *
     * @throws org.xml.sax.SAXException
     *              SAX exception
     */
    public static void init() throws SAXException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new StreamSource(XmlValidator.class.getClassLoader().getResource("xsd/upa_2021_request.xsd").toExternalForm()));
        validator = schema.newValidator();
    }


    /**
     * Validate.
     *
     * @param input
     *            the xml
     * @return true, if successful
     * @throws SAXException
     *             the SAX exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static boolean validate(File input) throws SAXException, IOException, XMLStreamException {
        StAXSource source = null;
        FileInputStream inputStream = null;
        boolean returnValue = false;

        if (validator == null) {
            init();
        }

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            inputStream = new FileInputStream(input);
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            source = new StAXSource(xmlStreamReader);

            validator.validate(source);
            returnValue = true;
        } catch (SAXException | IOException | XMLStreamException exception) {
            throw exception;
        }

        return returnValue;
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
