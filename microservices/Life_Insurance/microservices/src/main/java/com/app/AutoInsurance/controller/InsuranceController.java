package com.app.AutoInsurance.controller;


import com.app.AutoInsurance.dto.QuoteRequest;
import com.app.AutoInsurance.service.InsuranceQuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceQuoteService quoteService;

    @PostMapping("/quote")
    public ResponseEntity<?> getQuote(@Valid @RequestBody QuoteRequest request) {
        try {
            Map<String, Object> quote = quoteService.generateQuote(
                    request.getVehicleModel(),
                    request.getRegistrationYear(),
                    request.getPolicyType()
            );
            return ResponseEntity.ok(quote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}