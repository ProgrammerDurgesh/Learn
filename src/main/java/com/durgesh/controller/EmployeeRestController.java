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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeRestController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @PostMapping()
    public Employee save(@RequestBody Employee employee)
    {
        return employeeRepo.save(employee);
    }
    @GetMapping
    public double employees()
    {
        List<Float> ages=employeeRepo.findAll().stream().map(Employee::getSalary).collect(Collectors.toList());
        return ages.stream().mapToDouble(aFloat -> aFloat).summaryStatistics().getSum();
    }
}
