package com.microservices.Sellermodule.Config;

import com.microservices.Sellermodule.Dto.HomePolicyResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("homeinsurance")
public interface HomeInsuranceFeign {
    @GetMapping("/Hidden/homepolicy")
    public List<HomePolicyResponseDTO> getall();

    @DeleteMapping("/Hidden/homepolicy/{id}")
    public String deletePolicy(@PathVariable long id);

}
