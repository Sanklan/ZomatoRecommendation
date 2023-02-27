package org.example.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class BaseException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BaseException(String message) {
        super(message);
        log.error(message);
        StringWriter stringWriter = new StringWriter();
        this.printStackTrace(new PrintWriter(stringWriter));
        log.error(stringWriter.toString());
    }
}
