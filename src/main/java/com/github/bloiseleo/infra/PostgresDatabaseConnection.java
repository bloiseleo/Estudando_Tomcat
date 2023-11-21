package com.github.bloiseleo.infra;

import com.github.bloiseleo.exception.InfraException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabaseConnection implements DatabaseConnection{
    private static Connection connection;
    @Override
    public Connection getConnection() throws InfraException {
        if (checkIfConnectionIsValid()) {
            return connection;
        }
        connection = createNewConnection();
        return connection;
    }
    private boolean checkIfConnectionIsValid() throws InfraException {
        try {
            if (connection == null) {
                return false;
            }
            if(connection.isClosed()) {
                return false;
            }
            return true;
        } catch (SQLException sqlException) {
            throw new InfraException(sqlException);
        }
    }
    private Connection createNewConnection() throws InfraException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://database:5432/tasksdatabase?ssl=false&user=tasksdatabase&password=root");
            return connection;
        } catch (SQLException | ClassNotFoundException sqlException) {
            throw new InfraException(sqlException);
        }
    }
}
