package com.teste.dp.services;

import com.teste.dp.dto.DepartmentDto;
import com.teste.dp.dto.EmployeeDto;
import com.teste.dp.dto.exceptions.DatabaseException;
import com.teste.dp.dto.exceptions.ResourceNotFoundException;
import com.teste.dp.entities.Department;
import com.teste.dp.entities.Employee;
import com.teste.dp.repositories.DepartmentRepository;
import com.teste.dp.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDto> findAllPaged(Pageable pageable){
        Page<Employee> list = repository.findAll(pageable);
        return list.map(x -> new EmployeeDto(x));
    }

    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id){
        Optional<Employee> obj = repository.findById(id);
        Employee entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new EmployeeDto(entity, entity.getDepartments());
    }

    @Transactional
    public EmployeeDto insert(EmployeeDto dto){
        Employee entity = new Employee();
        copyDtotoEntity(dto, entity);
        entity = repository.save(entity);
        return new EmployeeDto(entity);
    }

    @Transactional
    public EmployeeDto update(Long id, EmployeeDto dto){
        try{
            Employee entity = repository.getReferenceById(id);
            copyDtotoEntity(dto, entity);
            entity = repository.save(entity);
            return new EmployeeDto(entity);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtotoEntity(EmployeeDto dto, Employee entity) {
        entity.setName(dto.getName());

        entity.getDepartments().clear();
        for (DepartmentDto depDto : dto.getDepartments()){
            Department department = departmentRepository.getReferenceById(depDto.getId());
            entity.getDepartments().add(department);
        }
    }

}