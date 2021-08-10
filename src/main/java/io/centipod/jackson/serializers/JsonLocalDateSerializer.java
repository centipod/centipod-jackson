package io.centipod.jackson.serializers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.centipod.jackson.exceptions.JsonParsingException;

/**
 * Serialization for LocalDate objects
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {

    /**
     * Formatter
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor
     */
    public JsonLocalDateSerializer() {}

    /**
     * Constructor
     * @param patter
     */
    public JsonLocalDateSerializer(String pattern) {

        if ((pattern != null) && !pattern.isEmpty()) {

            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws JsonProcessingException {

        try {

            jgen.writeString(value.format(this.formatter));
        } catch (IOException e) {

            throw new JsonParsingException("Failed to write value to JSON generator.", e);
        }
    }
}