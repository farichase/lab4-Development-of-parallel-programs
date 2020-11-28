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
    private void funcHandler(StoreFunction func){
        for (int i = 0; i < func.getTests().size(); i++) {
            testExecutorActor.tell(
                    new UnitTest(func.getPackageId(),
                                func.getJsScripts(),
                                func.getFunctionName(),
                                func.getTests().get(i).getTestName(),
                                func.getTests().get(i).getExpectedResult(),
                            )
            );
        }
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreFunction.class, jsFunc -> funcHandler(jsFunc))
                .match(String.class, msg -> storeActor.forward(msg, getContext()))
                .build();
    }
}
