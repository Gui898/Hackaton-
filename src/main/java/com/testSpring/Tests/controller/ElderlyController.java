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

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.service.ElderlyService;

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;  
@RestController
@RequestMapping("/elderly")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ElderlyController implements ProtocolMethods<Elderly> {

    private final ElderlyService elderlyService;

    public ElderlyController(ElderlyService elderlyService) {
        this.elderlyService = elderlyService;
    }

    // Overriding and using Post method;
    @Override
    @PostMapping
    public Elderly post(@RequestBody Elderly elderly) {
        return elderlyService.addElderly(elderly);
    }

    // Overriding and using Delete method;
    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        elderlyService.deleteElderly(id);
        return true;
    }

    // Overriding and using Put method;
    @Override
    @PutMapping("/{id}")
    public Elderly put(@PathVariable long id, @RequestBody Elderly elderly) {
        elderly.setIdElderly(id);
        return elderlyService.updateElderly(elderly);
    }

    // Overriding and using Patch method;
    @Override
    @PatchMapping("/{id}")
    public Elderly patch(@PathVariable long id, @RequestBody Elderly elderly) {
        elderly.setIdElderly(id);
        return elderlyService.updateElderly(elderly);
    }

    // Overriding and using Get method with ID Path;
    @Override
    @GetMapping("/{id}")
    public Elderly getById(@PathVariable long id) {
        return elderlyService.getElderlyById(id);
    }

    // Overriding and using Get method, returning all;
    @Override
    @GetMapping
    public List<Elderly> getAll() {
        return elderlyService.getAllElderly();
    }
}
