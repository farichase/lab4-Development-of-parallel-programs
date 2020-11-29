package mylab4;

import java.util.ArrayList;

public class UnitTest {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final String testName;
    private final String expectedResult;
    private final ArrayList<Integer> params;

    public UnitTest(String packageId, String jsScript, String functionName,
                    String testName, String expectedResult, ArrayList<Integer> params) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
    public String getPackageId(){
        return this.packageId;
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
    public static ArrayList<UnitTest> funcHandler(StoreFunction func){
        ArrayList<UnitTest> tests = new ArrayList<>();
        for (int i = 0; i < func.getTests().size(); i++) {
            tests.add(
                    new UnitTest(func.getPackageId(),
                            func.getJsScripts(),
                            func.getFunctionName(),
                            func.getTests().get(i).getTestName(),
                            func.getTests().get(i).getExpectedResult(),
                            func.getTests().get(i).getParams())
            );
        }
        return tests;
    }
}

