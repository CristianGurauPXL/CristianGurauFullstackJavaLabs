package be.pxl.departmentservice.service;

import be.pxl.departmentservice.domain.Department;
import be.pxl.departmentservice.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public List<Department> findByOrganization(Long organizationId) {
        return departmentRepository.findByOrganizationId(organizationId);
    }

    public List<Department> findByOrganizationWithEmployees(Long organizationId) {
        // No implementation yet, problem for Week 4
        return List.of();
    }
}