package com.borisov;

import com.borisov.infrostrucrure.ApplicationContext;
import com.borisov.infrostrucrure.ObjectFactory;
import com.borisov.model.Room;
import com.borisov.service.AngryPolicemen;
import com.borisov.service.CoronaDesinfector;
import com.borisov.service.Policemen;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CoronaDesinfector coronaDesinfector = ObjectFactory.getFactoryInstance().createInstance(CoronaDesinfector.class);
        coronaDesinfector.start(new Room());
    }
}
