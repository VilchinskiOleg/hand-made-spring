package com.borisov.infrostrucrure.postprocessor;

public interface PostProcessor {

    boolean support(Object instance);
    void process(Object instance);
}
