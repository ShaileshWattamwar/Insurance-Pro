package com.app.AutoInsurance.controller;

import com.app.AutoInsurance.Entity.Policy;
import com.app.AutoInsurance.service.InsuranceQuoteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Hidden")
@AllArgsConstructor
public class HiddenController {
    private InsuranceQuoteService service;
    private static final Logger logger= LoggerFactory.getLogger(HiddenController.class);

    @GetMapping("/AutoInsurance/all")
    public ResponseEntity<List<Policy>> getall(){
        return ResponseEntity.ok(service.getall());
    }

    @DeleteMapping("/AutoInsurance/{id}")
    public void deletebyid(@PathVariable long id){
        service.deleteById(id);
        logger.atInfo().log("Deleted");

    }

}
