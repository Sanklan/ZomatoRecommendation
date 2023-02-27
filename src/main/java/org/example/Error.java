package org.example;

import lombok.Getter;

public enum Error {
    DatabaseError(1, "Issue while retrieve data from source")
    ;
    private Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
    int code;
    String message;

    public String toString() {
        return code + " " + message;
    }
}
