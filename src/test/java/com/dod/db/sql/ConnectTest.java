package com.dod.db.sql;

import com.dod.db.sql.resources.DataTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ConnectTest {

    private static final Connect connect = Connect.getInstance("jdbc:sqlite::memory:");

    @BeforeClass
    public static void setUp() throws SQLException {
        new CreateDb(connect);
        new DataTest(connect);
    }

    @Test
    public void getInstance() {
        int instanceHash1 = connect.hashCode();
        int instanceHash2 = connect.hashCode();

        assertEquals(instanceHash1, instanceHash2);
    }
}