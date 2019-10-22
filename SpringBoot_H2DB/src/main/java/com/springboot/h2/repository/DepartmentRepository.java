package com.springboot.h2.repository;

import com.springboot.h2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends
        JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
 }
