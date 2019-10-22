package com.springboot.h2.service;

import com.springboot.h2.entity.Department;
import com.springboot.h2.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if(department == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Department Not Found1=");
        } else {
            return department;
        }
    }
    public Department getDepartmentByName(String name) {
        Department department = departmentRepository.findByName(name)
                .orElse(null);
        if(department == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Department Not Found");
        } else {
            return department;
        }
    }
    public Department createDepartment(Department department) {
        department.setCurrentTime();
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Department savedDepaterment = departmentRepository.findById(id).orElse(null);

        if(savedDepaterment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Department Not Found");
        } else {
            savedDepaterment.update(department);
            return departmentRepository.save(savedDepaterment);
        }

    }

    public void deleteDepartment(Long id) {
        if(departmentRepository.findById(id).isPresent()){
            departmentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Department Not Found");
        }
    }
}
