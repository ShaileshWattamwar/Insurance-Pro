package com.NewSupplierModule.Service;


import com.NewSupplierModule.DTO.SupplierDetailsDTO;
import com.NewSupplierModule.Entity.SupplierDetails;
import com.NewSupplierModule.Model.SupplierStatus;
import com.NewSupplierModule.Reposatory.SupplierDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SupplierDetailsServiceImplTest {
    @Mock
    private SupplierDetailsRepository repository;

    @InjectMocks
    private SupplierDetailsServiceImpl service;

    private SupplierDetails sampleEntity;
    private SupplierDetailsDTO sampleDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleDTO = new SupplierDetailsDTO();
        sampleDTO.setName("Test Supplier");
        sampleDTO.setRegistrationNumber("REG123");
        sampleDTO.setTaxId("TAX123");
        sampleDTO.setContactEmail("test@example.com");
        sampleDTO.setContactNumber("1234567890");
        sampleDTO.setBankAccount("123456789");
        sampleDTO.setIfscCode("IFSC001");
        sampleDTO.setDocumentsUploaded(true);

        sampleEntity = new SupplierDetails();
        sampleEntity.setId("1");
        sampleEntity.setName("Test Supplier");
        sampleEntity.setRegistrationNumber("REG123");
        sampleEntity.setTaxId("TAX123");
        sampleEntity.setContactEmail("test@example.com");
        sampleEntity.setContactNumber("1234567890");
        sampleEntity.setBankAccount("123456789");
        sampleEntity.setIfscCode("IFSC001");
        sampleEntity.setDocumentsUploaded(true);
        sampleEntity.setStatus(SupplierStatus.REGISTERED);
        sampleEntity.setCreatedAt(LocalDateTime.now());
        sampleEntity.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    void testRegister() {
        when(repository.save(ArgumentMatchers.<SupplierDetails>any())).thenReturn(sampleEntity);

        SupplierDetailsDTO result = service.register(sampleDTO);

        assertNotNull(result);
        assertEquals("Test Supplier", result.getName());
        verify(repository).save(ArgumentMatchers.<SupplierDetails>any());
    }

    @Test
    void testGetById_Found() {
        when(repository.findById("1")).thenReturn(Optional.of(sampleEntity));

        SupplierDetailsDTO result = service.getById(1L);

        assertEquals("Test Supplier", result.getName());
        verify(repository).findById("1");
    }

    @Test
    void testGetById_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(sampleEntity));

        List<SupplierDetailsDTO> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Test Supplier", ((List<?>) result).get(0).getClass());
        verify(repository).findAll();
    }

    @Test
    void testUpdate_Found() {
        when(repository.findById("1")).thenReturn(Optional.of(sampleEntity));
        when(repository.save(ArgumentMatchers.<SupplierDetails>any())).thenReturn(sampleEntity);

        sampleDTO.setName("Updated Supplier");

        SupplierDetailsDTO result = service.update(1L, sampleDTO);

        assertEquals("Updated Supplier", result.getName());
        verify(repository).save(ArgumentMatchers.<SupplierDetails>any());
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.update(1L, sampleDTO));
    }

    @Test
    void testApprove_Success() {
        sampleEntity.setStatus(SupplierStatus.REGISTERED);
        sampleEntity.setDocumentsUploaded(true);
        when(repository.findById("1")).thenReturn(Optional.of(sampleEntity));
        when(repository.save(ArgumentMatchers.<SupplierDetails>any())).thenReturn(sampleEntity);

        SupplierDetailsDTO result = service.approve(1L);

        assertNotNull(result);
        verify(repository).save(sampleEntity);
    }

    @Test
    void testApprove_NotRegistered() {
        sampleEntity.setStatus(SupplierStatus.APPROVED);
        when(repository.findById("1")).thenReturn(Optional.of(sampleEntity));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> service.approve(1L));
        assertEquals("Only REGISTERED suppliers can be approved.", ex.getMessage());
    }

    @Test
    void testApprove_DocumentsNotUploaded() {
        sampleEntity.setStatus(SupplierStatus.REGISTERED);
        sampleEntity.setDocumentsUploaded(false);
        when(repository.findById("1")).thenReturn(Optional.of(sampleEntity));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> service.approve(1L));
        assertEquals("Documents must be uploaded before approval.", ex.getMessage());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById("1");

        service.delete(1L);

        verify(repository).deleteById("1");
    }
}
