package com.bintm;

import java.time.Instant;

public class Task {
    String name;
    int memuse = 0;
    int cpuUse = 0;
    int cpuPercent = 0;
    SystemInfo sysinf = new SystemInfo();
    TaskHandler parent;

    Task(String CSVDATA, TaskHandler p){
        parent = p;
        String[] properties = CSVDATA.replaceAll("\"","").split(",", 5);
        name = properties[0].replaceAll(".exe","");
        memuse = Integer.valueOf(properties[4].replaceAll(" K","").replaceAll(",",""));
    }
    void updateMemuse(int m){
        memuse=m;
    }
    int getSecondsOf(String s){
        if(s.equals("N/A")){return 0;}
        String[] dats = s.split(":");
        int outpt = 0;
        outpt += Integer.valueOf(dats[0])*60*60;
        outpt += Integer.valueOf(dats[1])*60;
        outpt += Integer.valueOf(dats[2]);
        return outpt;
    }
    void updateCpuUse(){
        long before = Instant.now().toEpochMilli();

        String[] data = parent.io.getStdoutArrayFor("TASKLIST /V /FO CSV /FI \"IMAGENAME eq "+this.name+".exe \"");
        long after = Instant.now().toEpochMilli();
        int totalTime =(int)(after-before);
        int i = 0;
        for(String s : data){
            if(i == 0){
                i++;
                continue;
            }
            this.cpuUse+=getSecondsOf(s.replace("\"","").split(",")[8]);
        }
        cpuPercent = (int)((double)cpuUse/(double)totalTime)/sysinf.getCpuUsage();
    }
    int getPercentOfRam(){
        return (int)((memuse/(sysinf.getRAMSpace()*1024)));
    }
    int getCpuPercent(){
        return cpuPercent;
    }


}
