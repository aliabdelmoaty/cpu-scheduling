package ui;

import algorithms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import algorithms.Process;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel(TablePanel tablePanel, AverageWaitPanel avgWaitPanel, ProcessGanttChart ganttChartPanel) {
        setLayout(null);
        Button fcfsButton = new Button("FCFS");
        Button SJFButton = new Button("SJF");
        Button priorityButton = new Button("Priority");
        Button roundRobinButton = new Button("Round Robin");
        Button srtButton = new Button("SRT");
        Button pspButton = new Button("PSP");  // Add new PSP button

        fcfsButton.setBounds(50, 0, 150, 30);
        SJFButton.setBounds(50, 50, 150, 30);
        priorityButton.setBounds(50, 100, 150, 30);
        roundRobinButton.setBounds(50, 150, 150, 30);
        srtButton.setBounds(50, 200, 150, 30);
        pspButton.setBounds(50, 250, 150, 30);  // Position the new button

        setPreferredSize(new Dimension(960, 300));
        add(SJFButton);
        add(priorityButton);
        add(roundRobinButton);
        add(fcfsButton);
        add(srtButton);
        add(pspButton);  // Add the new button

        // Update action listeners to include error handling
        ActionListener algorithmListener = e -> {
            try {
                Process[] processes = tablePanel.getProcesses();
                if (processes.length == 0) {
                    JOptionPane.showMessageDialog(this, "Please add processes first");
                    return;
                }

                // Execute selected algorithm
                switch (e.getActionCommand()) {
                    case "FCFS":
                        FCFS.schedule(processes);
                        break;
                    case "SJF":
                        SJF.schedule(processes);
                        break;
                    case "SRT":
                        SRT.schedule(processes);
                        break;
                    case "Priority":
                        PSN.schedule(processes);
                        break;
                    case "Round Robin":
                        String input = JOptionPane.showInputDialog(
                            this,
                            "Enter time quantum:",
                            "Round Robin",
                            JOptionPane.QUESTION_MESSAGE);
                        if (input != null && !input.trim().isEmpty()) {
                            try {
                                int quantum = Integer.parseInt(input.trim());
                                if (quantum > 0) {
                                    RoundRobin.schedule(processes, quantum);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Quantum must be positive");
                                    return;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this, "Invalid quantum value");
                                return;
                            }
                        } else {
                            return;
                        }
                        break;
                    case "PSP":
                        PSP.schedule(processes);
                        break;
                }

                // Calculate results and update UI
                updateResults(processes, tablePanel, avgWaitPanel, ganttChartPanel);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error during scheduling: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        };

        // Add the listener to all buttons
        fcfsButton.addActionListener(algorithmListener);
        SJFButton.addActionListener(algorithmListener);
        priorityButton.addActionListener(algorithmListener);
        srtButton.addActionListener(algorithmListener);
        roundRobinButton.addActionListener(algorithmListener);
        pspButton.addActionListener(algorithmListener);
        // ... add to other buttons
    }

    private void updateResults(Process[] processes, TablePanel tablePanel, 
                             AverageWaitPanel avgWaitPanel, ProcessGanttChart ganttChartPanel) {
        try {
            int totalWaitingTime = 0;
            int totalTurnaroundTime = 0;
            
            // Update the processes list in TablePanel with the scheduled processes
            tablePanel.updateProcesses(processes);
            
            for (Process p : processes) {
                totalWaitingTime += p.getWaitingTime();
                totalTurnaroundTime += p.getTurnaroundTime();
            }

            // Update visualization
            ganttChartPanel.updateChart(processes);
            
            // Calculate and display averages
            double avgWaitTime = (double) totalWaitingTime / processes.length;
            double avgTurnaroundTime = (double) totalTurnaroundTime / processes.length;
            
            avgWaitPanel.setAverageWaitTime(avgWaitTime);
            avgWaitPanel.setAverageTurnaroundTime(avgTurnaroundTime);
            
            // Refresh the table to show updated values
            tablePanel.refreshTable();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Error updating results: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to calculate and display results
    private void calculateAndDisplayResults(Process[] processes,
            int totalWaitingTime,
            int totalTurnaroundTime,
            TablePanel tablePanel,
            AverageWaitPanel avgWaitPanel) {
        // Just refresh the table since data is already updated
        tablePanel.refreshTable();

        // Calculate and set average times
        int numberOfProcesses = processes.length;
        double averageWaitingTime = (double) totalWaitingTime / numberOfProcesses;
        double averageTurnaroundTime = (double) totalTurnaroundTime / numberOfProcesses;

        avgWaitPanel.setAverageWaitTime(averageWaitingTime);
        avgWaitPanel.setAverageTurnaroundTime(averageTurnaroundTime);
    }
}