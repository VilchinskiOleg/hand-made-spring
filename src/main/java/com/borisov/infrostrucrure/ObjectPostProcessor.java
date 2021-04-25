package com.borisov.infrostrucrure;

public interface ObjectPostProcessor {

    boolean support(Object instance);
    void process(Object instance);
}
