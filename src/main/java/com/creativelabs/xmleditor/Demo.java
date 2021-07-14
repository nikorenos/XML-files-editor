package com.creativelabs.xmleditor;

import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee("1", "81032401612", "John", "Smith", "500300200");
        Employee employee2 = new Employee("1", "55555555555", "Micheal", "Milko", "11111111");
        employeeList.add(employee1);
        System.out.println(employeeList);

        EmployeeListHandle handle = new EmployeeListHandle();
        //handle.modifyEmployeeList(employee2, employeeList);
        handle.deleteEmployeeFromList(1, employeeList);

    }
}
