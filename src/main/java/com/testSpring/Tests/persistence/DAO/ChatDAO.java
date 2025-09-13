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
import org.springframework.stereotype.Repository;

import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// UserDAO class implementing the CRUD;
@Repository
public class ChatDAO implements Crud<Chat> {

    // The MySQLConnection attribute;
    private final MySqlConnection mySqlConnection;

    // Constructor of the UserDAO;
    public ChatDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert;
    private final String INSERT_CHAT =
            """
                    INSERT INTO chat(id_chat, type_chat, id_elderly)
                    VALUES (NULL, ?, ?);
                    """;

    // SQL code to Delete;
    private final String DELETE_CHAT =
            """
                    DELETE FROM chat WHERE id_chat = ?;
                    """;

    // SQL code to Select by the ID;
    private final String SELECT_CHAT_BY_ID =
            """
                    SELECT * FROM chat c INNER JOIN elderly e ON c.id_elderly = e.id_elderly
                    WHERE id_chat = ?;
                    """;

    // SQL code to Select All;
    private final String SELECT_ALL_CHATS =
            """
                     SELECT * FROM chat c INNER JOIN elderly e ON c.id_elderly = e.id_elderly;
                    """;

    // Add method;
    @Override
    public void add(Chat chat) {

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(INSERT_CHAT);

            // Setting the values;
            preparedStatement.setString(1, chat.getTypeChat());
            preparedStatement.setLong(2, chat.getElderly().getIdElderly());

            // Execute and Update;
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

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
                    mySqlConnection.getConnection().prepareStatement(DELETE_CHAT);

            // Setting the values;
            preparedStatement.setLong(1, id);

            // Execute and Update;
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }
    }

    @Override
    public void update(Chat object) {}

    // Select by ID method;
    @Override
    public Chat selectById(long id) {

        // Declaring a new User;
        Chat chat = null;

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_CHAT_BY_ID);

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
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                // Instanciate the Chat with the values;
                chat = new Chat(resultSet.getString("type_chat"), elderly);
                chat.setIdChat(id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }

        // Returning the User;
        return chat;
    }

    // Select All method;
    @Override
    public List<Chat> selectAll() {

        // Declaring a List of all Users;
        List<Chat> allChats = new ArrayList<>();

        // Try-Catch to Handle Exceptions;
        try {

            // Opening the Connection;
            mySqlConnection.openConnection();

            // Preparing new Statement;
            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_ALL_CHATS);

            // Execute the Query;
            ResultSet resultSet = preparedStatement.executeQuery();

            // Getting all Results
            while (resultSet.next()) {

                // Declaring and Instanciaing a new Elderly with the values;
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone")
                );

                // Declaring and Instanciaing a new Elderly with the values;
                elderly.setIdElderly(resultSet.getLong("id_elderly"));
                Chat chat = new Chat(resultSet.getString("type_chat"), elderly);
                chat.setIdChat(resultSet.getLong("id_chat"));
                // Adding that User on the List;
                allChats.add(chat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            // Closing the Connection;
            mySqlConnection.closeConnection();
        }

        // Returning the List;
        return allChats;
    }
}