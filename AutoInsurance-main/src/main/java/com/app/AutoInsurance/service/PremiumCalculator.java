package com.app.AutoInsurance.service;
import com.app.AutoInsurance.Entity.VehicleModel;
import org.springframework.stereotype.Component;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@Component
public class PremiumCalculator {
    private static final Map<String, Map<String, Double>> TP_RATES = new HashMap<>();

    static {
        // Third-party rates for cars (as per IRDAI)
        Map<String, Double> carRates = new HashMap<>();
        carRates.put("<=1000", 2094.0);
        carRates.put("1000-1500", 3416.0);
        carRates.put(">1500", 7897.0);

        // Third-party rates for bikes
        Map<String, Double> bikeRates = new HashMap<>();
        bikeRates.put("<=75", 538.0);
        bikeRates.put("75-150", 714.0);
        bikeRates.put("150-350", 1366.0);
        bikeRates.put(">350", 2804.0);

        TP_RATES.put("CAR", carRates);
        TP_RATES.put("BIKE", bikeRates);
    }

    public double calculateTpPremium(VehicleModel vehicle) {
        String vehicleType = vehicle.getVehicleType();
        int engineCC = vehicle.getEngineCC();
        Map<String, Double> rates = TP_RATES.get(vehicleType);

        if ("CAR".equals(vehicleType)) {
            if (engineCC <= 1000) return rates.get("<=1000");
            else if (engineCC <= 1500) return rates.get("1000-1500");
            else return rates.get(">1500");
        } else {
            if (engineCC <= 75) return rates.get("<=75");
            else if (engineCC <= 150) return rates.get("75-150");
            else if (engineCC <= 350) return rates.get("150-350");
            else return rates.get(">350");
        }
    }

    public double calculateDepreciation(int registrationYear) {
        int age = Year.now().getValue() - registrationYear;
        return switch (age) {
            case 0 -> 0.15;
            case 1 -> 0.20;
            case 2 -> 0.30;
            case 3 -> 0.40;
            case 4 -> 0.50;
            default -> 0.60;
        };
    }
}