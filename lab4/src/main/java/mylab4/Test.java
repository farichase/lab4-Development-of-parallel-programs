package mylab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Test implements Serializable {
    private final String testName;
    private final String expectedResult;
    private final Integer[] params;
    private StoreFunction onePackage;

    @JsonCreator
    public Test(
        @JsonProperty("testName") String testName,
        @JsonProperty("expectedResult") String expectedResult,
        @JsonProperty("params") Integer[] params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
    public String getTestName() {
        return this.testName;
    }
    public String getExpectedResult() {
        return this.expectedResult;
    }
    public Integer[] getParams() {
        return this.params;
    }
    public StoreFunction getOnePackage(){
        return this.onePackage;
    }
    public void setStoreMessage(StoreFunction msg){
        this.onePackage =
    }
}
