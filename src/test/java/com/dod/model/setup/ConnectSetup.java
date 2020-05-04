package com.dod.model.setup;

import com.dod.db.sql.Connect;
import com.dod.db.sql.CreateDb;
import com.dod.db.sql.resources.DataTest;
import org.junit.BeforeClass;

import java.sql.SQLException;

public abstract class ConnectSetup {

    @BeforeClass
    public static void setUp() throws SQLException {
        final Connect connect = Connect.getInstance("jdbc:sqlite::memory:");
        new CreateDb(connect);
        new DataTest(connect);
    }
}
