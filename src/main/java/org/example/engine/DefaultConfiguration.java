package org.example.engine;

import org.example.engine.base.BaseRecommendationConfiguration;
import org.example.filter.Filters;
import org.example.filter.IFilter;
import org.example.filter.Filters.*;

import java.util.ArrayList;

public class DefaultConfiguration extends BaseRecommendationConfiguration {
    @Override
    protected void buildConfiguration() {
        this.filters = new ArrayList<IFilter>();
        filters.add(Filters.One);
        filters.add(Filters.two);
        filters.add(Filters.three);
        filters.add(Filters.four);
        filters.add(Filters.five);
        filters.add(Filters.six);
        filters.add(Filters.seven);
        filters.add(Filters.eight);
        filters.add(Filters.nine);

    }
}
