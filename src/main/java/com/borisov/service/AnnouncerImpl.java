package com.borisov.service;

import com.borisov.infrostrucrure.ObjectFactory;

public class AnnouncerImpl implements Announcer {

    private Recommendator recommendator = ObjectFactory.getFactoryInstance().createInstance(Recommendator.class);

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
