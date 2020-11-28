package mylab4;

import akka.actor.AbstractActor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(UnitTest.class, req -> {
                    ScriptEngine engine = new ScriptEngineManager() {
                    }
                })
    }
}
