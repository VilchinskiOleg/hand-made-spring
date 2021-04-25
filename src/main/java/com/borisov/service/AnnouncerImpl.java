package com.borisov.service;

import com.borisov.infrostrucrure.annotation.InjectObject;

public class AnnouncerImpl implements Announcer {

    @InjectObject
    private Recommendator recommendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
