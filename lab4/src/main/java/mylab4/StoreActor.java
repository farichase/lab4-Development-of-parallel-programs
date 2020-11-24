package mylab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {
    private 
    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create()
                .match(FunctionStore.class, message -> msgHandler(message))
                .match()
                .build();
    }
}
