package br.edu.ifsp.addthenewsoul.application.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (Database.connection == null) Database.connection = DriverManager.getConnection("jdbc:sqlite:persistence.db");

            Database.connection.createStatement();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Database.connection;
    }

    public static Statement getStatement() {
        Statement statement = null;
        try {
            statement = Database.connection.createStatement();
        } catch (NullPointerException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return statement;
    }
}
