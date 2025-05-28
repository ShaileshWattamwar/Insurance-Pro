package com.microservices.Sellermodule.Services;

import com.microservices.Sellermodule.Config.AutoInsuranceFeign;
import com.microservices.Sellermodule.Config.LifeInsuranceFeign;
import com.microservices.Sellermodule.Dto.AutoInsuranceCustomer;
import com.microservices.Sellermodule.Entity.LifeInsuranceCustomer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class SellerServices {
    private LifeInsuranceFeign lifeInsuranceFeign;
    private AutoInsuranceFeign autoInsuranceFeign;
    private static final Logger logger = LoggerFactory.getLogger(SellerServices.class);


    public List<LifeInsuranceCustomer> getall() {
        return lifeInsuranceFeign.getAllCustomers();
    }

    public void deletecustomer(int id) {
        lifeInsuranceFeign.deleteById(id);
        logger.atInfo().log("Deleted");
    }

    public List<AutoInsuranceCustomer> getallautoinsurance(){
        return autoInsuranceFeign.getall();
    }

    public void deletebyautoid(long id){
        autoInsuranceFeign.deletebyid(id);
        logger.atInfo().log("Autoinsurance Customer deleted");
    }
}
