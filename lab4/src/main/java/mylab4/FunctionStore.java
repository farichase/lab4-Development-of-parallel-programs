package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class FunctionStore implements Serializable {
    private final String packageId;
    private final String jsScripts;
    private final String functionName;
    private final List<Test> tests;

    @JsonCreator
    public FunctionStore(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScripts") String jsScripts,
            @JsonProperty("functionName") String functionName,
            @JsonProperty("tests") List<Test> tests
    ){
        this.packageId = packageId;
        this.jsScripts = jsScripts;
        this.functionName = functionName;
        this.tests = tests;
    }
    public String getPackageId() {
        return this.packageId;
    }
    public String jsScripts() {
        return this.jsScripts;
    }
    public String functionName() {
        return this.functionName;
    }
    public List<Test> getTests () {
        return this.tests;
    }
}
