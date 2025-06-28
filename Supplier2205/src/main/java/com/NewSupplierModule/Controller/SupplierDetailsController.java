package com.NewSupplierModule.Controller;

import com.NewSupplierModule.DTO.SupplierDetailsDTO;
import com.NewSupplierModule.ServiceInterface.SupplierDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierDetailsController {
    private final SupplierDetailsService supplierService;

    @Autowired
    public SupplierDetailsController(SupplierDetailsService supplierService) {
        this.supplierService = supplierService;
    }


    @PostMapping("/register")
    public ResponseEntity<SupplierDetailsDTO> registerSupplier(@RequestBody SupplierDetailsDTO dto) {
        SupplierDetailsDTO savedSupplier = supplierService.register(dto);
        return ResponseEntity.ok(savedSupplier);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SupplierDetailsDTO> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }


    @GetMapping
    public ResponseEntity<List<SupplierDetailsDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<SupplierDetailsDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDetailsDTO dto) {
        return ResponseEntity.ok(supplierService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/approve")
    public ResponseEntity<SupplierDetailsDTO> approveSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.approve(id));
    }
}
