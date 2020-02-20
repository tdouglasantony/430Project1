/*
CSCI 430 Group 1
Nick Juelich
Majed Alsharikh
Tyler Antony
Abhishek Adhikari
*/

import java.io.Serializable;
import java.util.Calendar;

public class Transaction implements Serializable {
    private String date;
    private String desc;
    private double dollarAmt;


    Transaction(String d, String description, double dollar){
        this.date = d;
        this.desc = description;
        this.dollarAmt = dollar;
    }
    public String getDate() {
        return date;
    }

    public double getDollarAmt() {
        return dollarAmt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDollarAmt(double dollarAmt) {
        this.dollarAmt = dollarAmt;
    }

    public String toString() {
        return " "+date+ " "+desc+ " "+dollarAmt;
    }
}
