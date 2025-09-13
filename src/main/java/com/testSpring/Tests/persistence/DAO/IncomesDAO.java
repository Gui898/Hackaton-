// Package;
package com.testSpring.Tests.persistence.DAO;

// Imports;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.testSpring.Tests.model.Elderly;
import org.springframework.stereotype.Repository;

import com.testSpring.Tests.model.Incomes;
import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// IncomesDAO class implementing the CRUD; 
@Repository
public class IncomesDAO implements Crud<Incomes>{
    
    // The MySQLConnection attribute; 
    private final MySqlConnection mySqlConnection;

    // Constructor of the IncomesDAO;
    public IncomesDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert; 
    private final String INSERT_INCOMES = 
        """
        INSERT INTO incomes (id_incomes, fix_or_not, value_incomes, payment_date, type_incomes, frequency)
        VALUES (NULL, ?, ?, ?, ?, ?);
        """;
    
    // SQL code to Delete;
    private final String DELETE_INCOMES = 
        """
        DELETE FROM incomes WHERE id_incomes = ?;
        """;

    // SQL code to Update;
    private final String UPDATE_INCOMES = 
        """
        UPDATE incomes
        SET fix_or_not = ?, value_incomes = ?, payment_date = ?, type_incomes = ?, frequency = ?
        WHERE id_incomes = ?;
        """;

    // SQL code to Select by the ID;
    private final String SELECT_INCOMES_BY_ID = 
        """
        SELECT * FROM elderly e INNER JOIN incomes i ON e.id_elderly = i.id_elderly WHERE id_incomes = ?;
        """;

    // SQL code to Select All;
    private final String SELECT_ALL_INCOMES = 
        """
         SELECT * FROM elderly e INNER JOIN incomes i ON e.id_elderly = i.id_elderly;
        """;

    // Add method;
    @Override
    public void add(Incomes income) {

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(INSERT_INCOMES);
        
            preparedStatement.setBoolean(1, income.isFixOrNot());
            preparedStatement.setDouble(2, income.getValueIncomes());
            preparedStatement.setDate(3, Date.valueOf(income.getDatePaymentIncomes().atStartOfDay().toLocalDate()));
            preparedStatement.setString(4, income.getTypeIncomes());
            preparedStatement.setInt(5, income.getFrequency());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection();
        }
    }

    // Delete method;
    @Override
    public void delete(long id) {

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(DELETE_INCOMES);
            
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection(); 
        }
    }

    // Update method;
    @Override
    public void update(Incomes income) {

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(UPDATE_INCOMES);

            preparedStatement.setBoolean(1, income.isFixOrNot());
            preparedStatement.setDouble(2, income.getValueIncomes());
            preparedStatement.setObject(3, Date.valueOf(income.getDatePaymentIncomes().atStartOfDay().toLocalDate()));
            preparedStatement.setString(4, income.getTypeIncomes());
            preparedStatement.setInt(5, income.getFrequency());
            preparedStatement.setLong(6, income.getIdIncomes());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection();
        }
    }

    // Select by ID method;
    @Override
    public Incomes selectById(long id) {

        Incomes income = null;

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_INCOMES_BY_ID);
            
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                LocalDate data = resultSet.getDate("payment_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                income = new Incomes(
                    resultSet.getBoolean("fix_or_not"),
                    data,
                    resultSet.getDouble("value_incomes"),
                    resultSet.getString("type_incomes"),
                    resultSet.getInt("frequency"),
                    elderly);
                income.setIdIncomes(resultSet.getLong("id_incomes"));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection();
        }

        return income;
    }

    // Select All method;
    @Override
    public List<Incomes> selectAll() {
        
        List<Incomes> allIncomes = new ArrayList<>();

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement = 
                mySqlConnection.getConnection().prepareStatement(SELECT_ALL_INCOMES);
            
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                LocalDate data = resultSet.getDate("payment_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Incomes income = new Incomes(
                        resultSet.getBoolean("fix_or_not"),
                        data,
                        resultSet.getDouble("value_incomes"),
                        resultSet.getString("type_incomes"),
                        resultSet.getInt("frequency"),
                        elderly);
                income.setIdIncomes(resultSet.getLong("id_incomes"));
                allIncomes.add(income);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
           mySqlConnection.closeConnection(); 
        }

        return allIncomes;
    }
}
