package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, ArrayList<String>> store = new HashMap<>();
    private FunctionResult printerID(String id) {
        if (store.containsKey(id)){
            ArrayList<String> funcArray = store.get(id);
            for (String item : funcArray) {
                return new FunctionResult(id, item);
            }
        }
        return new FunctionResult(id, "no tests");
    }
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .create()
                .match(Test.class, test -> {
                    String packageId = test.getFunc().getPackageId();
                    if (!this.store.containsKey(packageId)){
                        store.put(packageId, new ArrayList<>());
                    }
                    store.get(packageId).add(test);
                })
                .match(String.class, id -> sender().tell(printerID(id), ActorRef.noSender()))
                .build();
    }
    public static Props props() {
        return Props.create(StoreActor.class);
    }
}
