package mylab4;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, Map store = new HashMap<>();
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .create()
                .match(StoreFunction.class, test -> {
                    String packageId = test.getPackageId();
                    if (!this.store.containsKey(packageId)){
                        store.put(packageId, new ArrayList<>());
                    }
                    store.get(packageId).add(test);
                })
                .match(String.class, s -> sender().tell(s, self()))
                .build();
    }
    public static Props props() {
        return Props.create(StoreActor.class);
    }
}
