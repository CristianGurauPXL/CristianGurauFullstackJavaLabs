package be.pxl.departmentservice.controller;

import be.pxl.departmentservice.domain.Department;
import be.pxl.departmentservice.service.DepartmentService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> add(@RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.add(department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        Department department = departmentService.findById(id);

        if (department == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(department);
    }

    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/organization/{organizationId}")
    public List<Department> findByOrganization(@PathVariable Long organizationId) {
        return departmentService.findByOrganization(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationWithEmployees(@PathVariable Long organizationId) {
        // No implementation yet, problem for Week 4
        return departmentService.findByOrganizationWithEmployees(organizationId); // returns []
    }
}