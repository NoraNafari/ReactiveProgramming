package com.noran.demo.repository;

import com.noran.demo.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

    Mono<Employee> findById(String id);
    Mono<Boolean> existsById(String id);


}
