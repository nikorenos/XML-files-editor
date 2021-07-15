package com.creativelabs.xmleditor;

import com.creativelabs.xmleditor.employee.Employee;
import com.creativelabs.xmleditor.employee.EmployeeListHandle;
import com.creativelabs.xmleditor.filehandle.ReadXML;
import com.creativelabs.xmleditor.filehandle.ValidateXML;
import com.creativelabs.xmleditor.filehandle.WriteXML;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Manager {
    ReadXML readXML = new ReadXML();
    WriteXML writeXML = new WriteXML();
    ValidateXML validateXML = new ValidateXML();
    EmployeeListHandle employeeListHandle = new EmployeeListHandle();

    public void addRecordToXML(String xmlFilePath, String xsdFilePath, Employee employee) throws ParserConfigurationException, IOException, SAXException {
        validateXML.validateXMLInfo(xmlFilePath,xsdFilePath);
        List<Employee> employeeList = readXML.readFromXMLFile(xmlFilePath);
        employeeListHandle.addEmployeeToList(employee,employeeList);
        writeXML.writeXMLFromList(employeeList,xmlFilePath);
    }

    public void modifyRecordInXML(String xmlFilePath, String xsdFilePath, Employee employee) throws ParserConfigurationException, IOException, SAXException {
        validateXML.validateXMLInfo(xmlFilePath,xsdFilePath);
        List<Employee> employeeList = readXML.readFromXMLFile(xmlFilePath);
        employeeListHandle.modifyEmployeeList(employee,employeeList);
        writeXML.writeXMLFromList(employeeList,xmlFilePath);
    }

    public void deleteRecordInXML(String xmlFilePath, String xsdFilePath, int employeeId) throws ParserConfigurationException, IOException, SAXException {
        validateXML.validateXMLInfo(xmlFilePath,xsdFilePath);
        List<Employee> employeeList = readXML.readFromXMLFile(xmlFilePath);
        employeeListHandle.deleteEmployeeFromList(employeeId, employeeList);
        writeXML.writeXMLFromList(employeeList,xmlFilePath);
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Manager manager = new Manager();
        String xmlFilePath = "src/main/resources/people.xml";
        String xsdFilePath = "src/main/resources/people_schema.xsd";
        Employee employee = new Employee("21", "11115555555", "Micheal", "Zigi", "11111111");
        //manager.addRecordToXML(xmlFilePath, xsdFilePath, employee);
        //manager.modifyRecordInXML(xmlFilePath, xsdFilePath, employee);
        manager.deleteRecordInXML(xmlFilePath, xsdFilePath,4);
    }
}
