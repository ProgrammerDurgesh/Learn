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
    //get All Employee
    @GetMapping
    public List<Employee> employees()
    {
        return employeeRepo.findAll();
    }

    //get Employee by max-age
    @GetMapping(value = "maxAge")
    public Optional<Employee> getEmployeeInOldestAge()
    {
        return employeeRepo.findAll().stream().max((employee, t1) ->Integer.compare(employee.getAge(),t1.getAge()) );
    }

}
