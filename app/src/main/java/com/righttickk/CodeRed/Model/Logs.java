package com.righttickk.CodeRed.Model;

public class Logs {

    private String BodyTemp;
    private String CB1;
    private String CB2;
    private String CB3;
    private String RGroup1;
    private String RGroup2;
    private String RGroup3;
    private String id;
    private String lastDate;

    public Logs() {
    }

    public Logs(String bodyTemp, String CB1, String CB2, String CB3, String RGroup1, String RGroup2, String RGroup3, String id, String lastDate) {
        BodyTemp = bodyTemp;
        this.CB1 = CB1;
        this.CB2 = CB2;
        this.CB3 = CB3;
        this.RGroup1 = RGroup1;
        this.RGroup2 = RGroup2;
        this.RGroup3 = RGroup3;
        this.id = id;
        this.lastDate = lastDate;
    }

    public String getBodyTemp() {
        return BodyTemp;
    }

    public void setBodyTemp(String bodyTemp) {
        BodyTemp = bodyTemp;
    }

    public String getCB1() {
        return CB1;
    }

    public void setCB1(String CB1) {
        this.CB1 = CB1;
    }

    public String getCB2() {
        return CB2;
    }

    public void setCB2(String CB2) {
        this.CB2 = CB2;
    }

    public String getCB3() {
        return CB3;
    }

    public void setCB3(String CB3) {
        this.CB3 = CB3;
    }

    public String getRGroup1() {
        return RGroup1;
    }

    public void setRGroup1(String RGroup1) {
        this.RGroup1 = RGroup1;
    }

    public String getRGroup2() {
        return RGroup2;
    }

    public void setRGroup2(String RGroup2) {
        this.RGroup2 = RGroup2;
    }

    public String getRGroup3() {
        return RGroup3;
    }

    public void setRGroup3(String RGroup3) {
        this.RGroup3 = RGroup3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}
