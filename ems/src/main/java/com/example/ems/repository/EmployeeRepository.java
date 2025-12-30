package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE (:name IS NULL OR e.name LIKE %:name%) " +
            "AND (:departmentId IS NULL OR e.department.id = :departmentId) " +
            "AND (:role IS NULL OR e.role LIKE %:role%)")
    Page<Employee> searchEmployees(@Param("name") String name,
                                   @Param("departmentId") Long departmentId,
                                   @Param("role") String role,
                                   Pageable pageable);
}