package com.borisov.infrostrucrure;

import com.borisov.service.AngryPolicemen;
import com.borisov.service.Policemen;
import lombok.SneakyThrows;
import java.util.*;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private final Config config;
    private final List<ObjectPostProcessor> objectPostProcessors;

    @SneakyThrows
    private ObjectFactory() {
        config = new JavaConfig("com.borisov", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
        objectPostProcessors = new ArrayList<>();
        for (Class<? extends ObjectPostProcessor> implClass : config.getScanner().getSubTypesOf(ObjectPostProcessor.class)) {
            objectPostProcessors.add(implClass.getDeclaredConstructor().newInstance());
        }
    }

    public static ObjectFactory getFactoryInstance() {
        return instance;
    }



    @SneakyThrows
    public <T> T createInstance(Class<T> type) {
        //Choose implementation (delegate to config):
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        //Create object:
        T implInstance = implClass
                .getDeclaredConstructor()
                .newInstance();

        //Tune object (delegate to post processors):
        objectPostProcessors.forEach(postProcessor -> postProcessor.process(implInstance));

        return implInstance;
    }
}
