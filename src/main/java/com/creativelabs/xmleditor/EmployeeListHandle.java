package com.creativelabs.xmleditor;

import com.creativelabs.xmleditor.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListHandle {
    public List<Employee> modifyEmployeeList(Employee employee, ArrayList<Employee> list) {
        String employeeId = employee.getId();
        ArrayList<Employee> foundEmployeeList = (ArrayList<Employee>) list.stream()
                .filter(i -> i.getId().equals(employeeId))
                .collect(Collectors.toList());

        if (foundEmployeeList.size()>0) {
            Employee foundEmployee = foundEmployeeList.get(0);
            list.set(list.indexOf(foundEmployee), employee);
            System.out.println(list);
        } else {
            throw new NotFoundException("Employee id " + employeeId + " is not found.");
        }
        return list;
    }

    public List<Employee> addEmployeeToList(Employee employee, List<Employee> list) {
        String employeeId = employee.getId();
        ArrayList<Employee> foundEmployeeList = (ArrayList<Employee>) list.stream()
                .filter(i -> i.getId().equals(employeeId))
                .collect(Collectors.toList());

        if (foundEmployeeList.size()>0) {
            System.out.println("Employee with " + employeeId + " is already on the list!");
        } else {
            list.add(employee);
            System.out.println(list);
        }
        return list;
    }

    public List<Employee> deleteEmployeeFromList(int id, List<Employee> list) {
        String employeeId = String.valueOf(id);
        ArrayList<Employee> foundEmployeeList = (ArrayList<Employee>) list.stream()
                .filter(i -> i.getId().equals(employeeId))
                .collect(Collectors.toList());

        if (foundEmployeeList.size()>0) {
            Employee foundEmployee = foundEmployeeList.get(0);
            list.remove(foundEmployee);
            System.out.println(list);
        } else  {
            throw new NotFoundException("Employee id " + employeeId + " is not found.");
        }
        return list;
    }
}
