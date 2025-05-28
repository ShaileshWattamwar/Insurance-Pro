package com.microservices.Life_Insurance.Config;

import com.microservices.Life_Insurance.Dto.RequestDto;
import com.microservices.Life_Insurance.Entity.LifeInsuranceCustomer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConvertedClass {
    private ModelMapper modelMapper;

    public LifeInsuranceCustomer dtotocustomer(RequestDto requestDto){
        return modelMapper.map(requestDto, LifeInsuranceCustomer.class);
    }

    public RequestDto customertodto(LifeInsuranceCustomer customer){
        return modelMapper.map(customer, RequestDto.class);
    }

}
