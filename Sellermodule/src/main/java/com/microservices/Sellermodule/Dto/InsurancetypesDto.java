package com.microservices.Sellermodule.Dto;

import com.microservices.Sellermodule.Entity.LifeInsuranceCustomer;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsurancetypesDto {
    @Transient
    private List<LifeInsuranceCustomer> lifeInsuranceCustomerList;
}
