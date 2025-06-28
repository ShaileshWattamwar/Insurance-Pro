package com.NewSupplierModule.Reposatory;

import com.NewSupplierModule.Entity.SupplierDetails;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.function.Supplier;

public interface SupplierDetailsRepository extends MongoRepository<SupplierDetails, String> {
}
