package com.righttickk.CodeRed.Model;

public class Detail {
    private String DOB;
    private String cycleLength;
    private String id;
    private String lastDate;
    private String periodLength;
    private String BMI;

    public Detail() {
    }

    public Detail(String DOB, String cycleLength, String id, String lastDate, String periodLength,String BMI) {
        this.DOB = DOB;
        this.cycleLength = cycleLength;
        this.id = id;
        this.lastDate = lastDate;
        this.periodLength = periodLength;
        this.BMI = BMI;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(String cycleLength) {
        this.cycleLength = cycleLength;
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

    public String getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(String periodLength) {
        this.periodLength = periodLength;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }
}
