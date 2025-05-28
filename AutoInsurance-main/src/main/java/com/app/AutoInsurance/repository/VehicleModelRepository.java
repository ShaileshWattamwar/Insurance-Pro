package com.app.AutoInsurance.repository;

import com.app.AutoInsurance.Entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    Optional<VehicleModel> findByModel(String model);
}