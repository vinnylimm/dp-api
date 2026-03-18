package com.teste.dp.dto;

import com.teste.dp.entities.Department;
import com.teste.dp.entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmployeeDto {

    private Long id;
    private String name;

    private List<DepartmentDto> departments = new ArrayList<>();

    public EmployeeDto(){
    }

    public EmployeeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeDto(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public EmployeeDto(Employee entity, Set<Department> departments){
        this(entity);
        departments.forEach(dep -> this.departments.add(new DepartmentDto(dep)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }
}