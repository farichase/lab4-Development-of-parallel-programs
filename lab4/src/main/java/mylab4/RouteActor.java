package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

public class RouteActor extends AbstractActor {
    private final int NR = 10;

    private ActorRef storeActor;
    private ActorRef testExecutorActor;

    public RouteActor(ActorSystem system) {
        this.storeActor = system.actorOf(StoreActor.props(), "store");
        this.testExecutorActor = system.actorOf(
                new RoundRobinPool(NR)
                        .props(Props.create(TestExecutorActor.class, storeActor))
        );
    }
    private void funcHandler(StoreMessage jsFunc){
        for (Test test : jsFunc.getTests()) {
            test.
        }
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, jsFunc -> funcHandler(jsFunc))
                .match(String.class, msg -> storeActor.forward(msg, getContext()))
                .build();
    }
}
