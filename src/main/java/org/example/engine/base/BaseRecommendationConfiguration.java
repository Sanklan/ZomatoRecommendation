package org.example.engine.base;

import org.example.filter.IFilter;
import java.util.List;

public abstract class BaseRecommendationConfiguration {
    protected List<IFilter> filters;
    protected abstract void buildConfiguration();

    public List<IFilter> getFilters() {
        if (filters == null || filters.size() == 0) buildConfiguration();
        return filters;
    }
}
