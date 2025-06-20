package com.insurance.claim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim.entities.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long>{

}
