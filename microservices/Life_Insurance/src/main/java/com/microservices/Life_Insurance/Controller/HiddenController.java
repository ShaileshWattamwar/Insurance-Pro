package com.microservices.Life_Insurance.Controller;

import com.microservices.Life_Insurance.Entity.LifeInsuranceCustomer;
import com.microservices.Life_Insurance.Service.Impl.LifeInsuranceServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Hidden")
@AllArgsConstructor
public class HiddenController {
    private LifeInsuranceServices lifeInsuranceServices;

    @GetMapping("/Lifeinsurance/all")
    public ResponseEntity<List<LifeInsuranceCustomer>> getall() {
        return ResponseEntity.ok(lifeInsuranceServices.getallcustomers());
    }

    @DeleteMapping("/{id}")
    public void deletebyid(@PathVariable int id) {
        lifeInsuranceServices.deleterequest(id);
    }
}
