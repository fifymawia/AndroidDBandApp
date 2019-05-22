package com.daenjel.ilearn.Model;

public class StrandsModel {

    private String lottie;
    private String strands;

    public StrandsModel() {
    }

    public StrandsModel(String lottie, String strands) {
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
