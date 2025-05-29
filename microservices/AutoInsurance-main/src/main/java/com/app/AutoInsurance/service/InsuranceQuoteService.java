package com.app.AutoInsurance.service;
import com.app.AutoInsurance.Entity.Policy;
import com.app.AutoInsurance.Entity.VehicleModel;
import com.app.AutoInsurance.repository.PolicyRepository;
import com.app.AutoInsurance.repository.VehicleModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InsuranceQuoteService {
    private final VehicleModelRepository vehicleModelRepo;
    private final PolicyRepository policyRepo;
    private final PremiumCalculator premiumCalculator;

    public Map<String, Object> generateQuote(String vehicleModel, int registrationYear, String policyType) {
        VehicleModel vehicle = vehicleModelRepo.findByModel(vehicleModel)
                .orElseThrow(() -> new RuntimeException("Vehicle model not found"));

        List<Policy> policies = policyRepo.findByTypeAndVehicleType(policyType, vehicle.getVehicleType());
        List<Map<String, Object>> vendorQuotes = new ArrayList<>();

        for (Policy policy : policies) {
            Map<String, Object> quote = new HashMap<>();
            double tpPremium = premiumCalculator.calculateTpPremium(vehicle);
            double odPremium = 0.0;
            double idv = 0.0;

            if ("COMPREHENSIVE".equals(policyType)) {
                double depreciation = premiumCalculator.calculateDepreciation(registrationYear);
                idv = vehicle.getExShowroomPrice() * (1 - depreciation);
                odPremium = idv * (policy.getOdRate() / 100);
            }

            double addOns = 1000;
            double subtotal = odPremium + tpPremium + addOns;
            double gst =Math.round(subtotal * 0.18 * 100.0) / 100.0;
            double totalPremium = Math.round((subtotal + gst) * 100.0) / 100.0;

            quote.put("vendor", policy.getVendor());
            quote.put("type", policyType);
            quote.put("premium", totalPremium);
            quote.put("components", Map.of(
                    "odPremium", odPremium,
                    "tpPremium", tpPremium,
                    "addOns", addOns,
                    "gst", gst
            ));
            quote.put("idv", idv);
            vendorQuotes.add(quote);
        }
    //check
        return Map.of(
                "quoteId", UUID.randomUUID().toString(),
                "registrationYear", registrationYear,
                "vehicleModel", vehicleModel,
                "vendors", vendorQuotes
        );
    }

    public List<Policy> getall(){
        return policyRepo.findAll();
    }

    public void deleteById(long id){
        policyRepo.deleteById(id);
    }
}