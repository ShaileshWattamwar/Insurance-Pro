package com.microservices.Life_Insurance.Service.Impl;

import com.microservices.Life_Insurance.Config.ConvertedClass;
import com.microservices.Life_Insurance.Dto.RequestDto;
import com.microservices.Life_Insurance.Dto.ResponseDto;
import com.microservices.Life_Insurance.Entity.LifeInsuranceCustomer;
import com.microservices.Life_Insurance.Repository.Lifeinsurancerepo;
import com.microservices.Life_Insurance.Service.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LifeInsuranceServices implements Services {
    private Lifeinsurancerepo lifeinsurancerepo;
    private ConvertedClass modelmapper;


    @Override
    public ResponseDto calculations(RequestDto requestDto) {
        LifeInsuranceCustomer customer = modelmapper.dtotocustomer(requestDto);
        lifeinsurancerepo.save(customer);

        double base_premium = (requestDto.getSum_assured() * UtilClass.getBaserate(requestDto.getCustomer_age(), requestDto.getGender())) / (requestDto.getPolicy_term() * 1000);
        double risk_premium = base_premium * UtilClass.getFactorPremium(requestDto);
        double rider_premium = UtilClass.getRiderPremium(requestDto.getRiders());
        double total_premium = (base_premium) + (rider_premium) + (risk_premium);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setBase_premium(base_premium);
        responseDto.setRisk_premium(risk_premium);
        responseDto.setRider_premium(rider_premium);
        responseDto.setTotal_premium(total_premium);

        return responseDto;
    }

    @Override
    public void deleterequest(int id) {
        lifeinsurancerepo.deleteById(id);
    }

    @Override
    public List<LifeInsuranceCustomer> getallcustomers() {
        List<LifeInsuranceCustomer> customer = lifeinsurancerepo.findAll();
        return customer;
    }
}
