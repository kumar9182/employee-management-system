package com.example.ems.service;

import com.example.ems.dto.DepartmentDTO;
import com.example.ems.entity.Department;
import com.example.ems.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        Department saved = departmentRepository.save(department);
        dto.setId(saved.getId());
        return dto;
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dept -> {
                    DepartmentDTO dto = new DepartmentDTO();
                    dto.setId(dept.getId());
                    dto.setName(dept.getName());
                    return dto;
                }).collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(dto.getName());
        Department updated = departmentRepository.save(department);
        dto.setId(updated.getId());
        return dto;
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}