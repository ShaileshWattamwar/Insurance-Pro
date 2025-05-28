package com.microservices.Life_Insurance.Controller;

import com.microservices.Life_Insurance.Dto.RequestDto;
import com.microservices.Life_Insurance.Dto.ResponseDto;
import com.microservices.Life_Insurance.Service.Impl.LifeInsuranceServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Lifeinsurance")
@AllArgsConstructor
public class Lifeinsurancecontroller {
    private LifeInsuranceServices lifeInsuranceServices;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> adddata(@RequestBody RequestDto requestDto){
        return ResponseEntity.ok(lifeInsuranceServices.calculations(requestDto));
    }


}
