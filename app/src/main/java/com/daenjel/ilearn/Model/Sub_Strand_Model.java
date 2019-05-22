package com.daenjel.ilearn.Model;

public class Sub_Strand_Model {
    private String tracker;
    private String strands;

    public Sub_Strand_Model() {
    }

    public Sub_Strand_Model( String strands,String tracker) {
        this.tracker = tracker;
        this.strands = strands;
    }

    public String getTracker() {
        return tracker;
    }

    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getStrands() {
        return strands;
    }

    public void setStrands(String strands) {
        this.strands = strands;
    }
}
