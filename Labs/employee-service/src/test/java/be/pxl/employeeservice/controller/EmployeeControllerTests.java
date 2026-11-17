package be.pxl.employeeservice.controller;

import be.pxl.employeeservice.domain.Employee;
import be.pxl.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void add_validEmployee_returns201() throws Exception {
        Employee employee = Employee.builder()
                .firstName("Jan")
                .lastName("Janssens")
                .email("jan@example.com")
                .departmentId(1L)
                .organizationId(1L)
                .build();

        when(employeeService.add(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Jan",
                                    "lastName": "Janssens",
                                    "email": "jan@example.com",
                                    "departmentId": 1,
                                    "organizationId": 1
                                }
                                """))
                .andExpect(status().isCreated());

        verify(employeeService).add(any(Employee.class));
    }

    @Test
    void add_invalidEmployee_returns400AndDoesNotCallService() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Jan",
                                    "lastName": "Janssens",
                                    "email": "not-an-email",
                                    "departmentId": 1,
                                    "organizationId": 1
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(employeeService, never()).add(any(Employee.class));
    }
}