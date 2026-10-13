package be.pxl.employeeservice.service;

import be.pxl.employeeservice.client.NotificationClient;
import be.pxl.employeeservice.client.dto.NotificationRequest;
import be.pxl.employeeservice.domain.Employee;
import be.pxl.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final NotificationClient notificationClient;

    public Employee add(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        notificationClient.sendNotification(new NotificationRequest());
        return savedEmployee;
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> findByOrganization(Long organizationId) {
        return employeeRepository.findByOrganizationId(organizationId);
    }
}