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
                    String packageId = test.getOnePackage().getPackageId();
                    if (!this.store.containsKey(packageId)){
                        store.put(packageId, new ArrayList<>());
                    } else {
                        this.store.get(packageId).add(test);
                    }
                })
                .match()
                .build();

    }
}
