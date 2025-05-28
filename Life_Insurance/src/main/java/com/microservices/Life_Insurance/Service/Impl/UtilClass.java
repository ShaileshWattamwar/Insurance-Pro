package com.microservices.Life_Insurance.Service.Impl;

import com.microservices.Life_Insurance.Dto.RequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilClass {
    public static double getBaserate(int age, String gender) {
        if (age < 25) return gender.equalsIgnoreCase("Female") ? 1.10 : 1.20;
        if (age < 30) return gender.equalsIgnoreCase("Female") ? 1.35 : 1.50;
        if (age < 35) return gender.equalsIgnoreCase("Female") ? 1.80 : 2.0;
        if (age < 40) return gender.equalsIgnoreCase("Female") ? 2.50 : 2.80;
        if (age < 45) return gender.equalsIgnoreCase("Female") ? 3.60 : 4.0;
        if (age < 50) return gender.equalsIgnoreCase("Female") ? 5.40 : 6.0;
        if (age < 55) return gender.equalsIgnoreCase("Female") ? 8.0 : 9.0;
        return gender.equalsIgnoreCase("Female") ? 10.80 : 12.0;
    }

    public static double getFactorPremium(RequestDto customerDto) {
        double factorpremium = 0.0;
        if (customerDto.isSmoker())  factorpremium += 0.25;
        if (customerDto.isHealthIssues())  factorpremium += 0.20;
        if (customerDto.getOccupation().equalsIgnoreCase("Construction"))  factorpremium += 0.15;
        if (customerDto.isCovidaffected()) factorpremium += 0.10;
        return factorpremium;
    }

    public static double getRiderPremium(List<String> riders) {
        return riders.stream()
                .mapToDouble(r -> switch (r.toLowerCase()) {
                    case "critical_illness" -> 500;
                    case "accidental_death" -> 300;
                    case "covid_cover" -> 400;
                    default -> 0;
                }).sum();
    }

}
