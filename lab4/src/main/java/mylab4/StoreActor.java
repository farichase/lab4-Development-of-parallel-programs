package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {
    private ActorRef storeActor;

    private void funcHandler(StoreMessage jsFunc){
        for (Test test : jsFunc.getTests()) {

        }
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, jsFunc -> funcHandler(jsFunc))

                .build();
    }
}
