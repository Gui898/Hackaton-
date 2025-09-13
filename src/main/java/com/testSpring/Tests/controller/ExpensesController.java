// Package;
package com.testSpring.Tests.controller;

// Imports;
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

import com.testSpring.Tests.model.Expenses;
import com.testSpring.Tests.service.ExpensesService;

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;  
@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ExpensesController implements ProtocolMethods<Expenses> {

    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }

    @Override
    @PostMapping
    public Expenses post(@RequestBody Expenses expense) {
        return expensesService.addExpenses(expense);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        expensesService.deleteExpenses(id);
        return true;
    }

    @Override
    @PutMapping("/{id}")
    public Expenses put(@PathVariable long id, @RequestBody Expenses expense) {
        expense.setIdExpenses(id);
        return expensesService.updateExpenses(expense);
    }

    @Override
    @PatchMapping("/{id}")
    public Expenses patch(@PathVariable long id, @RequestBody Expenses expense) {
        expense.setIdExpenses(id);
        return expensesService.updateExpenses(expense);
    }

    @Override
    @GetMapping("/{id}")
    public Expenses getById(@PathVariable long id) {
        return expensesService.getExpensesById(id);
    }

    @Override
    @GetMapping
    public List<Expenses> getAll() {
        return expensesService.getAllExpenses();
    }
}
