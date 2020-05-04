package com.dod.db.sql;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectTest {

    @Test
    public void getInstance() {
        final Connect connect = Connect.getInstance("jdbc:sqlite::memory:");
        int instanceHash1 = connect.hashCode();
        int instanceHash2 = connect.hashCode();

        assertEquals(instanceHash1, instanceHash2);
    }
}