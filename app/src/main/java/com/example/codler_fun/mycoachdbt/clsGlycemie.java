package com.example.codler_fun.mycoachdbt;

public class clsGlycemie
{
    private String userid;
    private String glycemie;


    public clsGlycemie() {
    }

    public clsGlycemie(String userid, String glycemie) {
        this.userid = userid;
        this.glycemie = glycemie;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGlycemie() {
        return glycemie;
    }

    public void setGlycemie(String glycemie) {
        this.glycemie = glycemie;
    }
}
