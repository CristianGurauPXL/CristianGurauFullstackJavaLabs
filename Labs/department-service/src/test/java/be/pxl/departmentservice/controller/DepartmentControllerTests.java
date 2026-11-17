package be.pxl.departmentservice.controller;

import be.pxl.departmentservice.domain.Department;
import be.pxl.departmentservice.service.DepartmentService;
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

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    @Test
    void add_validDepartment_returns201() throws Exception {
        Department department = new Department();
        department.setName("IT");
        department.setOrganizationId(1L);

        when(departmentService.add(any(Department.class)))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "IT",
                                    "organizationId": 1
                                }
                                """))
                .andExpect(status().isCreated());

        verify(departmentService).add(any(Department.class));
    }

    @Test
    void add_invalidDepartment_returns400AndDoesNotCallService() throws Exception {
        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": ""
                                }
                                """))
                .andExpect(status().isBadRequest());

        verify(departmentService, never()).add(any(Department.class));
    }
}