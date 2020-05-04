package com.dod.model;

import com.dod.exceptions.NotFoundException;
import com.dod.model.setup.ConnectSetup;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;

public class CharacterRaceTest extends ConnectSetup {

    @Test
    public void createWithoutUUID() {
        CharacterRace c = new CharacterRace("Orc", "1d20", "10");
        assertNotNull(c.getUuid());
    }

    @Test
    public void createWithUUID() {
        UUID uuid = UUID.randomUUID();
        CharacterRace c = new CharacterRace(uuid.toString(), "Orc", "1d20", "10");
        assertEquals(uuid.toString(), c.getUuid());
    }

    @Test
    public void testAsJson() throws JsonProcessingException, ParseException {
        CharacterRace c = new CharacterRace("Teste", "1d20", "10");
        JSONObject j = c.asJson();

        assertEquals(c.getUuid(), j.get("uuid"));
        assertEquals(c.getName(), j.get("name"));
        assertEquals(c.getModifier(), j.get("modifier"));
        assertEquals(c.getMaxActions(), j.get("maxActions"));
    }

    @Test
    public void testInsertAndFind() throws SQLException, NotFoundException {
        CharacterRace c = new CharacterRace("Orc", "1d20", "10");
        c.insert();
        CharacterRace cr = CharacterRace.find(c.getUuid());

        assertEquals(c.getUuid(), cr.getUuid());
    }

    @Test
    public void testFind() throws SQLException, NotFoundException {
        CharacterRace cr = CharacterRace.find("uuid-0001");

        assertEquals("Human", cr.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testFailToFind() throws SQLException, NotFoundException {
        CharacterRace.find("no-uuid");
    }
}