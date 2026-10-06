package be.pxl.departmentservice.service;

import be.pxl.departmentservice.domain.Department;
import be.pxl.departmentservice.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void add_shouldSaveDepartment() {
        Department department = new Department();
        department.setName("IT");
        department.setOrganizationId(1L);

        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.add(department);

        ArgumentCaptor<Department> captor =
                ArgumentCaptor.forClass(Department.class);

        verify(departmentRepository).save(captor.capture());

        Department capturedDepartment = captor.getValue();

        assertThat(capturedDepartment.getName()).isEqualTo("IT");
        assertThat(capturedDepartment.getOrganizationId()).isEqualTo(1L);
        assertThat(result).isSameAs(department);
    }
}