package com.dod.db.sql.resources;

import com.dod.db.sql.Connect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataTest {

    public DataTest(final Connect connect) throws SQLException {
        insertCharacterRace(connect);
        insertCharacterClass(connect);
        insertCharacter(connect);
    }

    private void insertCharacterRace(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "INSERT INTO character_race (" +
                        "uuid," +
                        "name," +
                        "modifier," +
                        "max_actions" +
                        ") VALUES " +
                        "( 'uuid-0001', 'Human', 'A+1d10', '10' )," +
                        "( 'uuid-0002', 'Half-orc', 'A+1d20', '9' )," +
                        "( 'uuid-0003', 'Elf', 'D+1d10', '12' )," +
                        "( 'uuid-0004', 'Halfling', 'D+1d6', '15' )"

        );
        ps.executeUpdate();
    }

    private void insertCharacterClass(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "INSERT INTO character_class (" +
                        "uuid," +
                        "name," +
                        "modifier" +
                        ") VALUES " +
                        "( 'uuid-0001', 'Warrior', 'A+1d10' )," +
                        "( 'uuid-0002', 'Wizard', 'D+1d10' )," +
                        "( 'uuid-0003', 'Barbarian', 'A+1d20' )," +
                        "( 'uuid-0004', 'Thiefling', 'D+1d20' )"

        );
        ps.executeUpdate();
    }

    private void insertCharacter(final Connect connect) throws SQLException {
        PreparedStatement ps = connect.conn().prepareStatement(
                "INSERT INTO character (" +
                        "uuid," +
                        "name," +
                        "character_class_uuid," +
                        "character_race_uuid" +
                        ") VALUES " +
                        "( 'uuid-0001', 'Marty McFly', 'uuid-0002', 'uuid-0001' )"
        );
        ps.executeUpdate();
    }
}
