// Package;
package com.testSpring.Tests.persistence;

// Imports;
import java.util.List;

// CRUD interface, with all the methods;
public interface Crud<T> {
    
    // Add method;
    public void add(T object);

    // Delete method;
    public void delete(long id);

    // Update method;
    public void update(T object);

    // Select by ID method;
    public T selectById(long id);

    // Select All method;
    public List<T> selectAll();
}
