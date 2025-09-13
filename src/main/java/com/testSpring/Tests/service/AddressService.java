package com.testSpring.Tests.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testSpring.Tests.model.Address;
import com.testSpring.Tests.persistence.DAO.AddressDAO;

@Service
public class AddressService {

    private final AddressDAO addressDAO;

    public AddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public Address addAddress(Address address) {
        addressDAO.add(address);
        return address;
    }

    public long deleteAddress(long id) {
        addressDAO.delete(id);
        return id;
    }

    public Address updateAddress(Address address) {
        addressDAO.update(address);
        return address;
    }

    public Address getAddressById(long id) {
        return addressDAO.selectById(id);
    }

    public List<Address> getAllAddresses() {
        return addressDAO.selectAll();
    }
}
