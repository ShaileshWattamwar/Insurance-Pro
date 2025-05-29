package com.microservices.Sellermodule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoInsuranceCustomer {
    private String vendor;
    private String type; // "COMPREHENSIVE" or "THIRD_PARTY"
    private Double odRate; // Only for comprehensive policies
    private String vehicleType;
}
