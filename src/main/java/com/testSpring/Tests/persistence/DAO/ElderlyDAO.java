// Package;
package com.testSpring.Tests.persistence.DAO;

// Imports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// ElderlyDAO class implementing the CRUD; 
@Repository
public class ElderlyDAO implements Crud<Elderly>{
    
    // The MySQLConnection attribute; 
    private final MySqlConnection mySqlConnection;
    // Constructor of the UserDAO;
    public ElderlyDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert; 
    private final String INSERT_ELDERLY = 
        """
        INSERT INTO elderly(id_elderly, name_elderly, email, password_elderly, cpf, birth_year, gender, phone)
        VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);
        """;
    
    // SQL code to Delete;
    private final String DELETE_ELDERLY = 
        """
        DELETE FROM elderly WHERE id_elderly = ?;
        """;

    // SQL code to Update;
    private final String UPDATE_ELDERLY = 
        """
        UPDATE elderly
        SET name_elderly = ?, email = ?, password_elderly = ?, cpf = ?, birth_year = ?, gender = ?, phone = ?
        WHERE id_elderly = ?;
        """;

    // SQL code to Select by the ID;
    private final String SELECT_ELDERLY_BY_ID = 
        """
        SELECT * FROM elderly WHERE id_elderly = ?;
        """;

    // SQL code to Select All;
    private final String SELECT_ALL_ELDERLY = 
        """
        SELECT * FROM elderly;
        """;

    // Add method;
    @Override
    public void add(Elderly elderly) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(INSERT_ELDERLY);
        
            // Setting the values;
            preparedStatement.setString(1, elderly.getNameElderly());
            preparedStatement.setString(2, elderly.getEmail());
            preparedStatement.setString(3, elderly.getPasswordElderly());
            preparedStatement.setString(4, elderly.getCpf());
            preparedStatement.setString(5, elderly.getBirthYear());
            preparedStatement.setString(6, elderly.getGender());
            preparedStatement.setString(7, elderly.getPhone());

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
                mySqlConnection.getConnection().prepareStatement(DELETE_ELDERLY);
            
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
    public void update(Elderly elderly) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(UPDATE_ELDERLY);

            // Setting the values;
            preparedStatement.setString(1, elderly.getNameElderly());
            preparedStatement.setString(2, elderly.getEmail());
            preparedStatement.setString(3, elderly.getPasswordElderly());
            preparedStatement.setString(4, elderly.getCpf());
            preparedStatement.setString(5, elderly.getBirthYear());
            preparedStatement.setString(6, elderly.getGender());
            preparedStatement.setString(7, elderly.getPhone());
            preparedStatement.setLong(8, elderly.getIdElderly());
        
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
    public Elderly selectById(long id) {

        // Declaring a new User;
        Elderly elderly = null;

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_ELDERLY_BY_ID);
            
            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute the Query;
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting the Result;
            if (resultSet.next()) {

                // Instanciate the User with the values;
                elderly = new Elderly(resultSet.getString("name_elderly"), 
                    resultSet.getString("email"), resultSet.getString("password_elderly"),
                    resultSet.getString("cpf"), resultSet.getString("birth_year"),
                    resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));
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
        return elderly;
    }

    // Select All method;
    @Override
    public List<Elderly> selectAll() {
        
        // Declaring a List of all Users;
        List<Elderly> allElderly = new ArrayList();

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_ALL_ELDERLY);
            
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

                // Adding that User on the List;
                allElderly.add(elderly);
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
        return allElderly;
    }
}
