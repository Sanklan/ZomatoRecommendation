package org.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.example.Constants;
import org.example.data.CostBracket;
import org.example.data.Cuisine;
import org.example.data.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Filters {
    /**
     * 1. Featured restaurants of primary cuisine and primary cost bracket.
     * If none, then all featured restaurants of primary cuisine, secondary cost and secondary cuisine, primary cost
     */
    public static final IFilter One = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 1");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();
        if (topCuisine.size() == 0 || topCost.size() == 0) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }

        List<String> ret = datastore.filterRestaurant(topCuisine.get(0), topCost.get(0)).stream()
                .map(Restaurant::getRestaurantId).collect(Collectors.toList());
        if (ret.size() == 0) {
            /* If none, then all featured restaurants of primary cuisine, secondary cost and secondary cuisine, primary cost */
            for(int i = 1; i < 3; i++) {
                if (i < topCost.size())
                {
                    List<String> temp =   datastore.filterRestaurant(topCuisine.get(0), topCost.get(i)).stream()
                            .map(Restaurant::getRestaurantId).collect(Collectors.toList());
                    if (temp != null && temp.size() > 0) ret.addAll(temp);
                }
                if ( i < topCuisine.size()) {
                    List<String> temp =  datastore.filterRestaurant(topCuisine.get(i), topCost.get(0)).stream()
                            .map(Restaurant::getRestaurantId).collect(Collectors.toList());
                    if (temp != null && temp.size() > 0) ret.addAll(temp);
                }
            }
        }
        return ret;
    };

    /**
     * 2
     * All restaurants of Primary cuisine, primary cost bracket with rating >= 4
     */
    public static final IFilter two = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 2");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();
        if (topCuisine.size() == 0 || topCost.size() == 0) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }
        return datastore.filterRestaurantRatingGreaterThan(topCuisine.get(0), topCost.get(0), 4).stream()
                .map(Restaurant::getRestaurantId).collect(Collectors.toList());
    };

    /**
     * 3
     * All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
     */
    public static final IFilter three = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 3");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();

        if (topCuisine.size() == 0 || topCost.size() < 2) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }

        List<String> ret = new ArrayList<String>();
        if (topCost.size() > 1)
            datastore.filterRestaurantRatingGreaterThan(topCuisine.get(0), topCost.get(1), (float)4.5).stream()
                .map(Restaurant::getRestaurantId).forEach(ret::add);
        if (topCost.size() > 2)
            datastore.filterRestaurantRatingGreaterThan(topCuisine.get(0), topCost.get(2), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        return ret;
    };
    /**
     * 4
     * All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
     */
    public static final IFilter four = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 4");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();

        if (topCost.size() == 0 || topCuisine.size() < 2) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }

        List<String> ret = new ArrayList<String>();
        if (topCuisine.size() > 1)
            datastore.filterRestaurantRatingGreaterThan(topCuisine.get(1), topCost.get(0), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        if (topCuisine.size() > 2)
            datastore.filterRestaurantRatingGreaterThan(topCuisine.get(2), topCost.get(0), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        return ret;
    };
    /**
     * 5
     * Top 4 newly created restaurants by rating
     */
    public static final IFilter five = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 5");
        return datastore.filterRestaurantNewFour(4).stream()
                .map(Restaurant::getRestaurantId).collect(Collectors.toList());
    };
    /**
     * 6
     * All restaurants of Primary cuisine, primary cost bracket with rating < 4
     */
    public static final IFilter six = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 6");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();
        if (topCuisine.size() == 0 || topCost.size() == 0) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }
        return datastore.filterRestaurantRatingLessThan(topCuisine.get(0), topCost.get(0), 4).stream()
                .map(Restaurant::getRestaurantId).collect(Collectors.toList());
    };
    /**
     * 7
     * All restaurants of Primary cuisine, secondary cost bracket with rating < 4.5
     */
    public static final IFilter seven = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 7");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();

        if (topCuisine.size() == 0 || topCost.size() < 2) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }

        List<String> ret = new ArrayList<String>();
        if (topCost.size() > 1)
            datastore.filterRestaurantRatingLessThan(topCuisine.get(0), topCost.get(1), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        if (topCost.size() > 2)
            datastore.filterRestaurantRatingLessThan(topCuisine.get(0), topCost.get(2), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        return ret;
    };
    /**
     * 8
     * All restaurants of secondary cuisine, primary cost bracket with rating < 4.5
     */
    public static final IFilter eight = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 8");
        List<Cuisine> topCuisine = user.getTopCuisine();
        List<CostBracket> topCost = user.getTopCost();

        if (topCost.size() == 0 || topCuisine.size() < 2) {
            log.info(Constants.USER_PROFILE_INCOMPLETE);
            return new ArrayList<String>();
        }

        List<String> ret = new ArrayList<String>();
        if (topCuisine.size() > 1)
            datastore.filterRestaurantRatingLessThan(topCuisine.get(1), topCost.get(0), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        if (topCuisine.size() > 2)
            datastore.filterRestaurantRatingLessThan(topCuisine.get(2), topCost.get(0), (float)4.5).stream()
                    .map(Restaurant::getRestaurantId).forEach(ret::add);
        return ret;
    };

    /**
     * 9
     * All restaurants of any cuisine, any cost bracket
     */
    public static final IFilter nine = (user, datastore) -> {
        log.info(Constants.FILTERING_ON + "Filter 9");
        return datastore.getAllRestaurants().stream().limit(Constants.MAX_OUTPUT_SIZE)
                .map(Restaurant::getRestaurantId).collect(Collectors.toList());
    };
}
