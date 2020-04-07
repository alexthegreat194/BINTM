package com.bintm;

import java.util.ArrayList;

public class TaskHandler {

    ArrayList<Task> tasks = new ArrayList<Task>(0);
    IOHandler io = new IOHandler();
    static String[] osTaskNames = {
            "svchost.exe",
            ""


    };
    TaskHandler(){}

    boolean notOsTask(String name){
        for(String s: osTaskNames){
            if(name.equals(s)){
                return false;
            }
        }
        return true;
    }

    void populate(){
        tasks.clear();
        System.out.println("Task Data: ");
        String[] taskdata = io.getStdoutArrayFor("Tasklist /FO csv");
        for(int i = 1 ; i < taskdata.length;i++){
            Task t = new Task(taskdata[i],this);
            if (notOsTask(t.name+".exe")) {
                addTask(t);
            }

        }
        System.out.println("Total Tasks Running: "+tasks.size());
        updateCpuUsage();
    }

    void printChart(){

        for(Task t : getImportantTasks()){
                System.out.println(t.name);
                System.out.println("\t" + t.getPercentOfRam() + "% of ram @" + t.memuse + " Kb");
                System.out.println("\t" + t.getCpuPercent() + "% of CPU");
        }

    }

    void addTask(Task t){
        boolean exists = false;
        for(Task tE : tasks){
            if (tE.name.equals(t.name)){
                tE.memuse+=t.memuse;
                exists=true;
            }
        }
        if(!exists){
            tasks.add(t);
        }
    }

    ArrayList<Task> getImportantTasks(){

        ArrayList<Task> outpt = new ArrayList<Task>();
        for(Task t: tasks){

            if(t.getPercentOfRam()>0) {
                outpt.add(t);
            }

        }
        return outpt;

    }

    void updateCpuUsage(){
        for(Task t : getImportantTasks()){
            try {
                t.updateCpuUse();
            }catch (Exception e){
                System.out.print("Error for task: "+ t.name + " : ");
                e.printStackTrace();
            }
        }
    }


}
