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

/**
 * Controller responsável por gerenciar operações relacionadas a Incomes.
 * Implementa a interface ProtocolMethods para garantir consistência
 * entre os métodos de CRUD.
 */
@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AddressController implements ProtocolMethods<Address> {

    @Override
    @PostMapping
    public Address post(@RequestBody Address address) {
        // TODO: implementar lógica para salvar "income"
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        // TODO: implementar lógica para deletar por "id"
        return false;
    }

    @Override
    @PutMapping("/{id}")
    public Address put(@PathVariable long id, @RequestBody Address income) {
        // TODO: implementar lógica para atualizar "income" por "id"
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public Address patch(@PathVariable long id, @RequestBody Address income) {
        // TODO: implementar lógica para atualização parcial
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public Address getById(@PathVariable long id) {
        // TODO: implementar lógica para buscar por "id"
        return null;
    }

    @Override
    @GetMapping
    public List<Address> getAll() {
        // TODO: implementar lógica para listar todos
        return null;
    }
}
