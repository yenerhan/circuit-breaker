package com.yener.circuitbreaker.controller;

import com.yener.circuitbreaker.model.GetAllCustomerResponseDTO;
import com.yener.circuitbreaker.service.CircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/circuit-breaker")
public class CircuitController {

    @Autowired
    private CircuitService circuitService;

    @GetMapping(path = "/getAllCustomer")
    public ResponseEntity<GetAllCustomerResponseDTO> getAllCustomer() throws Exception {
        return ResponseEntity.ok().body(circuitService.getAllCustomer());
    }
}
