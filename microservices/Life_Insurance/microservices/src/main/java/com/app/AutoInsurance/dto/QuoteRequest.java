package com.app.AutoInsurance.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuoteRequest {
    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;

    @Min(value = 2000, message = "Registration year must be >= 2000")
    private int registrationYear;

    @NotBlank(message = "Policy type is required")
    private String policyType; // "COMPREHENSIVE" or "THIRD_PARTY"
}
