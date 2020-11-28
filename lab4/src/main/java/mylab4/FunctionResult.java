package mylab4;

public class FunctionResult {
    private String packageID;
    private String result;

    public FunctionResult(String packageID, String result) {
        this.packageID = packageID;
        this.result = result;
    }
    public String getPackageID() {
        return this.packageID;
    }
    public String getResult() {
        return this.result;
    }
    @Override
    public String toString(){
        return ""
    }
}
