package com.dod.db.sql.setup;

import com.dod.db.sql.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDb {

    public CreateDb(final Connect connect) throws SQLException {
        createCharacter(connect);
        createCharacterRace(connect);
        createCharacterClass(connect);
    }

    private void createCharacter(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "CREATE TABLE IF NOT EXISTS character (" +
                        "uuid TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "character_class_uuid TEXT NOT NULL," +
                        "character_race_uuid TEXT NOT NULL" +
                        ");"
        );
        ps.executeUpdate();
    }

    private void createCharacterRace(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "CREATE TABLE IF NOT EXISTS character_race (" +
                        "uuid TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "modifier TEXT NOT NULL," +
                        "max_actions TEXT NOT NULL" +
                        ");"
        );
        ps.executeUpdate();
    }

    private void createCharacterClass(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "CREATE TABLE IF NOT EXISTS character_class (" +
                        "uuid TEXT PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "modifier TEXT NOT NULL" +
                        ");"
        );
        ps.executeUpdate();
    }
}
