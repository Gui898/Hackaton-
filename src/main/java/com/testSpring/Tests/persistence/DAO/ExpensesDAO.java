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

import com.testSpring.Tests.model.Expenses;
import com.testSpring.Tests.persistence.Crud;
import com.testSpring.Tests.persistence.MySqlConnection;

// ExpensesDAO class implementing the CRUD;
@Repository
public class ExpensesDAO implements Crud<Expenses>{

    // The MySQLConnection attribute;
    private final MySqlConnection mySqlConnection;

    // Constructor of the ExpensesDAO;
    public ExpensesDAO(MySqlConnection mySqlConnection) {
        this.mySqlConnection = mySqlConnection;
    }

    // SQL code to Insert;
    private final String INSERT_EXPENSES =
            """
            INSERT INTO expenses (id_expenses, fix_or_not, value_expenses, payment_date, type_expenses, portion)
            VALUES (NULL, ?, ?, ?, ?, ?);
            """;

    // SQL code to Delete;
    private final String DELETE_EXPENSES =
            """
            DELETE FROM expenses WHERE id_expenses = ?;
            """;

    // SQL code to Update;
    private final String UPDATE_EXPENSES =
            """
            UPDATE expenses
            SET fix_or_not = ?, value_expenses = ?, payment_date = ?, type_expenses = ?, portion = ?
            WHERE id_expenses = ?;
            """;

    // SQL code to Select by the ID;
    private final String SELECT_EXPENSES_BY_ID =
            """
            SELECT * FROM elderly eld INNER JOIN expenses e ON eld.id_elderly = e.id_elderly WHERE id_expenses = ?;
            """;

    // SQL code to Select All;
    private final String SELECT_ALL_EXPENSES =
            """
            SELECT * FROM elderly eld INNER JOIN expenses e ON eld.id_elderly = e.id_elderly;
            """;

    // Add method;
    @Override
    public void add(Expenses expense) {

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(INSERT_EXPENSES);

            preparedStatement.setBoolean(1, expense.isFixOrNot());
            preparedStatement.setDouble(2, expense.getValueExpenses());
            preparedStatement.setDate(3, Date.valueOf(expense.getDatePaymentExpenses().atStartOfDay().toLocalDate()));
            preparedStatement.setString(4, expense.getTypeExpenses());
            preparedStatement.setInt(5, expense.getPortion());

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
                    mySqlConnection.getConnection().prepareStatement(DELETE_EXPENSES);

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
    public void update(Expenses expense) {

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(UPDATE_EXPENSES);

            preparedStatement.setBoolean(1, expense.isFixOrNot());
            preparedStatement.setDouble(2, expense.getValueExpenses());
            preparedStatement.setDate(3,  Date.valueOf(expense.getDatePaymentExpenses().atStartOfDay().toLocalDate()));
            preparedStatement.setString(4, expense.getTypeExpenses());
            preparedStatement.setInt(5, expense.getPortion());
            preparedStatement.setLong(6, expense.getIdExpenses());

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
    public Expenses selectById(long id) {

        Expenses expense = null;

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_EXPENSES_BY_ID);

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                LocalDate data = resultSet.getDate("payment_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                expense = new Expenses(
                        resultSet.getBoolean("fix_or_not"),
                        data,
                        resultSet.getDouble("value_expenses"),
                        resultSet.getString("type_expenses"),
                        resultSet.getInt("portion")
                        , elderly);
                expense.setIdExpenses(resultSet.getLong("id_expenses"));
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection();
        }

        return expense;
    }

    // Select All method;
    @Override
    public List<Expenses> selectAll() {

        List<Expenses> allExpenses = new ArrayList<>();

        try {
            mySqlConnection.openConnection();

            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(SELECT_ALL_EXPENSES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Elderly elderly = new Elderly(resultSet.getString("name_elderly"),
                        resultSet.getString("email"), resultSet.getString("password_elderly"),
                        resultSet.getString("cpf"), resultSet.getString("birth_year"),
                        resultSet.getString("gender"), resultSet.getString("phone"));
                elderly.setIdElderly(resultSet.getLong("id_elderly"));

                LocalDate data = resultSet.getDate("payment_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Expenses expense = new Expenses(
                        resultSet.getBoolean("fix_or_not"),
                        data,
                        resultSet.getDouble("value_expenses"),
                        resultSet.getString("type_expenses"),
                        resultSet.getInt("portion")
                        , elderly);
                expense.setIdExpenses(resultSet.getLong("id_expenses"));
                allExpenses.add(expense);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            mySqlConnection.closeConnection();
        }

        return allExpenses;
    }

    public double sumExpensesById(Elderly elderly) {
        double totalExpenses = 0.0;

        try {
            mySqlConnection.openConnection();

            String sql = """
                SELECT SUM(value_expenses)
                FROM expenses
                WHERE id_elderly = ?;
            """;

            PreparedStatement preparedStatement =
                    mySqlConnection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, elderly.getIdElderly());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalExpenses = resultSet.getDouble(1);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            mySqlConnection.closeConnection();
        }

        return totalExpenses;
    }
}