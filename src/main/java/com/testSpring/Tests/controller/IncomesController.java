package com.testSpring.Tests.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpring.Tests.model.Incomes;
import com.testSpring.Tests.service.IncomesService;

@RestController
@RequestMapping("/incomes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class IncomesController implements ProtocolMethods<Incomes> {

    private final IncomesService incomesService;

    public IncomesController(IncomesService incomesService) {
        this.incomesService = incomesService;
    }

    @Override
    @PostMapping
    public Incomes post(@RequestBody Incomes income) {
        return incomesService.addIncomes(income);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        incomesService.deleteIncomes(id);
        return true;
    }

    @Override
    @PutMapping("/{id}")
    public Incomes put(@PathVariable long id, @RequestBody Incomes income) {
        income.setIdIncomes(id);
        return incomesService.updateIncomes(income);
    }

    @Override
    @PatchMapping("/{id}")
    public Incomes patch(@PathVariable long id, @RequestBody Incomes income) {
        income.setIdIncomes(id);
        return incomesService.updateIncomes(income);
    }

    @Override
    @GetMapping("/{id}")
    public Incomes getById(@PathVariable long id) {
        return incomesService.getIncomesById(id);
    }

    @Override
    @GetMapping
    public List<Incomes> getAll() {
        return incomesService.getAllIncomes();
    }
}
