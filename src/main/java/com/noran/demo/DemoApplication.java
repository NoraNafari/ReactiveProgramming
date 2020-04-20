package com.noran.demo;

import com.noran.demo.service.InsertEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    InsertEmployeeService insertEmployeeService;

    @Override
    public void run(String... args) throws Exception {
        insertEmployeeService.insertEmployees();
    }
}
