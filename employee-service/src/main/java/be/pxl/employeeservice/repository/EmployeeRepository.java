package be.pxl.employeeservice.repository;

import be.pxl.employeeservice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByOrganizationId(Long organizationId);
}