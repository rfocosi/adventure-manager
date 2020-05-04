package com.dod.model;

import com.dod.db.sql.Connect;
import com.dod.db.sql.CreateDb;
import com.dod.db.sql.resources.DataTest;
import com.dod.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;

public class CharacterTest {

    private static final Connect connect = Connect.getInstance("jdbc:sqlite::memory:");

    @BeforeClass
    public static void setUp() throws SQLException {
        new CreateDb(connect);
        new DataTest(connect);
    }

    @Test
    public void createWithoutUUID() throws NotFoundException, SQLException {
        Character c = new Character("Sponge Bob", "uuid-0002", "uuid-0001");
        assertNotNull(c.getUuid());
    }

    @Test
    public void createWithUUID() throws NotFoundException, SQLException {
        UUID uuid = UUID.randomUUID();
        Character c = new Character(uuid.toString(), "Danny Phantom", "uuid-0003", "uuid-0001");
        assertEquals(uuid.toString(), c.getUuid());
    }

    @Test
    public void testAsJson() throws JsonProcessingException, ParseException, NotFoundException, SQLException {
        Character c = new Character("Philbor Antonov", "uuid-0001", "uuid-0004");
        JSONObject j = c.asJson();

        assertEquals(c.getUuid(), j.get("uuid"));
        assertEquals(c.getName(), j.get("name"));
        assertEquals(c.getCharacterRace().getUuid(), ((JSONObject) j.get("characterRace")).get("uuid"));
        assertEquals(c.getCharacterClass().getUuid(), ((JSONObject) j.get("characterClass")).get("uuid"));
    }

    @Test
    public void testInsertAndFind() throws SQLException, NotFoundException {
        Character c = new Character("Perry Platypus", "uuid-0004", "uuid-0001");
        c.insert();
        Character cr = Character.find(c.getUuid());

        assertEquals(c.getUuid(), cr.getUuid());
    }

    @Test
    public void testFind() throws SQLException, NotFoundException {
        Character cr = Character.find("uuid-0001");

        assertEquals("Marty McFly", cr.getName());
        assertEquals("Human", cr.getCharacterRace().getName());
        assertEquals("Wizard", cr.getCharacterClass().getName());
    }

    @Test(expected = NotFoundException.class)
    public void testFailToFind() throws SQLException, NotFoundException {
        Character.find("no-uuid");
    }
    
}