package mylab4;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, Map<String, String>> store = new HashMap<>();
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .create()
                .match(Test.class, test -> {
                    String packageId = test.getOnePackage().getPackageId();
                    if (!this.store.containsKey(packageId)){
                        HashMap<>() tests = new HashMap<>();
                        store.put(packageId, new HashMap<>());
                    }
                    store.get(packageId).put(test.getTestName(), test.getExpectedResult());
                })
                .match(String.class, s -> sender().tell(s, self()))
                .build();
    }
    public static Props props() {
        return Props.create(StoreActor.class);
    }
}
