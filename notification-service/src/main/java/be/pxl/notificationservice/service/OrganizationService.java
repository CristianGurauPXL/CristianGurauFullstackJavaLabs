package be.pxl.notificationservice.service;

import be.pxl.notificationservice.domain.Organization;
import be.pxl.notificationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization add(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public List<Organization> findWithDepartments(Long id) {
        // No implementation yet, problem for later, changes will be made
        return List.of();
    }

    public List<Organization> findWithEmployees(Long id) {
        // No implementation yet, problem for later, changes will be made
        return List.of();
    }

    public List<Organization> findWithDepartmentsAndEmployees(Long id) {
        // No implementation yet, problem for later, changes will be made
        return List.of();
    }
}