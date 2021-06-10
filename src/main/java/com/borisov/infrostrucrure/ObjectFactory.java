package com.borisov.infrostrucrure;

import com.borisov.infrostrucrure.postprocessor.PostProcessor;
import lombok.SneakyThrows;
import java.util.*;

public class ObjectFactory {

    private static ObjectFactory instance;
    private final List<PostProcessor> postProcessors = new ArrayList<>();
    private ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends PostProcessor> implClass : context.getConfig().getScanner().getSubTypesOf(PostProcessor.class)) {
            postProcessors.add(implClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createInstance(Class<T> type) {

        //TODO: Create object:
        T instance = type
                .getDeclaredConstructor()
                .newInstance();

        //TODO: Tune object (delegate to post processors):
        postProcessors.forEach(postProcessor -> {
            if (postProcessor.support(instance)) {
                postProcessor.process(instance, context);
            }
        });

        return instance;
    }
}
