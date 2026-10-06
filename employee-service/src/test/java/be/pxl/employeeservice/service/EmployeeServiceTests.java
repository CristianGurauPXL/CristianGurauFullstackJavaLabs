package be.pxl.employeeservice.service;

import be.pxl.employeeservice.domain.Employee;
import be.pxl.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void add_shouldSaveEmployee() {
        Employee employee = Employee.builder()
                .firstName("Jan")
                .lastName("Janssens")
                .email("jan@example.com")
                .departmentId(1L)
                .organizationId(1L)
                .build();

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.add(employee);

        ArgumentCaptor<Employee> captor =
                ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(captor.capture());

        Employee capturedEmployee = captor.getValue();

        assertThat(capturedEmployee.getFirstName()).isEqualTo("Jan");
        assertThat(capturedEmployee.getLastName()).isEqualTo("Janssens");
        assertThat(capturedEmployee.getEmail()).isEqualTo("jan@example.com");
        assertThat(capturedEmployee.getDepartmentId()).isEqualTo(1L);
        assertThat(capturedEmployee.getOrganizationId()).isEqualTo(1L);

        assertThat(result).isSameAs(employee);
    }
}