package com.NewSupplierModule.Service;

import com.NewSupplierModule.DTO.SupplierDetailsDTO;
import com.NewSupplierModule.Entity.SupplierDetails;
import com.NewSupplierModule.Model.SupplierStatus;
import com.NewSupplierModule.Reposatory.SupplierDetailsRepository;
import com.NewSupplierModule.ServiceInterface.SupplierDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierDetailsServiceImpl implements SupplierDetailsService {
    private final SupplierDetailsRepository repository;

    @Autowired
    public SupplierDetailsServiceImpl(SupplierDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierDetailsDTO register(SupplierDetailsDTO dto) {
        SupplierDetails entity = toEntity(dto);
        entity.setStatus(SupplierStatus.REGISTERED);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        SupplierDetails saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    public SupplierDetailsDTO getById(Long id) {
        return repository.findById(String.valueOf(id))
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public List<SupplierDetailsDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDetailsDTO update(Long id, SupplierDetailsDTO dto) {
        SupplierDetails supplier = (SupplierDetails) repository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setName(dto.getName());
        supplier.setRegistrationNumber(dto.getRegistrationNumber());
        supplier.setTaxId(dto.getTaxId());
        supplier.setContactEmail(dto.getContactEmail());
        supplier.setContactNumber(dto.getContactNumber());
        supplier.setBankAccount(dto.getBankAccount());
        supplier.setIfscCode(dto.getIfscCode());
        supplier.setDocumentsUploaded(dto.isDocumentsUploaded());
        supplier.setUpdatedAt(LocalDateTime.now());

        SupplierDetails updated = repository.save(supplier);
        return toDTO(updated);
    }
    @Override
    public SupplierDetailsDTO approve(Long id) {
        SupplierDetails supplier = (SupplierDetails) repository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (supplier.getStatus() != SupplierStatus.REGISTERED) {
            throw new IllegalStateException("Only REGISTERED suppliers can be approved.");
        }

        if (!supplier.isDocumentsUploaded()) {
            throw new IllegalStateException("Documents must be uploaded before approval.");
        }

        supplier.setStatus(SupplierStatus.APPROVED);
        supplier.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(supplier));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(String.valueOf(id));
    }



    private SupplierDetails toEntity(SupplierDetailsDTO dto) {
        SupplierDetails s = new SupplierDetails();
        s.setName(dto.getName());
        s.setRegistrationNumber(dto.getRegistrationNumber());
        s.setTaxId(dto.getTaxId());
        s.setContactEmail(dto.getContactEmail());
        s.setContactNumber(dto.getContactNumber());
        s.setBankAccount(dto.getBankAccount());
        s.setIfscCode(dto.getIfscCode());
        s.setDocumentsUploaded(dto.isDocumentsUploaded());
        return s;
    }

    private SupplierDetailsDTO toDTO(SupplierDetails s) {
        SupplierDetailsDTO dto = new SupplierDetailsDTO();
        dto.setName(s.getName());
        dto.setRegistrationNumber(s.getRegistrationNumber());
        dto.setTaxId(s.getTaxId());
        dto.setContactEmail(s.getContactEmail());
        dto.setContactNumber(s.getContactNumber());
        dto.setBankAccount(s.getBankAccount());
        dto.setIfscCode(s.getIfscCode());
        dto.setDocumentsUploaded(s.isDocumentsUploaded());
        return dto;
    }
}
