package com.noran.demo.model.samples;

import com.noran.demo.model.Employee;
import com.noran.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class SampleEmployees {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> createSampleEmployees() {
        Employee employee01 = new Employee("1", "name01");
        Employee employee02 = new Employee("2", "name02");
        Employee employee03 = new Employee("3", "name03");
        Employee employee04 = new Employee("4", "name04");
        Employee employee05 = new Employee("5", "name05");
        Employee employee06 = new Employee("6", "name06");
        Employee employee07 = new Employee("7", "name07");
        Employee employee08 = new Employee("8", "name08");
        Employee employee09 = new Employee("9", "name09");
        Employee employee10 = new Employee("10", "name10");


        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee01);
        employeeList.add(employee02);
        employeeList.add(employee03);
        employeeList.add(employee04);
        employeeList.add(employee05);
        employeeList.add(employee06);
        employeeList.add(employee07);
        employeeList.add(employee08);
        employeeList.add(employee09);
        employeeList.add(employee10);


        return employeeList;
    }


}
