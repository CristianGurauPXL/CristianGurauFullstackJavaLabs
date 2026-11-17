package be.pxl.organizationservice.controller;

import be.pxl.organizationservice.domain.Organization;
import be.pxl.organizationservice.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrganizationService organizationService;

    @Test
    void findById_existingOrganization_returns200() throws Exception {
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("PXL");

        when(organizationService.findById(1L))
                .thenReturn(organization);

        mockMvc.perform(get("/organizations/1"))
                .andExpect(status().isOk());

        verify(organizationService).findById(1L);
    }

    @Test
    void findById_nonExistingOrganization_returns404() throws Exception {
        when(organizationService.findById(1L))
                .thenReturn(null);

        mockMvc.perform(get("/organizations/1"))
                .andExpect(status().isNotFound());

        verify(organizationService).findById(1L);
    }
}