package com.microservices.Sellermodule.Controller;

import com.microservices.Sellermodule.Dto.AutoInsuranceCustomer;
import com.microservices.Sellermodule.Dto.HomePolicyResponseDTO;
import com.microservices.Sellermodule.Entity.LifeInsuranceCustomer;
import com.microservices.Sellermodule.Services.SellerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Types")
@AllArgsConstructor
public class Sellercontroller {
    private SellerServices services;

    @GetMapping("/LifeInsurance/all")
    public ResponseEntity<List<LifeInsuranceCustomer>> getall() {
        return ResponseEntity.ok(services.getall());
    }

    @DeleteMapping("/LifeInsurance/{id}")
    public void delete(@PathVariable int id) {
        services.deletecustomer(id);
    }

    @GetMapping("/AutoInsurance/all")
    public ResponseEntity<List<AutoInsuranceCustomer>> getallauto() {
        return ResponseEntity.ok(services.getallautoinsurance());
    }

    @DeleteMapping("/AutoInsurance/{id}")
    public void deleteautoid(@PathVariable long id) {
        services.deletebyautoid(id);
    }

    @GetMapping("/HomeInsurance/all")
    public ResponseEntity<List<HomePolicyResponseDTO>> getallhome() {
        return ResponseEntity.ok(services.getallhomecustomers());
    }

    @DeleteMapping("/HomeInsurance/{id}")
    public String deletehomeid(@PathVariable long id) {
        return services.deletehomecustomerbyid(id);
    }


}
