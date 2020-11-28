package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class StoreFunction implements Serializable {
    private final String packageId;
    private final String jsScripts;
    private final String functionName;

    @JsonCreator
    public StoreFunction(
            @JsonProperty("packageId") String packageId,
            @JsonProperty("jsScripts") String jsScripts,
            @JsonProperty("functionName") String functionName
    ){
        this.packageId = packageId;
        this.jsScripts = jsScripts;
        this.functionName = functionName;
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
}
