package mylab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, Map<String, String>> store = new HashMap<>();
    private FunctionResult printerID(String id) {
        return new FunctionResult(id, store.get(id).get(0), store.get(id).get(1));
    }
    @Override
    public Receive createReceive(){
        return ReceiveBuilder.create()
                .match(
                        FunctionResult.class,
                        item -> {
                            if (!store.containsKey(item.getPackageID())) {
                                store.put(item.getPackageID(), new HashMap<>());
                            }
                            store.get(item.getPackageID()).put(item.getTestName(), item.getResult());
                })
                .match(String.class, id -> sender().tell(printerID(id), self()))
                .build();
    }
}
