package com.testSpring.Tests.controller;

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.service.BalanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;
@RestController
@RequestMapping("/elderly")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BalanceController {

    private final BalanceService balanceService;
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public double getBalance(Elderly elderly) {
        return balanceService.calculateBalance(elderly);
    }
}
