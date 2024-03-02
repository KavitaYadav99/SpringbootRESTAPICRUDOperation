package com.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    @Column(name = "employee_name")
    private String employee_name;

    @Column(name = "employee_age")
    private int employee_age;

    @Column(name = "employee_salary")
    private float employee_salary;


    @Column(name = "employee_city")
    private String employee_city;

}
