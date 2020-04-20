package com.noran.demo.controller;

import com.noran.demo.exception.EmployeeNotFoundException;
import com.noran.demo.model.Employee;
import com.noran.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public Mono<ResponseEntity<Employee>> createEmployee(Employee employee) {
        return employeeRepository.save(employee).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ServerSentEvent<Object>> getEmployeeById(@PathVariable("id") String id) {
        return employeeRepository.findById(id)
                .map(employee -> ServerSentEvent.builder().data(employee).build())
                .switchIfEmpty(Mono.error(() -> new EmployeeNotFoundException("Employee not found.")));
    }


    @GetMapping("/all")
    public Flux<ServerSentEvent> returnAllEmployees() {
        return employeeRepository.findAll()
                .map(employee -> ServerSentEvent.builder().data(employee).build());
    }
    
}
