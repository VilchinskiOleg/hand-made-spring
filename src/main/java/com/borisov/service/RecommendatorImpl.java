package com.borisov.service;

import com.borisov.infrostrucrure.annotation.InjectProperty;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("защитите себя от короны, пейте " + alcohol);
    }
}
