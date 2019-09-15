package com.article.config.holder;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RemoRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
