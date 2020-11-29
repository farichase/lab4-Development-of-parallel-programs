package mylab4;

import java.util.ArrayList;

public class OutputResult {
    private String id;
    private ArrayList<String> res;

    public OutputResult(String id, ArrayList<String> res){
        this.id = id;
        this.res = res;
    }
    public String getId() {
        return id;
    }
    public ArrayList<String> getRes() {
        return res;
    }
}
