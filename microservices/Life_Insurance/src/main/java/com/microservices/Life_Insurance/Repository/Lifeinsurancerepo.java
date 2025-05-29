package com.microservices.Life_Insurance.Repository;

import com.microservices.Life_Insurance.Entity.LifeInsuranceCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lifeinsurancerepo extends JpaRepository<LifeInsuranceCustomer,Integer> {
}
