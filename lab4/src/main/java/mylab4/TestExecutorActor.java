package mylab4;

import akka.actor.AbstractActor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    private static final String ENGINE_NAME = "nashorn";
    
    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(UnitTest.class, req -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    engine.eval(req.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    String res = invocable.invokeFunction(req.getFunctionName(), req.getParams().toArray()).toString();
                    int answer = "";
                    if (res.equals(req.getExpectedResult())) answer = "OK";
                    else answer = "NOTOK"

                    return new ;
                })
    }
}
