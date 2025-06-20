package com.insurance.claim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurance.claim.entities.PolicyHolder;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, String> {

}
