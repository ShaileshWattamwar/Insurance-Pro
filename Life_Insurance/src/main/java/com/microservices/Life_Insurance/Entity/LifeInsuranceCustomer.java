package com.microservices.Life_Insurance.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LifeInsuranceCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int customer_id;
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
