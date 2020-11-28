package mylab4;

public class FunctionResult {
    private String packageID;
    private boolean result;

    public FunctionResult(String packageID, boolean result) {
        this.packageID = packageID;
        this.result = result;
    }
    public String getPackageID() {
        return this.packageID;
    }
    public boolean getResult() {
        return this.result;
    }
    public void setPackageID() {
        this.packageID = packageID;
    }
    public void setResult() {
        this.result = result;
    }
}
