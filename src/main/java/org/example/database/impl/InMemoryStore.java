package org.example.database.impl;

import org.example.data.CostBracket;
import org.example.data.Cuisine;
import org.example.data.Restaurant;
import org.example.data.User;
import org.example.database.IDatastore;
import static org.example.Constants.TwoDayDenominator;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryStore implements IDatastore {
    private List<User> users;
    private List<Restaurant> restaurants;
    private InMemoryStore database;

    /**
     * @return
     */
    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    /**
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        return restaurants.stream().filter(restaurant -> restaurant.getRestaurantId().equalsIgnoreCase(restaurantId))
                .findFirst().orElse(null);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        if (getUser(user.getName()) != null) return false;
        return users.add(user);
    }

    /**
     * @param restaurant
     * @return
     */
    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        if (restaurantExist(restaurant)) return false;
        restaurants.add(restaurant);
        return true;
    }

    /**
     * @param user
     * @param restaurant
     * @return
     */
    @Override
    public boolean updateOrderMetricForUser(User user, Restaurant restaurant) {
        if (!restaurantExist(restaurant) || getUser(user.getName()) == null) return false;
        return user.updateOrderMetric(restaurant);
    }

    @Override
    public List<Restaurant> filterRestaurant(final Cuisine cuisine, final CostBracket cost) {
        return restaurants.stream()
                .filter( restaurant -> restaurant.getCuisine() == cuisine && restaurant.getCostBracket() == cost)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> filterRestaurant(final float rating) {
        return restaurants.stream()
                .filter( restaurant -> restaurant.getRating() == rating)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> filterRestaurantNewFour(int limit) {
        Date today = new Date();

        return restaurants.stream()
                .filter( restaurant ->
                    Math.abs(
                            restaurant.getOnboardedTime().getTime() - today.getTime()) / TwoDayDenominator < 48.0)
                .sorted(new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant o1, Restaurant o2) {
                        return ((Double)Math.ceil(o2.getRating() - o1.getRating())).intValue();
                    }
                }).limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> filterRestaurantRatingGreaterThan(Cuisine cuisine, CostBracket cost, float rating) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getCostBracket() == cost && restaurant.getCuisine() == cuisine)
                .filter(restaurant -> restaurant.getRating() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> filterRestaurantRatingLessThan(Cuisine cuisine, CostBracket cost, float rating) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getCostBracket() == cost && restaurant.getCuisine() == cuisine)
                .filter(restaurant -> restaurant.getRating() < rating)
                .collect(Collectors.toList());
    }

    private boolean restaurantExist(final Restaurant restaurant) {
        return restaurants.stream()
                .anyMatch(rest -> rest.getRestaurantId().equalsIgnoreCase(restaurant.getRestaurantId()));
    }

}
