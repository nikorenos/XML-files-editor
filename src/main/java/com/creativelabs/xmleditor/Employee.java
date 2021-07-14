package com.creativelabs.xmleditor;

public class Employee {
    private String id;
    private String pesel;
    private String firstname;
    private String lastname;
    private String phone;

    public Employee(String id, String pesel, String firstname, String lastname, String phone) {
        this.id = id;
        this.pesel = pesel;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", pesel='" + pesel + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
