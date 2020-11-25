package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public class FunctionStore implements Serializable {
    private final String packageId;
    private final String jsScripts;
    private final String functionName;
    private final List<Test> tests;

    @JsonCreator
    public FunctionStore(){
        this.packageId = packageId;
        this.jsScripts = jsScripts;
        this.functionName = functionName;
        this.tests = tests;
    }
}
