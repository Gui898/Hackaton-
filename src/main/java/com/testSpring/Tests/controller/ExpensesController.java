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

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;  
@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ExpensesController implements ProtocolMethods<ExpensesController>{
    
    // Overriding and using Post method;
    @Override
    @PostMapping
    public ExpensesController post(@RequestBody ExpensesController expense) {
        return null;
    }

    // Overriding and using Delete method;
    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return false;
    }

    // Overriding and using Put method;
    @Override
    @PutMapping("/{id}")
    public ExpensesController put(@PathVariable long id, @RequestBody ExpensesController user) {

        return null;
    }

    // Overriding and using Patch method;
    @Override
    @PatchMapping("/{id}")
    public ExpensesController patch(@PathVariable long id, @RequestBody ExpensesController user) {

        return null;
    }

    // Overriding and using Get method with ID Path;
    @Override
    @GetMapping("/{id}")
    public ExpensesController getById(@PathVariable long id) {

        return null;
    }

    // Overriding and using Get method, returning all;
    @Override
    @GetMapping
    public List<ExpensesController> getAll() {

        return null;
    }
}
