package com.creativelabs.xmleditor;

import com.creativelabs.xmleditor.filehandle.ReadXML;
import com.creativelabs.xmleditor.filehandle.WriteXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Manager {
    ReadXML readXML = new ReadXML();
    WriteXML writeXML = new WriteXML();
    EmployeeListHandle employeeListHandle = new EmployeeListHandle();

    public void addRecordToXML(String filePath, Employee employee) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> employeeList = readXML.readFromXMLFile(filePath);
        employeeListHandle.addEmployeeToList(employee,employeeList);
        writeXML.writeXMLFromList(employeeList,filePath);
    }

    public void modifyRecordInXML(String filePath, Employee employee) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> employeeList = readXML.readFromXMLFile(filePath);
        employeeListHandle.modifyEmployeeList(employee,employeeList);
        writeXML.writeXMLFromList(employeeList,filePath);
    }

    public void deleteRecordInXML(String filePath, int employeeId) throws ParserConfigurationException, IOException, SAXException {
        List<Employee> employeeList = readXML.readFromXMLFile(filePath);
        employeeListHandle.deleteEmployeeFromList(employeeId, employeeList);
        writeXML.writeXMLFromList(employeeList,filePath);
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Manager manager = new Manager();
        String filePath = "src/main/resources/test.xml";
        Employee employee = new Employee("3", "11115555555", "Micheal", "Milko", "11111111");
        //manager.addRecordToXML(filePath, employee);
        manager.modifyRecordInXML(filePath, employee);
        //manager.deleteRecordInXML(filePath,2);
    }
}
