package io.centipod.jackson.serializers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import io.centipod.jackson.exceptions.JsonParsingException;

/**
 * Deserializer for LocalDate objects
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    /**
     * Formatter
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor
     */
    public JsonLocalDateDeserializer() {}

    /**
     * Constructor
     * @param pattern
     */
    public JsonLocalDateDeserializer(String pattern) {

        if ((pattern != null) && !pattern.isEmpty()) {

            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws JsonProcessingException {

        String value = null;

        try {

            value = p.getValueAsString();
        } catch (IOException e) {

            throw new JsonParsingException("Failed to read value from JSON parser.", e);
        }

        if ((value != null) && !value.isEmpty()) {

            return LocalDate.from(this.formatter.parse(value));
        } else {

            return null;
        }
    }
}