package com.creativelabs.xmleditor.filehandle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.creativelabs.xmleditor.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {

    public ArrayList<Employee> readFromXMLFile(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(filePath));
        NodeList nodeList = document.getElementsByTagName("employee");
        NodeList nodeList2 = document.getElementsByTagName("pesel");
        NodeList nodeList3 = document.getElementsByTagName("firstname");
        NodeList nodeList4 = document.getElementsByTagName("lastname");
        NodeList nodeList5 = document.getElementsByTagName("phone");

        String id = null;
        String pesel;
        String firstname;
        String lastname;
        String phone;
        ArrayList<Employee> employeeList = new ArrayList<>();

        for(int x=0,size= nodeList.getLength(); x<size; x++) {
            if (nodeList.item(x).getAttributes().getNamedItem("id") != null) {
                id = nodeList.item(x).getAttributes().getNamedItem("id").getNodeValue();
                //System.out.println(id);
            }
            pesel = nodeList2.item(x).getFirstChild().getNodeValue();
            //System.out.println(pesel);
            firstname = nodeList3.item(x).getFirstChild().getNodeValue();
            //System.out.println(firstname);
            lastname = nodeList4.item(x).getFirstChild().getNodeValue();
            //System.out.println(lastname);
            phone = nodeList5.item(x).getFirstChild().getNodeValue();
            //System.out.println(phone);
            employeeList.add(new Employee(id, pesel, firstname, lastname, phone));
        }

        System.out.println("File " + filePath + " successfully read.");
        return employeeList;
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        ReadXML readXML = new ReadXML();

    }


}
