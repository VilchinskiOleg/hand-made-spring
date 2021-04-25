package com.borisov.infrostrucrure;

public interface ObjectPostProcessor {

    void process(Object instance, Class<?> type);
}
