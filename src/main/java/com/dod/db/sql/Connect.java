package com.dod.db.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Connect {
    private static final Logger logger = Logger.getLogger(Connect.class.getName());
    private static Connect connect;
    private Connection conn;

    private Connect(final String url) {
        try {
            this.conn = DriverManager.getConnection(url);
            logger.info("Connection to database has been established.");
        } catch (SQLException e) {
            logger.severe(e.getMessage());
        }
    }

    public static Connect getInstance() {
        return getInstance(System.getenv("db.connect.url"));
    }

    public static Connect getInstance(final String url) {
        return (connect == null ? connect = new Connect(url) : connect);
    }

    public Connection conn() {
        return conn;
    }
}
