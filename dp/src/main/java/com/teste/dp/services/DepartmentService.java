package com.teste.dp.services;

import com.teste.dp.dto.DepartmentDto;
import com.teste.dp.dto.exceptions.DatabaseException;
import com.teste.dp.dto.exceptions.ResourceNotFoundException;
import com.teste.dp.entities.Department;
import com.teste.dp.repositories.DepartmentRepository;
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
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Transactional(readOnly = true)
    public Page<DepartmentDto> findAllPaged(Pageable pageable){
        Page<Department> list = repository.findAll(pageable);
        return list.map(x -> new DepartmentDto(x));
    }

    @Transactional(readOnly = true)
    public DepartmentDto findById(Long id){
        Optional<Department> obj = repository.findById(id);
        Department entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
        return new DepartmentDto(entity);
    }

    @Transactional
    public DepartmentDto insert(DepartmentDto dto){
        Department entity = new Department();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new DepartmentDto(entity);
    }

    @Transactional
    public DepartmentDto update(Long id, DepartmentDto dto){
        try {
            Department entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new DepartmentDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource not found.");
        }
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity fail.");
        }
    }

}
