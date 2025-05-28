package com.microservices.Sellermodule.Entity;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LifeInsuranceCustomer {
    private String customer_name;
    private int customer_age;
    private String gender;
    private boolean smoker;
    private String occupation;
    private boolean healthIssues;
    private boolean covidaffected;
    private double sum_assured;
    private double policy_term;
    @ElementCollection
    private List<String> riders;
}
