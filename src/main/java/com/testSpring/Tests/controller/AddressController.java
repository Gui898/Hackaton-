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

import com.testSpring.Tests.model.Address;
import com.testSpring.Tests.service.AddressService;

/**
 * Controller responsável por gerenciar operações relacionadas a Incomes.
 * Implementa a interface ProtocolMethods para garantir consistência
 * entre os métodos de CRUD.
 */
@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AddressController implements ProtocolMethods<Address> {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    @PostMapping
    public Address post(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        addressService.deleteAddress(id);
        return true;
    }

    @Override
    @PutMapping("/{id}")
    public Address put(@PathVariable long id, @RequestBody Address address) {
        address.setIdAddress(id);
        return addressService.updateAddress(address);
    }

    @Override
    @PatchMapping("/{id}")
    public Address patch(@PathVariable long id, @RequestBody Address address) {
        address.setIdAddress(id);
        return addressService.updateAddress(address);
    }

    @Override
    @GetMapping("/{id}")
    public Address getById(@PathVariable long id) {
        return addressService.getAddressById(id);
    }

    @Override
    @GetMapping
    public List<Address> getAll() {
        return addressService.getAllAddresses();
    }
}
