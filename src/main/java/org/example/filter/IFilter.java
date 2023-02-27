package org.example.filter;

import java.util.List;
import org.example.data.User;
import org.example.database.IDatastore;

@FunctionalInterface
public interface IFilter {
    public List<String> filter(User user, IDatastore datastore);
}
