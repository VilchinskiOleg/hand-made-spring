package com.borisov.service;

import com.borisov.infrostrucrure.annotation.InjectObject;
import com.borisov.model.Room;

public class CoronaDesinfector {

    @InjectObject
    private Announcer announcer;
    @InjectObject
    private Policemen policemen;

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
