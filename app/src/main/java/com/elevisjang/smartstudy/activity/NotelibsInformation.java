package com.elevisjang.smartstudy.activity;

public class NotelibsInformation {

    private String Name;
    private String time;

    public NotelibsInformation(){

    }

    public NotelibsInformation(String Name,String time){

        this.Name = Name;
        this.time = time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
