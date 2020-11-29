package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    private Map<String, ArrayList<String>> store = new HashMap<>();
    private FunctionResult printerID(String id) {
        if (store.containsKey(id)){
            ArrayList<String> funcArray = store.get(id);
            for (int i = 0; i < funcArray.size(); i++) {
                System.out.println(8);
                return new FunctionResult(id, funcArray.get(i));
            }
        }
        System.out.println(7);
        return new FunctionResult(id, "no tests");
    }
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(
                        FunctionResult.class,
                        item -> {
                            ArrayList<String> funcArray;
                            if (store.containsKey(item.getPackageID())) {
                                funcArray = store.get(item.getPackageID());
                            } else {
                                funcArray = new ArrayList<>();
                            }
                            funcArray.add(item.getResult());
                            store.put(item.getPackageID(), funcArray);
                })
                .match(String.class, id -> sender().tell(printerID(id), ActorRef.noSender()))
                .build();
    }
}
