package com.creativelabs.xmleditor.filehandle;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import com.creativelabs.xmleditor.exception.NotValidXMLFile;
import org.xml.sax.SAXException;

public class ValidateXML {

    public void validateXMLInfo(String xmlFilePath, String xsdFilePath) {
        boolean isValid = validateXMLSchema(xmlFilePath,xsdFilePath);

        if (isValid) {
            System.out.println("File " + xmlFilePath + " is valid against " + xsdFilePath);
        } else {
            throw new NotValidXMLFile("File " + xmlFilePath + " is not valid against " + xsdFilePath);
        }
    }

    public static boolean validateXMLSchema(String xmlFilePath,String xsdFilePath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            return false;
        }
        return true;
    }
}