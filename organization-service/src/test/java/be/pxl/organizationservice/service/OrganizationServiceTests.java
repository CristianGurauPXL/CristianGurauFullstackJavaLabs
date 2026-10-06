package be.pxl.organizationservice.service;

import be.pxl.organizationservice.domain.Organization;
import be.pxl.organizationservice.repository.OrganizationRepository;
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
class OrganizationServiceTests {

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @Test
    void add_shouldSaveOrganization() {
        Organization organization = new Organization();
        organization.setName("PXL");

        when(organizationRepository.save(organization)).thenReturn(organization);

        Organization result = organizationService.add(organization);

        ArgumentCaptor<Organization> captor =
                ArgumentCaptor.forClass(Organization.class);

        verify(organizationRepository).save(captor.capture());

        Organization capturedOrganization = captor.getValue();

        assertThat(capturedOrganization.getName()).isEqualTo("PXL");
        assertThat(result).isSameAs(organization);
    }
}