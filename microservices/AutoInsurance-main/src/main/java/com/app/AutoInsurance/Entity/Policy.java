package com.app.AutoInsurance.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendor;
    private String type; // "COMPREHENSIVE" or "THIRD_PARTY"
    private Double odRate; // Only for comprehensive policies
    private String vehicleType; // "CAR" or "BIKE"
}