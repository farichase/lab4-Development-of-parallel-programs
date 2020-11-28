package mylab4;

import java.util.ArrayList;

public class UnitTest {
    private final String packageID;
    private final String jsScript;
    private final String functionName;
    private final String testName;
    private final String expectedResult;
    private final ArrayList<Integer> params;

    public UnitTest(String packageID, String jsScript, String functionName,
                    String testName, String expectedResult, ArrayList<Integer> params) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
    public String getPackageID(){
        return this.packageID;
    }
    public String getJsScript() {
        return this.jsScript;
    }
    public String getFunctionName(){
        return this.functionName;
    }
    public String getTestName() {
        return this.testName;
    }
    public String getExpectedResult() {
        return this.expectedResult;
    }
    public ArrayList<Integer> getParams(){
        return this.params;
    }
}

