package com.borisov.infrostrucrure;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private ObjectFactory objectFactory = ObjectFactory.getFactoryInstance();
    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    public <T> T getInstance(Class<T> type) {
        return null;
    }
}
