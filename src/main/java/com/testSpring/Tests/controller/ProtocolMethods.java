// Package;
package com.testSpring.Tests.controller;

// Imports;
import java.util.List;

// Intarface of the HTTP Protocol methods; 
public interface ProtocolMethods<T> {
    
    // Post;
    T post(T entity);

    // Delete;
    boolean delete(long id);

    // Put;
    T put(long id, T entity);

    // Patch;
    T patch(long id, T entity);

    // Get with an ID path;
    T getById(long id);

    // Get returning all values;
    List<T> getAll();
}
