package com.microservices.Sellermodule.Config;

import com.microservices.Sellermodule.Dto.AutoInsuranceCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("autoinsurance")
public interface AutoInsuranceFeign {
    @GetMapping("/Hidden/AutoInsurance/all")
    public List<AutoInsuranceCustomer> getall();

    @DeleteMapping("/Hidden/AutoInsurance/{id}")
    public void deletebyid(@PathVariable long id);
}
