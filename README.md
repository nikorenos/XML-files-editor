# Identity IQ JDBC project

### 1. Project description
Project allows handling XML files: validating against XSD file, reading and writing modified XML file.

### 2. Project methods
Methods in XMLHandle class:
1. validateXMLSchema - return true when xml file is validated against xsd schema.
2. readFromXMLFile - returns list of Employee derived from xml file.
3. writeXMLFromList - write modified xml file with provided Employee list.

Methods in EmployeeListHandle class:
4. modifyEmployeeList - returns modified list of Employees which was provided as a list from XML file.
5. addEmployeeToList - adds new employee record and returns modified list of Employees which was provided as a list from XML file.
6. deleteEmployeeFromList - deleets employee record and returns modified list of Employees which was provided as a list from XML file.

### 3. Requirements
Technologies used in project:
```bash
1) Java 8
2) Gradle 6.8
```

### 4. Troubleshooting
If you encounter any problems regarding operation, please let me know. 

