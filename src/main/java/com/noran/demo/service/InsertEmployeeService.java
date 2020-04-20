package com.noran.demo.service;

import com.noran.demo.model.Employee;
import com.noran.demo.model.samples.SampleEmployees;
import com.noran.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InsertEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ReactiveMongoOperations operations;

    public void insertEmployees() {
        SampleEmployees sampleEmployees = new SampleEmployees();

        operations.collectionExists(Employee.class)
                .flatMap(exists -> exists ? operations.dropCollection(Employee.class) : Mono.just(exists))
                .flatMap(o -> operations.createCollection(Employee.class, new CollectionOptions(1024L * 1024L, 10L, true)))
                .then()
                .block();

        employeeRepository.saveAll(Flux.fromIterable(sampleEmployees.createSampleEmployees())).then().block();

    }
}
