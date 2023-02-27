package org.example.database;

import org.example.database.impl.InMemoryStore;
import org.example.exception.DatabaseException;

public class DatabaseFactory {
    public static IDatastore getDatabase(DatabaseType type) {
        switch (type) {
            case InMemoryStore: return new InMemoryStore();
        }
        throw new DatabaseException();
    }
}
