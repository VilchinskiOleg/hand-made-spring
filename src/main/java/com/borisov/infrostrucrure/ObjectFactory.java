package com.borisov.infrostrucrure;

import lombok.SneakyThrows;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private Config config = new JavaConfig("com.borisov.service");

    private ObjectFactory() { }

    public static ObjectFactory getFactoryInstance() {
        return instance;
    }



    @SneakyThrows
    public <T> T createInstance(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        return implClass
                .getDeclaredConstructor()
                .newInstance();
    }
}
