package com.microservices.Life_Insurance.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private double base_premium;
    private double risk_premium;
    private double rider_premium;
    private double total_premium;
}
