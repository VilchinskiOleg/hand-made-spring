package com.borisov.infrostrucrure.postprocessor;

import com.borisov.infrostrucrure.ApplicationContext;

public interface PostProcessor {

    boolean support(Object instance);
    void process(Object instance, ApplicationContext context);
}
