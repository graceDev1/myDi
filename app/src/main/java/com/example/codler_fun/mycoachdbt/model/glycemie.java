package com.example.codler_fun.mycoachdbt.model;

import java.util.Date;

public class glycemie
{

    private String userid;
   private Date date;
   private Double rate;

    public glycemie() {
    }

    public glycemie(String userid, Date date, Double rate) {
        this.userid = userid;
        this.date = date;
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
