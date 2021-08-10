package io.centipod.jackson.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception wrapper class
 *
 * @author Christian Schuit, Centipod B.V., copyright 2016-2021
 */
public class JsonParsingException extends JsonProcessingException {

    /**
     * Class version
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param msg
     * @param rootCause
     */
    public JsonParsingException(String msg, Throwable rootCause) {

        super(msg, rootCause);
    }
}
