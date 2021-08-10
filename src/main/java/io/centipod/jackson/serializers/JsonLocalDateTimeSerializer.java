package io.centipod.jackson.serializers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.centipod.jackson.exceptions.JsonParsingException;

/**
 * Serialization for LocalDateTime objects
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    /**
     * Formatter
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * Constructor
     */
    public JsonLocalDateTimeSerializer() {}

    /**
     * Constructor
     * @param patter
     */
    public JsonLocalDateTimeSerializer(String pattern) {

        if ((pattern != null) && !pattern.isEmpty()) {

            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws JsonProcessingException {

        try {

            jgen.writeString(value.atZone(ZoneId.of("Africa/Johannesburg")).format(this.formatter));
        } catch (IOException e) {

            throw new JsonParsingException("Failed to write value to JSON generator.", e);
        }
    }
}