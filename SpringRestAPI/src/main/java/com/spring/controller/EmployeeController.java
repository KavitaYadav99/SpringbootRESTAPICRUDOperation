package com.spring.controller;

import com.spring.model.Employee;
import com.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public List<Employee> createEmployees(@RequestBody List<Employee> employee) {
        return employeeService.createEmployees(employee);
    }

    @GetMapping("/employees")    //get all the employee
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> list = employeeService.getAllEmployees();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //ResponseEntity is a class in Spring that represents the entire HTTP response, including status code, headers, and body.


    //get employee by id
    @GetMapping("/employees/{employee_id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int employee_id) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(employee_id);
            if (employee.isPresent()) {
                return new ResponseEntity<Employee>(employee.get(), HttpStatus.FOUND);
            } else {
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Employee>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/{employee_id}")
    public String updateEmployeeById(@PathVariable int employee_id, @RequestBody Employee employee) {
        try {
            String message = employeeService.updateEmployee(employee_id, employee);
            if (message.equals("Employee updated successfully!")) {
                return message;
            } else {
                return "Employee not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating employee";
        }
    }
    //delete by emp id

    @DeleteMapping("/employees/{employee_id}")
    public String deleteEmployeeById(@PathVariable int employee_id) {
        try {
            return employeeService.deleteEmployeeById(employee_id);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting employee";
        }
    }

    //delete all employee
    @DeleteMapping("/employees")
    public String deleteAllEmployees() {
        try {
            return employeeService.deleteAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting employees";
        }
    }
}
