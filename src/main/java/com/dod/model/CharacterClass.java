package com.dod.model;

import com.dod.db.sql.Connect;
import com.dod.exceptions.NotFoundException;
import com.dod.model.base.ModelBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterClass extends ModelBase {

    public final String name;
    public final String modifier;

    protected CharacterClass(String name, String modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    protected CharacterClass(String uuid, String name, String modifier) {
        this(name, modifier);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getModifier() {
        return modifier;
    }

    public void insert() throws SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "INSERT INTO character_class(" +
                                "uuid," +
                                "name," +
                                "modifier" +
                                ") VALUES (?, ?, ?)"
                );
        ps.setString(1, getUuid());
        ps.setString(2, getName());
        ps.setString(3, getModifier());
        ps.executeUpdate();
    }

    public static CharacterClass find(final String uuid) throws NotFoundException, SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "SELECT " +
                                "uuid," +
                                "name," +
                                "modifier" +
                                " FROM character_class " +
                                " WHERE uuid = ?"
                );
        ps.setString(1, uuid);

        final ResultSet rs = ps.executeQuery();
        if (!rs.next()) throw new NotFoundException(uuid);

        return new CharacterClass(
                rs.getString("uuid"),
                rs.getString("name"),
                rs.getString("modifier")
        );
    }
}
