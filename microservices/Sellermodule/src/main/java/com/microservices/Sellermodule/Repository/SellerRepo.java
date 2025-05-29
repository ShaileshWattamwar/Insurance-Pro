package com.microservices.Sellermodule.Repository;

import com.microservices.Sellermodule.Entity.InsuranceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<InsuranceTypes,Integer> {
}
