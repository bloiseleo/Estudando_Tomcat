package com.github.bloiseleo.infra;

import com.github.bloiseleo.exception.InfraException;

import java.sql.Connection;

public interface DatabaseConnection {
    /**
     * Creates and returns a new connection. If a connection already exists and it stills open, so this old one is returned.
     * If the old connection was closed, so a new one is open and then returned.
     * @return {@link Connection}
     */
    public Connection getConnection() throws InfraException;
}
