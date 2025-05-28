package com.microservices.Life_Insurance.Service;

import com.microservices.Life_Insurance.Dto.RequestDto;
import com.microservices.Life_Insurance.Dto.ResponseDto;
import com.microservices.Life_Insurance.Entity.LifeInsuranceCustomer;

import java.util.List;

public interface Services {
    public ResponseDto calculations(RequestDto requestDto);
    public void deleterequest(int id);
    public List<LifeInsuranceCustomer> getallcustomers();
}
