package com.wishmaster.ifmo.ws.jaxrs.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection connect() {
        Connection connection = null;
        String jdbcURL = "jdbc:postgresql://localhost:5432/SearchSoapDatabase";
        String username = "postgres";
        String password = "2760591";
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL server");
        } catch (SQLException e) {
            System.out.println("Error in connection to PostgreSQL server");
            e.printStackTrace();
        }
        return connection;
    }
}