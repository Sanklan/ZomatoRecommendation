package org.example.data;

import lombok.Getter;
import org.example.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class User {
    @Getter
    String name;
    private HashMap<Cuisine, Integer> cuisinesTracking;
    private HashMap<CostBracket, Integer> costTracking;

    public User(String name) {
        this.name = name;
        this.cuisinesTracking = new HashMap<Cuisine, Integer>();
        this.costTracking = new HashMap<CostBracket, Integer>();

    }

    public boolean updateOrderMetric(Restaurant restaurant) {
        Cuisine cuisine = restaurant.getCuisine();
        CostBracket cost = restaurant.getCostBracket();
        cuisinesTracking.put(cuisine, cuisinesTracking.getOrDefault(cuisine, 0) + 1);
        costTracking.put(cost, costTracking.getOrDefault(cost, 0) + 1);
        return true;
    }


    public List<Cuisine> getTopCuisine() {
        return  cuisinesTracking.entrySet().stream().sorted(new Comparator<Map.Entry<Cuisine, Integer>>() {
            @Override
            public int compare(Map.Entry<Cuisine, Integer> o1, Map.Entry<Cuisine, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public List<CostBracket> getTopCost() {
        return costTracking.entrySet().stream().sorted(new Comparator<Map.Entry<CostBracket, Integer>>() {
            @Override
            public int compare(Map.Entry<CostBracket, Integer> o1, Map.Entry<CostBracket, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        }).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    
}
