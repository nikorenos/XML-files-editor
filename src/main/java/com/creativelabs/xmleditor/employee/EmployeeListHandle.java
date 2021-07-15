package com.creativelabs.xmleditor.employee;

import com.creativelabs.xmleditor.exception.DuplicateException;
import com.creativelabs.xmleditor.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListHandle {
    public List<Employee> modifyEmployeeList(Employee employee, List<Employee> list) {
        String employeeId = employee.getId();
        List<Employee> foundEmployeeList = list.stream()
                .filter(i -> i.getId().equals(employeeId))
                .collect(Collectors.toList());

        if (foundEmployeeList.size()>0) {
            Employee foundEmployee = foundEmployeeList.get(0);
            list.set(list.indexOf(foundEmployee), employee);
        } else {
            throw new NotFoundException("Employee id " + employeeId + " is not found.");
        }
        return list;
    }

    public List<Employee> addEmployeeToList(Employee employee, List<Employee> list) {
        String employeeId = employee.getId();
        List<Employee> foundEmployeeList = list.stream()
                .filter(i -> i.getId().equals(employeeId))
                .collect(Collectors.toList());

        if (foundEmployeeList.size()>0) {
            throw new DuplicateException("Employee with id " + employeeId + " is already in the file.");
        } else {
            list.add(employee);
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
        } else  {
            throw new NotFoundException("Employee with id " + employeeId + " is not found.");
        }
        return list;
    }
}
