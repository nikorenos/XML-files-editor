package com.creativelabs.xmleditor.filehandle;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import com.creativelabs.xmleditor.employee.Employee;
import org.w3c.dom.*;
import java.io.File;
import java.util.List;


public class WriteXML {

    public void writeXMLFromList(List<Employee> list, String filePath) {
        String NS_URL = "people_schema";

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("people");
            doc.appendChild(root);
            root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                    "xs:schemaLocation", NS_URL + ".xsd");

            for (Employee person:list) {
            Element employee = doc.createElement("employee");
            employee.setAttribute("id", person.getId());
            root.appendChild(employee);

            Element pesel = doc.createElement("pesel");
            pesel.appendChild(doc.createTextNode(person.getPesel()));
            employee.appendChild(pesel);

            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode(person.getFirstname()));
            employee.appendChild(firstname);

            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode(person.getLastname()));
            employee.appendChild(lastname);

            Element phone = doc.createElement("phone");
            phone.appendChild(doc.createTextNode(person.getPhone()));
            employee.appendChild(phone);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("File " + filePath + " successfully saved.");
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}