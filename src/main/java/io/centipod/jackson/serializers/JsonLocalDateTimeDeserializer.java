package io.centipod.jackson.serializers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import io.centipod.jackson.exceptions.JsonParsingException;

/**
 * Deserializer for LocalDateTime objects
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    /**
     * Formatter
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * Constructor
     */
    public JsonLocalDateTimeDeserializer() {}

    /**
     * Constructor
     * @param pattern
     */
    public JsonLocalDateTimeDeserializer(String pattern) {

        if ((pattern != null) && !pattern.isEmpty()) {

            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws JsonProcessingException {

        String value = null;

        try {

            value = p.getValueAsString();
        } catch (IOException e) {

            throw new JsonParsingException("Failed to read value from JSON parser.", e);
        }

        return LocalDateTime.from(this.formatter.parse(value));
    }
}