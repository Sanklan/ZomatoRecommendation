package org.example;

import org.example.data.Restaurant;
import org.example.data.User;
import org.example.database.DatabaseFactory;
import org.example.database.DatabaseType;
import org.example.database.IDatastore;
import org.example.engine.DefaultConfiguration;
import org.example.engine.RecommendationGenerator;

import java.util.*;
public class Zomato {
    private IDatastore datastore;
    private RecommendationGenerator recommendationGeneratorWithDefaultConfig;


    public Zomato(DatabaseType type) {
        this.datastore = DatabaseFactory.getDatabase(type);
        recommendationGeneratorWithDefaultConfig = new RecommendationGenerator(new DefaultConfiguration(), datastore);
    }

    public boolean addUser(User user) {
        return datastore.addUser(user);
    }

    public boolean addRestaurant(Restaurant restaurant) {
        return datastore.addRestaurant(restaurant);
    }

    public boolean updateUserMetric(User user, Restaurant restaurant) {
        return datastore.updateOrderMetricForUser(user, restaurant);
    }

    public User getUser(String name ) {
        return datastore.getUser(name);
    }

    public Restaurant getRestaurant(String restaurantId) {
        return datastore.getRestaurant(restaurantId);
    }

    public List<String> getRecommendationForUser(User user) {
        return recommendationGeneratorWithDefaultConfig
                .generateRecommendation(Constants.MAX_OUTPUT_SIZE, user);
    }
}
