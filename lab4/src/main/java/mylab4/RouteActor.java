package mylab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import scala.concurrent.Future;

public class RouteActor extends AbstractActor {
    private final int NR = 10;
    private ActorRef storeActor;
    private ActorRef testExecutorActor;

    public RouteActor(ActorSystem system) {
        this.storeActor = system.actorOf(Props.create(StoreActor.class), "store");
        this.testExecutorActor = system.actorOf(
                new RoundRobinPool(NR)
                        .props(Props.create(TestExecutorActor.class))
        );
    }
    private void funcHandler(StoreFunction func){

    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreFunction.class, jsFunc -> funcHandler(jsFunc))
                .match(String.class, msg -> storeActor.forward(msg, getContext()))
                .build();
    }
}
