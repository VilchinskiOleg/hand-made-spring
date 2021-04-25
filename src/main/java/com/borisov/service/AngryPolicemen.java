package com.borisov.service;

public class AngryPolicemen implements Policemen{

    @Override
    public void checkRoom() {
        System.out.println("Всем крышка! Тра-та-та-та!");
    }
}
