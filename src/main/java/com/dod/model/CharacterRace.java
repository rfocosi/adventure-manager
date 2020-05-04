package com.dod.model;

import com.dod.db.sql.Connect;
import com.dod.exceptions.NotFoundException;
import com.dod.model.base.ModelBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterRace extends ModelBase {

    private final String name;
    private final String modifier;
    private final String maxActions;

    protected CharacterRace(String name, String modifier, String maxActions) {
        this.name = name;
        this.modifier = modifier;
        this.maxActions = maxActions;
    }

    protected CharacterRace(String uuid, String name, String modifier, String maxActions) {
        this(name, modifier, maxActions);
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

    public String getMaxActions() {
        return maxActions;
    }

    public void insert() throws SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "INSERT INTO character_race(" +
                                "uuid," +
                                "name," +
                                "modifier," +
                                "max_actions" +
                                ") VALUES (?, ?, ?, ?)"
                );
        ps.setString(1, getUuid());
        ps.setString(2, getName());
        ps.setString(3, getModifier());
        ps.setString(4, getMaxActions());
        ps.executeUpdate();
    }

    public static CharacterRace find(final String uuid) throws NotFoundException, SQLException {
        final PreparedStatement ps = Connect.getInstance().conn()
                .prepareStatement(
                        "SELECT " +
                                "uuid," +
                                "name," +
                                "modifier," +
                                "max_actions" +
                                " FROM character_race " +
                                " WHERE uuid = ?"
                );
        ps.setString(1, uuid);

        final ResultSet rs = ps.executeQuery();
        if (!rs.next()) throw new NotFoundException(uuid);

        return new CharacterRace(
                rs.getString("uuid"),
                rs.getString("name"),
                rs.getString("modifier"),
                rs.getString("max_actions")
        );
    }
}
