package mylab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {
    private void msgHandler(FunctionStore msg){
        for (Test test : msg.getTests()) {
            
        }
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(FunctionStore.class, message -> msgHandler(message))

                .build();
    }
}
