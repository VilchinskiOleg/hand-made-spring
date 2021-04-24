package com.borisov;

import com.borisov.model.Room;
import com.borisov.service.CoronaDesinfector;

public class Main {
    public static void main(String[] args) {
        new CoronaDesinfector().start(new Room());
    }
}
