package com.borisov;

import com.borisov.infrostrucrure.ApplicationContext;
import com.borisov.infrostrucrure.ApplicationContextInitialaiser;
import com.borisov.infrostrucrure.ObjectFactory;
import com.borisov.model.Room;
import com.borisov.service.AngryPolicemen;
import com.borisov.service.CoronaDesinfector;
import com.borisov.service.Policemen;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = ApplicationContextInitialaiser.run("com.borisov", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
        context.getInstance(CoronaDesinfector.class).start(new Room());
    }
}
