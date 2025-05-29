package com.app.AutoInsurance.config;

import com.app.AutoInsurance.Entity.Policy;
import com.app.AutoInsurance.Entity.VehicleModel;
import com.app.AutoInsurance.repository.PolicyRepository;
import com.app.AutoInsurance.repository.VehicleModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final VehicleModelRepository vehicleModelRepo;
    private final PolicyRepository policyRepo;

    @PostConstruct
    public void seedData() {

        VehicleModel i20 = new VehicleModel();
        i20.setBrand("Hyundai");
        i20.setModel("i20");
        i20.setVehicleType("CAR");
        i20.setEngineCC(1197);
        i20.setExShowroomPrice(700000);
        vehicleModelRepo.save(i20);


        Policy hdfcComprehensive = new Policy();
        hdfcComprehensive.setVendor("HDFC Ergo");
        hdfcComprehensive.setType("COMPREHENSIVE");
        hdfcComprehensive.setOdRate(2.5);
        hdfcComprehensive.setVehicleType("CAR");
        policyRepo.save(hdfcComprehensive);

        Policy iciciThirdParty = new Policy();
        iciciThirdParty.setVendor("ICICI Lombard");
        iciciThirdParty.setType("THIRD_PARTY");
        iciciThirdParty.setVehicleType("CAR");
        policyRepo.save(iciciThirdParty);
    }
}