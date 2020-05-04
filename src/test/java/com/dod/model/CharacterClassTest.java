package com.dod.model;

import com.dod.exceptions.NotFoundException;
import com.dod.model.setup.ConnectSetup;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CharacterClassTest extends ConnectSetup {

    @Test
    public void createWithoutUUID() {
        CharacterClass c = new CharacterClass("Orc", "1d20");
        assertNotNull(c.getUuid());
    }

    @Test
    public void createWithUUID() {
        UUID uuid = UUID.randomUUID();
        CharacterClass c = new CharacterClass(uuid.toString(), "Orc", "1d20");
        assertEquals(uuid.toString(), c.getUuid());
    }

    @Test
    public void testAsJson() throws JsonProcessingException, ParseException {
        CharacterClass c = new CharacterClass("Teste", "1d20");
        JSONObject j = c.asJson();

        assertEquals(c.getUuid(), j.get("uuid"));
        assertEquals(c.getName(), j.get("name"));
        assertEquals(c.getModifier(), j.get("modifier"));
    }

    @Test
    public void testInsertAndFind() throws SQLException, NotFoundException {
        CharacterClass c = new CharacterClass("Orc", "1d20", "10");
        c.insert();
        CharacterClass cr = CharacterClass.find(c.getUuid());

        assertEquals(c.getUuid(), cr.getUuid());
    }

    @Test
    public void testFind() throws SQLException, NotFoundException {
        CharacterClass cr = CharacterClass.find("uuid-0001");

        assertEquals("Warrior", cr.getName());
    }

    @Test(expected = NotFoundException.class)
    public void testFailToFind() throws SQLException, NotFoundException {
        CharacterClass.find("no-uuid");
    }
}