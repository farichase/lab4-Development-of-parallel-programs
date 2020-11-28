package mylab4;

import java.util.ArrayList;

public class UnitTest {
    public final String packageID;
    public final String jsScripts;
    public final String functionName;
    public final String testName;
    public final String expectedResult;
    public final ArrayList<Integer> params;

    public UnitTest(String packageID, String jsScripts, String functionName,
                    String testName, String expectedResult, ArrayList<Integer> params) {
        this.packageID = packageID;
        this.jsScripts = jsScripts;
        this.functionName = functionName;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
}

