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
    private final int TIMEOUT = 4000;
    private ActorRef storeActor;
    private ActorRef testExecutorActor;

    public RouteActor(ActorSystem system) {
        this.testExecutorActor = system.actorOf(
                new RoundRobinPool(NR)
                        .props(Props.create(TestExecutorActor.class))
        );
        this.storeActor = system.actorOf(StoreActor.props(), "store");
    }
    private void funcHandler(StoreFunction func){
        for (int i = 0; i < func.getTests().size(); i++) {
            testExecutorActor.tell(
                    new UnitTest(func.getPackageId(),
                                func.getJsScripts(),
                                func.getFunctionName(),
                                func.getTests().get(i).getTestName(),
                                func.getTests().get(i).getExpectedResult(),
                                func.getTests().get(i).getParams()), getSelf()
            );
        }
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreFunction.class, jsFunc -> funcHandler(jsFunc))
                .match(String.class, id ->{
                    Future<Object> res = Patterns.ask(storeActor, id, TIMEOUT);
                    getSender().tell(res, self());
                })
                .build();
    }
}
