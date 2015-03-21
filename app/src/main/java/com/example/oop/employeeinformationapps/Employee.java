package com.example.oop.employeeinformationapps;

/**
 * Created by OOP on 3/6/2015.
 */
public class Employee {
    private int id;
    private String code;
    private String name;
    private double salary;

    //This is a test change

    public Employee(String name, String code, double salary)
    {
        setName(name);
        setCode(code);
        setSalary(salary);
    }

    public Employee()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
