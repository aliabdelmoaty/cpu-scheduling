package ui;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import algorithms.Process;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel(TablePanel tablePanel, AverageWaitPanel avgWaitPanel) {
        
        setLayout(null);
        Button fcfsButton = new Button("FCFS");
        Button SJFButton = new Button("SJF");
        Button priorityButton = new Button("Priority");
        Button roundRobinButton = new Button("Round Robin");
        fcfsButton.setBounds(50, 0, 150, 30);
        SJFButton.setBounds(50, 50, 150, 30);
        priorityButton.setBounds(50, 100, 150, 30);
        roundRobinButton.setBounds(50, 150, 150, 30);
        fcfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };
                int currentTime = 0;
                int totalWaitingTime = 0;
                int totalTurnaroundTime = 0;

                for (int i = 0; i < processes.length; i++) {
                    Process process = processes[i];
                    int startTime = Math.max(currentTime, process.getArrivalTime());
                    process.setStartTime(startTime);
                    int endTime = startTime + process.getBurstTime();
                    process.setEndTime(endTime);
                    process.setTurnaroundTime(endTime - process.getArrivalTime());

                    int waitingTime = process.getStartTime() - process.getArrivalTime();
                    totalWaitingTime += waitingTime;
                    totalTurnaroundTime += process.getTurnaroundTime();

                    currentTime = endTime;

                }

                int numberOfProcesses = processes.length;
                double averageWaitingTime = (double) totalWaitingTime / numberOfProcesses;
                double averageTurnaroundTime = (double) totalTurnaroundTime / numberOfProcesses;

                tablePanel.refreshTable();
                avgWaitPanel.setAverageWaitTime(averageWaitingTime);
                avgWaitPanel.setAverageTurnaroundTime(averageTurnaroundTime);

            }
        });  
        setPreferredSize(new Dimension(960, 300));
        add(SJFButton);
        add(priorityButton);
        add(roundRobinButton);
        add(fcfsButton);
    }
}
