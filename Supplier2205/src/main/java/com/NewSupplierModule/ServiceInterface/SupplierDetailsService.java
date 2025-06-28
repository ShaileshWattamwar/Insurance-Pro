package com.NewSupplierModule.ServiceInterface;

import com.NewSupplierModule.DTO.SupplierDetailsDTO;

import java.util.List;

public interface SupplierDetailsService {
    SupplierDetailsDTO register(SupplierDetailsDTO dto);
    SupplierDetailsDTO getById(Long id);
    List<SupplierDetailsDTO> getAll();
    SupplierDetailsDTO update(Long id, SupplierDetailsDTO dto);
    void delete(Long id);
    SupplierDetailsDTO approve(Long id);
}
