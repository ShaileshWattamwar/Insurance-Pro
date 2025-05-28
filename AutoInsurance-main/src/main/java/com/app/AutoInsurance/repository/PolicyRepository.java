package com.app.AutoInsurance.repository;


import com.app.AutoInsurance.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByTypeAndVehicleType(String type, String vehicleType);
}
