package mylab4;

import akka.actor.AbstractActor;

public class TestExecutorActor extends AbstractActor {
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(UnitTest.class, item -> {
                    
                })
    }
}
