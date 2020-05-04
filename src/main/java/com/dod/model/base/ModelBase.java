package com.dod.model.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.UUID;

public abstract class ModelBase {

    protected String uuid = UUID.randomUUID().toString();

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public JSONObject asJson() throws ParseException, JsonProcessingException {
        return (JSONObject) new JSONParser().parse(toJson());
    }
}
