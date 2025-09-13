
// Package;
package com.testSpring.Tests.persistence.DAO;

// Imports;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testSpring.Tests.model.Chat;
import com.testSpring.Tests.model.Elderly;
import com.testSpring.Tests.model.Message;
import org.springframework.stereotype.Repository;

import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// UserDAO class implementing the CRUD;
@Repository
public class MessageDAO implements Crud<Message> {

    // The MySQLConnection attribute;
    private final MySqlConnection mySqlConnection;

    // Constructor of the UserDAO;
    public MessageDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert;
    private final String INSERT_MESSAGE =
            """
            INSERT INTO message(id_message, sender, text_message, id_chat)
            VALUES (NULL, ?, ?, ?);
            """;

    // SQL code to Delete;
    private final String DELETE_MESSAGE =
            """
            DELETE FROM message WHERE id_message = ?;
            """;

    // SQL code to Update;
    private final String UPDATE_MESSAGE =
            """
            UPDATE message
            SET text_message = ? WHERE id_message = ?;
            """;

    // SQL code to Select by the ID;
    private final String SELECT_MESSAGE_BY_ID =
            """
                    SELECT *FROM message m INNER JOIN chat c ON m.id_chat = c.id_chat
                    INNER JOIN elderly e ON c.id_elderly = e.id_elderly
                    WHERE m.id_message = ?;
            """;

    // SQL code to Select All;
    private final String SELECT_ALL_MESSAGE =
            """
            SELECT * FROM message;
            """;

    // Add method;
    @Override
    public void add(Message message) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(INSERT_MESSAGE);

            // Setting the values;
            preparedStatement.setString(1, message.getSender());
            preparedStatement.setString(2, message.getTextMessage());
            preparedStatement.setLong(3, message.getChat().getIdChat());

            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
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
                    mySqlConnection.getConnection().prepareStatement(DELETE_MESSAGE);

            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }
    }

    // Update method;
    @Override
    public void update(Message message) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(UPDATE_MESSAGE);

            // Setting the values;
            preparedStatement.setString(1, message.getTextMessage());
            preparedStatement.setLong(2, message.getIdMessage());

            // Execute and Update;
            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }
    }

    // Select by ID method;
    @Override
    public Message selectById(long id) {

        // Declaring a new Message;
        Message message = null;

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_MESSAGE_BY_ID);

            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute the Query;
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting the Result;
            if (resultSet.next()) {

                // Instanciate the Elderly with the values;
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone")
                );

                // Instanciate the Chat with the values;
                Chat chat = new Chat(resultSet.getString("type_chat"), elderly);

                // Instanciate the Message with the values;
                message = new Message(resultSet.getString("sender"),
                        resultSet.getString("text_message"), chat);
                message.setIdMessage(id);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }

        // Returning the User;
        return message;
    }

    // Select All method;
    @Override
    public List<Message> selectAll() {

        // Declaring a List of all Users;
        List<Message> allMessages = new ArrayList<>();

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_ALL_MESSAGE);

            // Execute the Query;
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting all Results
            while(resultSet.next()) {

                // Instanciate the Elderly with the values;
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone")
                );

                // Instanciate the Chat with the values;
                Chat chat = new Chat(resultSet.getString("type_chat"), elderly);

                // Instanciate the Message with the values;
                Message message = new Message(resultSet.getString("sender"),
                        resultSet.getString("text_message"), chat);
                message.setIdMessage(resultSet.getLong("id_message"));

                // Adding that User on the List;
                allMessages.add(message);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }

        // Returning the List;
        return allMessages;
    }
}