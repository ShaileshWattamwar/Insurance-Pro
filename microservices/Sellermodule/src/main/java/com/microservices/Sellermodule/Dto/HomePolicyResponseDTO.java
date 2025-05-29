package com.microservices.Sellermodule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePolicyResponseDTO {
    private String policyHolderName;
    private String city;
    private Double buildingValue;
    private Double contentValue;
    private Double carpetArea;
    private String buildingType;
    private Boolean earthquakeCover;
    private Boolean floodCover;
}
