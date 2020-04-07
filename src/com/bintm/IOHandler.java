package com.bintm;

import java.io.*;

public class IOHandler {
    IOHandler(){


    }
    void execute(String command){
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
        pb.redirectErrorStream(true);
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    String getStdoutFor(String command){

        String out = "";
        ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c", command);
        pb.redirectErrorStream(true);

        Process p;
        try {

            p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (true){
                String l = br.readLine();
                if(l == null){break;}
                out+=l+"\n";
            }
            System.out.println(out);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;

    }
    String[] getStdoutArrayFor(String command){
        String prelim = getStdoutFor(command);
        return prelim.split("\n");
    }




}
