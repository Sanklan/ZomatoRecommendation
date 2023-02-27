package org.example.engine;

import org.example.database.IDatastore;
import org.example.engine.base.BaseRecommendationConfiguration;
import org.example.filter.IFilter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.example.data.*;

public class RecommendationGenerator {
    LinkedList<IFilter> filters;
    private IDatastore datastore;

    public RecommendationGenerator(BaseRecommendationConfiguration configuration, IDatastore datastore) {
        this.filters = (LinkedList<IFilter>) configuration.getFilters();
        this.datastore = datastore;
    }


    public List<String> generateRecommendation(int outputSize, User user) {
        //filters are not required to be mutually exclusive
        HashSet<String> mapped = new HashSet<String>();
        List<String> recommendations = new ArrayList<String>();

        for(IFilter filter:filters) {
            filter.filter(user, datastore).stream()
                    .forEach( restaurantId -> {
                        if (!mapped.contains(restaurantId)) {
                            recommendations.add(restaurantId);
                            mapped.add(restaurantId);
                        }
                    });
            if (recommendations.size() > outputSize) break;
        }
        return recommendations.stream().limit(outputSize).collect(Collectors.toList());
    }
}
