package io.centipod.jackson.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.centipod.jackson.serializers.JsonLocalDateDeserializer;
import io.centipod.jackson.serializers.JsonLocalDateSerializer;
import io.centipod.jackson.serializers.JsonLocalDateTimeDeserializer;
import io.centipod.jackson.serializers.JsonLocalDateTimeSerializer;

/**
 * Utility class for JSON serialization and deserialization
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonUtil {

    /**
     * Singleton object mapper instance
     */
    private static ObjectMapper mapper;

    static {

        configure(null, null);
    }

    /**
     * Constructor with limited visibility
     */
    private JsonUtil() {}

    /**
     *
     * @param patternLocalDate
     * @param patternLocalDateTime
     */
    public static void configure(String patternLocalDate, String patternLocalDateTime) {

        mapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDate.class, new JsonLocalDateSerializer(patternLocalDate));
        module.addDeserializer(LocalDate.class, new JsonLocalDateDeserializer(patternLocalDate));
        module.addSerializer(LocalDateTime.class, new JsonLocalDateTimeSerializer(patternLocalDateTime));
        module.addDeserializer(LocalDateTime.class, new JsonLocalDateTimeDeserializer(patternLocalDateTime));

        mapper
            .registerModule(module)
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .findAndRegisterModules();
    }

    /**
     * Returns mapper instance
     * @return
     */
    public static ObjectMapper getMapper() {

        return mapper;
    }

    /**
     * Returns the JSON representation of an object
     * @param object
     * @param prettyPrint
     * @return
     * @throws JsonProcessingException
     */
    public static String of(Object object, boolean... prettyPrint) throws JsonProcessingException {

        if ((prettyPrint.length > 0) && prettyPrint[0]) {

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } else {

            return mapper.writeValueAsString(object);
        }
    }

    /**
     * Deserializes a JSON string to an object
     * @param <T>
     * @param json
     * @param type
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T to(String json, Class<T> type) throws JsonProcessingException {

        return mapper.readValue(json, type);
    }

    /**
     * Deserializes a JSON string to an object
     * @param <T>
     * @param json
     * @param type
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T to(String json, TypeReference<T> type) throws JsonProcessingException {

        return mapper.readValue(json, type);
    }
}
