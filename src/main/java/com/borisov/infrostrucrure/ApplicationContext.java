package com.borisov.infrostrucrure;

import com.borisov.infrostrucrure.annotation.Singleton;
import com.borisov.infrostrucrure.config.Config;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    @Setter
    private ObjectFactory objectFactory;
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getInstance(Class<T> type) {

        //TODO: Check cash:
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        //TODO: Choose implementation (delegate to config):
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        //TODO: Create instance (delegate to factory):
        T instance = objectFactory.createInstance(implClass);

        //TODO: Cashing instance if has @Singleton:
        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, instance);
        }

        return instance;
    }
}
