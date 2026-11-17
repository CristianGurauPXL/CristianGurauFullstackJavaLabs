package be.pxl.departmentservice.repository;

import be.pxl.departmentservice.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByOrganizationId(Long organizationId);

    // No implementation yet, problem for Week 4
    // List<Department> findByOrganizationIdWithEmployess(Long organizationId, List<Employees>);
}
