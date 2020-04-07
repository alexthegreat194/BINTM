package com.bintm;

import java.util.ArrayList;

public class TaskHandler {

    ArrayList<Task> tasks = new ArrayList<Task>(0);
    IOHandler io = new IOHandler();

    TaskHandler(){}

    void populate(){
        tasks.clear();
        System.out.println("Task Data: ");
        String[] taskdata = io.getStdoutArrayFor("Tasklist /FO csv");
        for(int i = 1 ; i < taskdata.length;i++){
            tasks.add(new Task(taskdata[i]));
        }
        System.out.println("Total Tasks Running: "+tasks.size());
    }



}
