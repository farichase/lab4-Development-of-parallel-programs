package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, Map<String, String>> store = new HashMap<>();
    private FunctionResult printerID(String id) {
        if (store.containsKey(id)){
            ArrayList<String> funcArray = store.get(id);
            for (int i = 0; i < funcArray.size(); i++) {
                return new FunctionResult(id, funcArray.get(i));
            }
        }
        return new FunctionResult(id, "no tests");
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
                            store.get(item.getPackageID()).put(item.);
                })
                .match(String.class, id -> sender().tell(printerID(id), ActorRef.noSender()))
                .build();
    }
}
