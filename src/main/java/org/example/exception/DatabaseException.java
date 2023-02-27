package org.example.exception;

import org.example.Error;

public class DatabaseException extends BaseException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     */
    public DatabaseException() {
        super(Error.DatabaseError.toString());
    }
}
