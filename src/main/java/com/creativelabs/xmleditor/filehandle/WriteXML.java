package com.creativelabs.xmleditor.filehandle;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class WriteXML {

    public static void main(String[] args) {
        String NS_URL = "people_schema";

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("people");
            doc.appendChild(root);
            Element employee = doc.createElement("employee");
            employee.setAttribute("id", "1");
            root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:schemaLocation", NS_URL + ".xsd");
            root.appendChild(employee);

            Element pesel = doc.createElement("pesel");
            pesel.appendChild(doc.createTextNode("81032401612"));
            employee.appendChild(pesel);

            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("John"));
            employee.appendChild(firstname);

            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("Silver"));
            employee.appendChild(lastname);

            Element phone = doc.createElement("phone");
            phone.appendChild(doc.createTextNode("555143111"));
            employee.appendChild(phone);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/test.xml"));
            transformer.transform(source, result);

            transformer.transform(new DOMSource(doc),
                    new StreamResult(new FileOutputStream("src/main/resources/test.xml")));

            System.out.println("File saved!");
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException pce) {
            pce.printStackTrace();
        }

    }
}