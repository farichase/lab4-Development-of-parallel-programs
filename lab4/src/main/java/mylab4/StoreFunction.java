package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;

public class StoreFunction implements Serializable {
    private final String packageId;
    private final String jsScripts;
    private final String functionName;
    private final ArrayList<Test> tests;

    @JsonCreator
    public StoreFunction(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScripts") String jsScripts,
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
