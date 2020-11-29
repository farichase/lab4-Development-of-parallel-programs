package mylab4;

import akka.actor.AbstractActor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutorActor extends AbstractActor {
    private static final String ENGINE_NAME = "nashorn";
    private static final String FAIL = "fail";
    private static final String SUCCESS = "success";

    @Override
    public Receive createReceive(){
        return receiveBuilder()
                .match(UnitTest.class, req -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    engine.eval(req.getJsScript());
                    Invocable invocable = (Invocable) engine;
                    String res = invocable.invokeFunction(req.getFunctionName(), req.getParams().toArray()).toString();
                    String answer;
                    if (res.equals(req.getExpectedResult())) answer = SUCCESS;
                    else answer = FAIL;
                    sender().tell(new FunctionResult(req.getPackageId(), answer), self());
                })
                .build();
    }
}
