package com.borisov.infrostrucrure;

import com.borisov.service.AngryPolicemen;
import com.borisov.service.Policemen;
import lombok.SneakyThrows;
import java.util.*;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private Config config;
    private ObjectPostProcessor postProcessor = new InjectPropertiesObjectPostProcessor("properties.properties");

    private ObjectFactory() {
        config = new JavaConfig("com.borisov.service", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
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
        postProcessor.process(implInstance, implClass);

        return implInstance;
    }
}
