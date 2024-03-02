package com.spring.service;

import java.util.List; // Importing java.util.List only
import java.util.Optional;

import com.spring.model.Employee;
import com.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create list of  employees
    public List<Employee> createEmployees(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

  //fetch all the employee
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    //fetch employee by Id
    public Optional<Employee> getEmployeeById(int employee_id) {
        return employeeRepository.findById(employee_id);

    }

    //update employee by id
    public String updateEmployee(int employee_id, Employee employee)
    {
        Optional<Employee> emp = employeeRepository.findById(employee_id);

        if (emp.isPresent()) {
            Employee existEmployee = emp.get();
            existEmployee.setEmployee_age(employee.getEmployee_age());
            existEmployee.setEmployee_name(employee.getEmployee_name());
            existEmployee.setEmployee_salary(employee.getEmployee_salary());
            existEmployee.setEmployee_city(employee.getEmployee_city());

            // Save
            employeeRepository.save(existEmployee);

            return "Employee updated successfully!";
        } else {
            return "Employee not found";
        }

    }

    //delete employee by id
    public String deleteEmployeeById(int employee_id)
    {
        Optional<Employee> emp = employeeRepository.findById(employee_id);

        if (emp.isPresent()) {
            employeeRepository.deleteById(employee_id);
            return "Employee deleted successfully!";
        } else {
            return "Employee not exist";
        }
    }

    //delete all  employee

    public String deleteAllEmployees() {
        try {
            employeeRepository.deleteAll();
            return "All employees deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting employees";
        }
    }
}
