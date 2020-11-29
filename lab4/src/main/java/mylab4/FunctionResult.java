package mylab4;

public class FunctionResult {
    private String packageID;
    private String testName;
    private String result;

    public FunctionResult(String packageID, String testName, String result) {
        this.packageID = packageID;
        this.testName = testName;
        this.result = result;
    }
    public String getPackageID() {
        return this.packageID;
    }
    public String getResult() {
        return this.result;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public void setPackageID() {
        this.packageID = packageID;
    }
    public void setResult() {
        this.result = result;
    }
}
