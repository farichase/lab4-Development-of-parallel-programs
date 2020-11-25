package mylab4;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor implements AbstractActor {
    private Map<String, ArrayList<Test>> store = new HashMap<>()
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .create()
                .match(Test.class, test -> {
                    if (!this.store.containsKey())
                })

    }
}
