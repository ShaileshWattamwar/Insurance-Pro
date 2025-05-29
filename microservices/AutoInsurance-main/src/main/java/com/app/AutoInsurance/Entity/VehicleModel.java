package com.app.AutoInsurance.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String vehicleType; // "CAR" or "BIKE"
    private int engineCC;
    private double exShowroomPrice;
}