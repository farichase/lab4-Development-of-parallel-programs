package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class StoreMessage implements Serializable {
    private final String packageId;
    private final String jsScripts;
    private final String functionName;
    private final List<Test> tests;

    @JsonCreator
    public StoreMessage(
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
    public String getJsScripts() {
        return this.jsScripts;
    }
    public String getFunctionName() {
        return this.functionName;
    }
    public List<Test> getTests () {
        return this.tests;
    }
}
