package org.example.database;
import java.util.*;
import org.example.data.*;
public interface IDatastore {
    public List<Restaurant> getAllRestaurants();
    public List<User> getAllUsers();
    public User getUser(String name);
    public Restaurant getRestaurant(String restaurantId);
    public boolean addUser(User user);
    public boolean addRestaurant(Restaurant restaurant);
    public boolean updateOrderMetricForUser(User user, Restaurant restaurant);

    public List<Restaurant> filterRestaurant(Cuisine cuisine, CostBracket cost);

    public List<Restaurant> filterRestaurant(float rating);
    public List<Restaurant> filterRestaurantNewFour(int limit);
    public List<Restaurant> filterRestaurantRatingGreaterThan(Cuisine cuisine, CostBracket cost, float rating);
    public List<Restaurant> filterRestaurantRatingLessThan(Cuisine cuisine, CostBracket cost, float rating);

}
