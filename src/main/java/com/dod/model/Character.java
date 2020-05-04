package com.dod.model;

import com.dod.db.sql.Connect;
import com.dod.exceptions.NotFoundException;
import com.dod.model.base.ModelBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Character extends ModelBase {

    private final String name;
    private final CharacterRace characterRace;
    private final CharacterClass characterClass;

    protected Character(String name, String character_race_uuid, String character_class_uuid) throws NotFoundException, SQLException {
        this.name = name;
        this.characterRace = CharacterRace.find(character_race_uuid);
        this.characterClass = CharacterClass.find(character_class_uuid);
    }

    protected Character(String uuid, String name, String character_race_uuid, String character_class_uuid) throws NotFoundException, SQLException {
        this(name, character_race_uuid, character_class_uuid);
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public CharacterRace getCharacterRace() {
        return characterRace;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void insert() throws SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "INSERT INTO character(" +
                                "uuid," +
                                "name," +
                                "character_race_uuid," +
                                "character_class_uuid" +
                                ") VALUES (?, ?, ?, ?)"
                );
        ps.setString(1, getUuid());
        ps.setString(2, getName());
        ps.setString(3, characterRace.getUuid());
        ps.setString(4, characterClass.getUuid());
        ps.executeUpdate();
    }

    public static Character find(final String uuid) throws NotFoundException, SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "SELECT " +
                                "uuid," +
                                "name," +
                                "character_race_uuid," +
                                "character_class_uuid" +
                                " FROM character " +
                                " WHERE uuid = ?"
                );
        ps.setString(1, uuid);

        final ResultSet rs = ps.executeQuery();
        if (!rs.next()) throw new NotFoundException(uuid);

        return new Character(
                rs.getString("uuid"),
                rs.getString("name"),
                rs.getString("character_race_uuid"),
                rs.getString("character_class_uuid")
        );
    }
}
