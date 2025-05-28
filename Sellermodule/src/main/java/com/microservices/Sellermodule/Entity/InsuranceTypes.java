package com.microservices.Sellermodule.Entity;

import com.microservices.Sellermodule.Dto.AutoInsuranceCustomer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Transient
    private List<LifeInsuranceCustomer> lifeInsuranceCustomerList;
    @Transient
    private List<AutoInsuranceCustomer> autoInsuranceslist;
//    @Transient
//    private List<HomeInsuranceCustomer> homeInsuranceCustomerList;
}
