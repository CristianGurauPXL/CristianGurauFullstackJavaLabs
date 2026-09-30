package be.pxl.notificationservice.repository;

import be.pxl.notificationservice.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}