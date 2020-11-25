package mylab4;

import akka.actor.AbstractActor;

import java.util.HashMap;

public class StoreActor implements AbstractActor {
    private Map<Map<String, String>> store = new HashMap<>()
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .create()
                .match(Test.class, test -> add)

    }
}
