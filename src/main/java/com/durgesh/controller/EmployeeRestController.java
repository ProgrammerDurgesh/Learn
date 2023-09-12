package com.durgesh.controller;

import com.durgesh.entity.Employee;
import com.durgesh.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeRestController {
    //JAVA-8 <-*-> Concept
    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping()
    public Employee save(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    //get All Employee
    @GetMapping
    public List<Employee> employees() {
        return employeeRepo.findAll();
    }

    //get Employee by max-age
    @GetMapping(value = "max-age")
    public Optional<Employee> getEmployeeInOldestAge() {
        return employeeRepo.findAll().stream().max((employee, t1) -> Integer.compare(employee.getAge(), t1.getAge()));
    }

    @GetMapping(value = "min-age")
    public Optional<Employee> getEmployeeInYoungestAge() {
        return employeeRepo.findAll().stream().min((employee, t1) -> Integer.compare(employee.getAge(), t1.getAge()));
    }

    @GetMapping(value = "max-salary")
    public Optional<Employee> highestSalariedEmployee() {
        return employeeRepo.findAll().stream().max((employee, t1) -> Integer.compare(employee.getSalary().intValue(), t1.getSalary().intValue()));
    }

    @GetMapping(value = "min-salary")
    public Optional<Employee> lowestSalariedEmployee() {
        return employeeRepo.findAll().stream().min((employee, t1) -> Integer.compare(employee.getSalary().intValue(), t1.getSalary().intValue()));
    }

    @GetMapping(value = "total-salary")
    public OptionalDouble budgetOfSalary() {
        return employeeRepo.findAll().stream().mapToDouble(Employee::getSalary).average();
    }

}
