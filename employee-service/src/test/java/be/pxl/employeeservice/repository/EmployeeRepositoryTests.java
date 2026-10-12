
package be.pxl.employeeservice.repository;

import be.pxl.employeeservice.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class EmployeeRepositoryTests {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4");

    @Autowired
    private EmployeeRepository repository;

    @Test
    void savesAndFindsEmployee() {
        Employee employee = Employee.builder()
                .firstName("Jan")
                .lastName("Janssens")
                .email("jan@example.com")
                .departmentId(1L)
                .organizationId(1L)
                .build();

        Employee saved = repository.save(employee);
        Employee found = repository.findById(saved.getId()).orElseThrow();

        assertNotNull(saved.getId());
        assertEquals("Jan", found.getFirstName());
        assertEquals("Janssens", found.getLastName());
        assertEquals("jan@example.com", found.getEmail());
        assertEquals(1L, found.getDepartmentId());
        assertEquals(1L, found.getOrganizationId());
    }

    @Test
    void findsEmployeesByDepartmentId() {
        Employee employee = Employee.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@example.com")
                .departmentId(2L)
                .organizationId(1L)
                .build();

        repository.save(employee);

        List<Employee> employees = repository.findByDepartmentId(2L);

        assertFalse(employees.isEmpty());
        assertTrue(employees.stream().allMatch(e -> e.getDepartmentId().equals(2L)));
    }
}
