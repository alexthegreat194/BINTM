package com.bintm;

import javax.swing.*;
import java.time.Instant;

public class BINTMForm {
    private JPanel panel1;
    private JLabel CpuLabel;
    private JProgressBar Cpubar;
    private JProgressBar TotalRamBat;
    private JScrollPane mainScrollPane;
    private JPanel progressArea;
    private JProgressBar freeDiskSpaceBar;
    private JProgressBar totalDiskSpaceBar;
    private JProgressBar ramInUseBar;
    private JProgressBar ramAvailableBar;

    //pablo classes:
    private WindowUpdater updater;
    private IOHandler io;
    private SystemInfo sysinf;
    private TaskHandler th;

    public BINTMForm()
    {
        System.out.println("Window Launch...");
        io = new IOHandler(); //
        sysinf = new SystemInfo(); //
        th = new TaskHandler();
        updater = new WindowUpdater();

        System.out.println("Total Ram: "+sysinf.getRAMSpace()+" GB");
        System.out.println("Ram in use: "+ sysinf.getRAMinUse()+" GB");
        System.out.println("Ram Available: "+ sysinf.getAvailableRAM()+" GB");
        System.out.println("Total Disk Space: "+ sysinf.getDiskSpace() + " GB");
        System.out.println("Free Disk Space: "+sysinf.getAvailableDiskSpace() + " GB");

        th.startUpdateThread();
        new Thread(updater).start();
    }

    class WindowUpdater implements Runnable
    {
        boolean stop = false;

        void sleep(int millis){
            long time = Instant.now().toEpochMilli();
            long elapsed = 0;
            if(stop){return;}
            while(elapsed<millis) {
                elapsed = Instant.now().toEpochMilli() - time;
            }
        }

        public void run(){
            while(true){
                sleep(1000);
                if(stop){return;}
                //update window VVVVVV

                updateWindowData();
            }
        }
    }


    void updateWindowData()
    {
        TotalRamBat.setValue((int)sysinf.getRAMSpace());
        freeDiskSpaceBar.setValue((int)sysinf.getDiskSpace());
        totalDiskSpaceBar.setValue((int)sysinf.getDiskSpace());
        ramInUseBar.setValue((int)(sysinf.getRAMinUse()/1000));
        ramAvailableBar.setValue((int)(sysinf.getAvailableRAM()/1000));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BINTMForm");
        frame.setContentPane(new BINTMForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
