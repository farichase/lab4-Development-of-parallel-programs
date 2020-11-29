package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class StoreFunction {
    @JsonProperty("packageId")
    private final String packageId;
    @JsonProperty("jsScript")
    private final String jsScripts;
    @JsonProperty("functionName")
    private final String functionName;
    @JsonProperty("tests")
    private final ArrayList<Test> tests;

    @JsonCreator
    public StoreFunction(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScript") String jsScripts,
            @JsonProperty("functionName") String functionName,
            @JsonProperty("tests") ArrayList<Test> tests
    ){
        this.packageId = packageId;
        this.jsScripts = jsScripts;
        this.functionName = functionName;
        this.tests = tests;
    }
    public String getPackageId() {
        return this.packageId;
    }
    public String getJsScripts() {
        return this.jsScripts;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    public ArrayList<Test> getTests() {
        return tests;
    }
}
