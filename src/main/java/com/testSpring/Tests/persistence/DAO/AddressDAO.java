// Package;
package com.testSpring.Tests.persistence.DAO;

// Imports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.testSpring.Tests.model.Address;
import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// Class AddressDAO implementing the CRUD;
@Repository
public class AddressDAO implements Crud<Address> {
    
    // The MySQLConnection attribute; 
    private final MySqlConnection mySqlConnection;

    // Constructor of the AddressDAO;
    public AddressDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert; 
    private final String INSERT_ADDRESS = 
        """
        INSERT INTO address(id_address, neighborhood, city, house_number, state_address, street, id_elderly)
        VALUES (NULL, ?, ?, ?, ?, ?, ?);
        """;
    
    // SQL code to Delete;
    private final String DELETE_ADDRESS = 
        """
        DELETE FROM address WHERE id_address = ?;
        """;

    // SQL code to Update;
    private final String UPDATE_ADDRESS = 
        """
        UPDATE address
        SET name_elderly = ?, email = ?, password_elderly = ?, cpf = ?, birth_year = ?, gender = ?, phone = ?
        WHERE id_elderly = ?;
        """;

    // SQL code to Select by the ID;
    private final String SELECT_ADDRESS_BY_ID = 
        """
        SELECT * 
        FROM (address INNER JOIN elderly ON address.id_elderly = elderly.id_elderly)
        WHERE id_address = ?;   
        """;

    // SQL code to Select All;
    private final String SELECT_ALL_ADDRESS = 
        """
        SELECT * 
        FROM (address INNER JOIN elderly ON address.id_elderly = elderly.id_elderly);  
        """;

    // Add method;
    @Override
    public void add(Address address) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(INSERT_ADDRESS);
        
            // Setting the values;
            preparedStatement.setString(1, address.getNeighborhood());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getNumber());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setLong(6, address.getElderly().getIdElderly());
            

            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }
    }

    // Delete method;
    @Override
    public void delete(long id) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(DELETE_ADDRESS);
            
            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection(); 
        }
    }

    // Update method;
    @Override
    public void update(Address address) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(UPDATE_ADDRESS);

            // Setting the values;
            preparedStatement.setString(1, address.getNeighborhood());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getNumber());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getStreet());
            preparedStatement.setLong(6, address.getElderly().getIdElderly());
            preparedStatement.setLong(8, address.getIdAddress());
        
            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }
    }

    // Select by ID method;
    @Override
    public Address selectById(long id) {

        // Declaring a new User;
        Address address = null;

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_ADDRESS_BY_ID);
            
            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute the Query;
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting the Result;
            if (resultSet.next()) {

                // Instanciate the User with the values;
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"), 
                    resultSet.getString("email"), resultSet.getString("password_elderly"),
                    resultSet.getString("cpf"), resultSet.getString("birth_year"),
                    resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                address = new Address(resultSet.getString("neighborhood"), 
                    resultSet.getString("city"), resultSet.getInt("house_number"),
                    resultSet.getString("state_address"), resultSet.getString("street"),
                    elderly);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }

        // Returning the User;
        return address;
    }

    // Select All method;
    @Override
    public List<Address> selectAll() {
        
        // Declaring a List of all Users;
        List<Address> allAddress = new ArrayList();

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_ALL_ADDRESS);
            
            // Execute the Query;  
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting all Results
            while(resultSet.next()) {

                // Declaring and Instanciaing a new User with the values;
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"), 
                    resultSet.getString("email"), resultSet.getString("password_elderly"),
                    resultSet.getString("cpf"), resultSet.getString("birth_year"),
                    resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                Address address = new Address(resultSet.getString("neighborhood"), 
                    resultSet.getString("city"), resultSet.getInt("house_number"),
                    resultSet.getString("state_address"), resultSet.getString("street"),
                    elderly);

                // Adding that User on the List;
                allAddress.add(address);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {

            // Closing the Connection;
           mySqlConnection.closeConnection(); 
        }

        // Returning the List;
        return allAddress;
    }
}
