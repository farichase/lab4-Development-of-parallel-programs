package mylab4;


import java.util.Map;

public class OutputResult {
    private String id;
    private Map<String, String> res;

    public OutputResult(String id, Map<String, String> res){
        this.id = id;
        this.res = res;
    }
    public String getId() {
        return id;
    }
    public Map<String, String> getRes() {
        return res;
    }
}
