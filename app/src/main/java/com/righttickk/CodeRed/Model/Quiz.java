package com.righttickk.CodeRed.Model;

public class Quiz {

    private String rbGroup1;
    private String rbGroup2;
    private String rbGroup3;
    private String rbGroup4;
    private String rbGroup5;

    public Quiz() {
    }

    public Quiz(String rbGroup1, String rbGroup2, String rbGroup3, String rbGroup4, String rbGroup5) {
        this.rbGroup1 = rbGroup1;
        this.rbGroup2 = rbGroup2;
        this.rbGroup3 = rbGroup3;
        this.rbGroup4 = rbGroup4;
        this.rbGroup5 = rbGroup5;
    }

    public String getRbGroup1() {
        return rbGroup1;
    }

    public void setRbGroup1(String rbGroup1) {
        this.rbGroup1 = rbGroup1;
    }

    public String getRbGroup2() {
        return rbGroup2;
    }

    public void setRbGroup2(String rbGroup2) {
        this.rbGroup2 = rbGroup2;
    }

    public String getRbGroup3() {
        return rbGroup3;
    }

    public void setRbGroup3(String rbGroup3) {
        this.rbGroup3 = rbGroup3;
    }

    public String getRbGroup4() {
        return rbGroup4;
    }

    public void setRbGroup4(String rbGroup4) {
        this.rbGroup4 = rbGroup4;
    }

    public String getRbGroup5() {
        return rbGroup5;
    }

    public void setRbGroup5(String rbGroup5) {
        this.rbGroup5 = rbGroup5;
    }
}
