package com.testSpring.Tests.persistence;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class MySqlConnection {

    Connection connection;


    public void openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ ConstsDb.IP+":"+ConstsDb.PORT+"/"+ConstsDb.DATABASE,
                    ConstsDb.LOGIN,
                    ConstsDb.PASSWORD
            );

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            if(!(this.connection.isClosed())){
                this.connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

}
