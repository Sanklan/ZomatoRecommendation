package org.example.data;
import lombok.Getter;

import java.util.Date;

@Getter
public class Restaurant {
    private String restaurantId;
    private Cuisine cuisine;
    private CostBracket costBracket;
    private float rating;
    private boolean isRecommended;
    private Date onboardedTime;

    public Restaurant(String restaurantId, Cuisine cuisine, CostBracket costBracket, Date onboardedTime) {
        this.restaurantId = restaurantId;
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.onboardedTime = onboardedTime;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setRecommended(boolean recommended) {
        this.isRecommended = recommended;
    }
}
