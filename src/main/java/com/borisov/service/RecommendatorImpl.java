package com.borisov.service;

import com.borisov.infrostrucrure.annotation.InjectProperty;
import com.borisov.infrostrucrure.annotation.Singleton;

@Singleton
public class RecommendatorImpl implements Recommendator {

    @InjectProperty(value = "wisky")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("защитите себя от короны, пейте " + alcohol);
    }
}
