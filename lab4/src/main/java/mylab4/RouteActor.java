package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.japi.pf.ReceiveBuilder;

public class RouteActor extends AbstractActor {
    private ActorRef storeActor;
    private ActorRef testExecutorActor;

    public RouteActor(ActorSystem system) {
        this.storeActor = system.actorOf(StoreActor.props(), "store");
    }
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
