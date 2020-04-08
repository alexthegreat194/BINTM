package com.bintm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;


public class Main {

    public static void main(String[] args) {
	    System.out.println("Hello World");
	    IOHandler io = new IOHandler();
	    SystemInfo sysinf = new SystemInfo();
	    System.out.println("Total Ram: "+sysinf.getRAMSpace()+" GB");
	    System.out.println("Ram in use: "+ sysinf.getRAMinUse()+" GB");
	    System.out.println("Ram Available: "+ sysinf.getAvailableRAM()+" GB");
	    System.out.println("Total Disk Space: "+ sysinf.getDiskSpace() + " GB");
	    System.out.println("Free Disk Space: "+sysinf.getAvailableDiskSpace() + " GB");

	    TaskHandler th = new TaskHandler();
	    th.startUpdateThread();
		JFrame frame = new JFrame("FrameDemo");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JTable table = new JTable(th.getInfoString(),th.getTableHeader());
		table.setModel(new DefaultTableModel());
		frame.getContentPane().add(table, BorderLayout.CENTER);

		frame.pack();

		frame.setVisible(true);

    }
}
