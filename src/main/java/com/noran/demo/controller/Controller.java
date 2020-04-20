package com.noran.demo.controller;

import com.google.common.collect.Lists;
import com.noran.demo.exception.EmployeeNotFoundException;
import com.noran.demo.model.Employee;
import com.noran.demo.repository.EmployeeRepository;
import com.noran.demo.service.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RandomStringGenerator stringGenerator;

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

    //Return a stream of every 100 string of a list
    @GetMapping("/stringStreams")
    public Flux<ServerSentEvent<Stream<String>>> strings() {
        List<String> listOfStrings = stringGenerator.generate();
        return Flux.interval(Duration.ofSeconds(10))
                .map(sequence -> ServerSentEvent.<Stream<String>> builder()
                        .id(String.valueOf(sequence))
                        .data(Lists.partition(listOfStrings, 100).iterator().next().stream())
                        .build());
    }

}
