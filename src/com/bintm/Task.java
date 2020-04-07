package com.bintm;

public class Task {

    String name;
    int memuse = 0;
    int cpuUse = 0;


    Task(String CSVDATA){
        String[] properties = CSVDATA.replaceAll("\"","").split(",");
        name = properties[0].replaceAll(".exe","");
        memuse = Integer.valueOf(properties[4].replaceAll(" K",""));
    }
    void updateMemuse(int m){
        memuse=m;
    }
    void updateCpuUse(int c){
        cpuUse=c;
    }



}
