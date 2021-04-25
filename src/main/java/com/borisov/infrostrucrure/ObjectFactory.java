package com.borisov.infrostrucrure;

import com.borisov.infrostrucrure.postprocessor.PostProcessor;
import com.borisov.service.AngryPolicemen;
import com.borisov.service.Policemen;
import lombok.SneakyThrows;
import java.util.*;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private final Config config;
    private final List<PostProcessor> postProcessors;

    @SneakyThrows
    private ObjectFactory() {
        config = new JavaConfig("com.borisov", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
        postProcessors = new ArrayList<>();
        for (Class<? extends PostProcessor> implClass : config.getScanner().getSubTypesOf(PostProcessor.class)) {
            postProcessors.add(implClass.getDeclaredConstructor().newInstance());
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
        postProcessors.forEach(postProcessor -> {
            if (postProcessor.support(implInstance)) {
                postProcessor.process(implInstance);
            }
        });

        return implInstance;
    }
}
