package com.daenjel.ilearn;

public class Sub_Strand_Model {
    private String lottie;
    private String strands;

    public Sub_Strand_Model() {
    }

    public Sub_Strand_Model(String lottie, String strands) {
        this.lottie = lottie;
        this.strands = strands;
    }

    public String getLottie() {
        return lottie;
    }

    public void setLottie(String lottie) {
        this.lottie = lottie;
    }

    public String getStrands() {
        return strands;
    }

    public void setStrands(String strands) {
        this.strands = strands;
    }
}
