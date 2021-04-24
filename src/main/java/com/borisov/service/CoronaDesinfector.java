package com.borisov.service;

import com.borisov.infrostrucrure.ObjectFactory;
import com.borisov.model.Room;

public class CoronaDesinfector {

    private Announcer announcer = ObjectFactory.getFactoryInstance().createInstance(Announcer.class);
    private Policemen policemen = ObjectFactory.getFactoryInstance().createInstance(Policemen.class);

    public void start(Room room){
        announcer.announce("начинаем дезинфекцию, покиньте помещение!");
        policemen.checkRoom();
        desinfect(room);
        announcer.announce("комната продизенфицирована, можете зайти");
    }

    private void desinfect(Room room) {
        System.out.println("зачитывается молитва: 'корона изыди!' - молитва прочитана, вирус низвергнут в ад");
    }
}
