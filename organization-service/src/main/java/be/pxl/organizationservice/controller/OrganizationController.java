package be.pxl.organizationservice.controller;

import be.pxl.organizationservice.domain.Organization;
import be.pxl.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<Organization> findById(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);

        if (organization == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(organization);
    }

    @GetMapping("/{id}/with-departments")
    public List<Organization> findWithDepartments(@PathVariable Long id) {
        // No implementation yet, problem for later, changes will be made
        return organizationService.findWithDepartments(id);
    }

    @GetMapping("/{id}/with-employees")
    public List<Organization> findWithEmployees(@PathVariable Long id) {
        // No implementation yet, problem for later, changes will be made
        return organizationService.findWithEmployees(id);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public List<Organization> findWithDepartmentsAndEmployees(@PathVariable Long id) {
        // No implementation yet, problem for later, changes will be made
        return organizationService.findWithDepartmentsAndEmployees(id);
    }
}