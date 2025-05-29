package com.microservices.Sellermodule.Config;

import com.microservices.Sellermodule.Entity.LifeInsuranceCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("lifeinsurance")
public interface LifeInsuranceFeign {
    @GetMapping("/Hidden/Lifeinsurance/all")
    List<LifeInsuranceCustomer> getAllCustomers();

    @DeleteMapping("/Hidden/{id}")
    void deleteById(@PathVariable int id);
}
