package com.elevisjang.smartstudy.activity;

public class ClassInformation {

    private String ClassName;
    private String TeacherName;

    public ClassInformation(){

    }

    public ClassInformation(String ClassName,String TeacherName){
        this.ClassName = ClassName;
        this.TeacherName = TeacherName;
    }


    public String getClassName(){

        return ClassName;
    }

    public void setClassName(String Classname){

        this.ClassName = Classname;
    }

    public String getTeacherName(){

        return TeacherName;
    }

    public void setTeacherName(String TeacherName){

        this.TeacherName = TeacherName;
    }
}
