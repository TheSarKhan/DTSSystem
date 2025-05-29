package com.sarkhan.dtssystem.controller.eligibility;

import com.sarkhan.dtssystem.model.content.eligibility.Eligibility;
import com.sarkhan.dtssystem.service.eligibility.service.EligibilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/eligibility")
@RequiredArgsConstructor
public class EligibilityController {

    private final EligibilityService eligibilityService;

    @GetMapping
    public ResponseEntity<Eligibility> getEligibility(){
        return ResponseEntity.status(200).body(eligibilityService.getEligibilityData());
    }
}
