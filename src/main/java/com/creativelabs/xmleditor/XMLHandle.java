package com.creativelabs.xmleditor;

import com.creativelabs.xmleditor.employee.Employee;
import com.creativelabs.xmleditor.employee.EmployeeListHandle;
import com.creativelabs.xmleditor.exception.NotValidXMLFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandle {

    public boolean validateXMLSchema(String xmlFilePath,String xsdFilePath) {
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

    public ArrayList<Employee> readFromXMLFile(String filePath) throws ParserConfigurationException, IOException, SAXException {
        String id;
        String pesel;
        String firstname;
        String lastname;
        String phone;
        ArrayList<Employee> employeeList = new ArrayList<>();
        try
        {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("employee");
            for (int n = 0; n < nodeList.getLength(); n++)
            {
                Node node = nodeList.item(n);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    id = element.getAttribute("id");
                    pesel = element.getElementsByTagName("pesel").item(0).getTextContent();
                    firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                    phone = element.getElementsByTagName("phone").item(0).getTextContent();
                    employeeList.add(new Employee(id, pesel, firstname, lastname, phone));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employeeList;
    }

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

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        XMLHandle xmlHandle = new XMLHandle();
        EmployeeListHandle employeeListHandle = new EmployeeListHandle();
        String xmlFilePath = "src/main/resources/people.xml";
        String xsdFilePath = "src/main/resources/people_schema.xsd";
        Employee employee = new Employee("4", "99115555555", "Leon", "Zigi", "11111111");

        if (!xmlHandle.validateXMLSchema(xmlFilePath, xsdFilePath)) {
            throw new NotValidXMLFile("File " + xmlFilePath + " is not valid against " + xsdFilePath);
        }
        List<Employee> employeeList = xmlHandle.readFromXMLFile(xmlFilePath);
        //List<Employee> modifiedEmployeeList = employeeListHandle.modifyEmployeeList(employee,employeeList);
        List<Employee> modifiedEmployeeList2 = employeeListHandle.deleteEmployeeFromList(4, employeeList);
        xmlHandle.writeXMLFromList(modifiedEmployeeList2,xmlFilePath);
    }
}
