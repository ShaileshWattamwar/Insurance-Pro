package com.NewSupplierModule.Controller;

import com.NewSupplierModule.DTO.SupplierDetailsDTO;
import com.NewSupplierModule.ServiceInterface.SupplierDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javax.swing.UIManager.get;
import static org.apache.hc.core5.http.io.support.ClassicRequestBuilder.delete;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SupplierDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SupplierDetailsService supplierService; // This will now be injected properly

    @Autowired
    private ObjectMapper objectMapper;

    private SupplierDetailsDTO sampleSupplier;

    @BeforeEach
    void setUp() {
        sampleSupplier = new SupplierDetailsDTO();
        sampleSupplier.setId(1L);
        sampleSupplier.setName("Test Supplier");
        sampleSupplier.setEmail("test@example.com");
    }

    @Test
    void testRegisterSupplier() throws Exception {
        Mockito.when(supplierService.register(any())).thenReturn(sampleSupplier);

        mockMvc.perform((RequestBuilder) post("/api/suppliers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(objectMapper.writeValueAsString(sampleSupplier))))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("Test Supplier"));
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public SupplierDetailsService supplierDetailsService() {
            return Mockito.mock(SupplierDetailsService.class);
        }
    }

    @Test
    void testGetSupplierById() throws Exception {
        Mockito.when(supplierService.getById(1L)).thenReturn(sampleSupplier);

        mockMvc.perform((RequestBuilder) get("/api/suppliers/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllSuppliers() throws Exception {
        Collections Collections = null;
        Mockito.when(supplierService.getAll()).thenReturn(Collections.singletonList(sampleSupplier));

        mockMvc.perform((RequestBuilder) get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()").value(1));
    }

    @Test
    void testUpdateSupplier() throws Exception {
        sampleSupplier.setName("Updated Supplier");
        Mockito.when(supplierService.update(eq(1L), any())).thenReturn(sampleSupplier);

        mockMvc.perform((RequestBuilder) put("/api/suppliers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.valueOf(objectMapper.writeValueAsString(sampleSupplier))))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value("Updated Supplier"));
    }

    @Test
    void testDeleteSupplier() throws Exception {
        mockMvc.perform((RequestBuilder) delete("/api/suppliers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testApproveSupplier() throws Exception {
        sampleSupplier.setApproved(true);
        Mockito.when(supplierService.approve(1L)).thenReturn(sampleSupplier);

        mockMvc.perform((RequestBuilder) put("/api/suppliers/1/approve"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.approved").value(true));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public SupplierDetailsService supplierDetailsService() {
            return Mockito.mock(SupplierDetailsService.class);
        }
    }
}
