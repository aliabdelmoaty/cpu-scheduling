package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;

import algorithms.FirstComeFirstServe;
import algorithms.RoundRobin;
import algorithms.ShortestJobFirst;
import algorithms.PriorityPreemptive;
import algorithms.PriorityNonPreemptive;
import algorithms.Process;
import classes.Row;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel(TablePanel tablePanel, AverageWaitPanel avgWaitPanel) {
        setLayout(null);

        // FCFS Button
        Button fcfsButton = new Button("FCFS");
        fcfsButton.setBounds(50, 10, 150, 30); // Adjusted position

        fcfsButton.addActionListener((ActionEvent e) -> {
            FirstComeFirstServe fcfs = new FirstComeFirstServe();

            Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };
            for (Process process : processes) {
                fcfs.add(new Row(process.getName(), process.getArrivalTime(), process.getBurstTime()));
            }

            fcfs.process();

            for (Row row : fcfs.getRows()) {
                Process process = Arrays.stream(processes)
                        .filter(p -> p.getName().equals(row.getProcessName()))
                        .findFirst()
                        .orElse(null);
                if (process != null) {
                    process.setStartTime(fcfs.getEvent(row).getStartTime());
                    process.setEndTime(fcfs.getEvent(row).getFinishTime());
                    process.setWaitingTime(row.getWaitingTime());
                    process.setTurnaroundTime(row.getTurnaroundTime());
                }
            }

            int totalWaitingTime = Arrays.stream(processes).mapToInt(Process::getWaitingTime).sum();
            int totalTurnaroundTime = Arrays.stream(processes).mapToInt(Process::getTurnaroundTime).sum();

            calculateAndDisplayResults(processes, totalWaitingTime, totalTurnaroundTime, tablePanel, avgWaitPanel);
        });

        add(fcfsButton);

        // RoundRobin Button
        Button rrButton = new Button("Round Robin");
        rrButton.setBounds(50, 50, 150, 30); // Adjusted position

        rrButton.addActionListener((ActionEvent e) -> {
            String quantumInput = JOptionPane.showInputDialog(this, "Enter Time Quantum:", "Round Robin",
                    JOptionPane.PLAIN_MESSAGE);
            int timeQuantum = 2; // Default quantum value

            try {
                timeQuantum = Integer.parseInt(quantumInput);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Default Time Quantum = 2", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            RoundRobin rr = new RoundRobin();
            rr.setTimeQuantum(timeQuantum);

            Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };
            for (Process process : processes) {
                rr.add(new Row(process.getName(), process.getArrivalTime(), process.getBurstTime()));
            }

            rr.process();

            for (Row row : rr.getRows()) {
                Process process = Arrays.stream(processes)
                        .filter(p -> p.getName().equals(row.getProcessName()))
                        .findFirst()
                        .orElse(null);
                if (process != null) {
                    process.setStartTime(rr.getEvent(row).getStartTime());
                    process.setEndTime(rr.getEvent(row).getFinishTime());
                    process.setWaitingTime(row.getWaitingTime());
                    process.setTurnaroundTime(row.getTurnaroundTime());
                }
            }

            int totalWaitingTime = Arrays.stream(processes).mapToInt(Process::getWaitingTime).sum();
            int totalTurnaroundTime = Arrays.stream(processes).mapToInt(Process::getTurnaroundTime).sum();

            calculateAndDisplayResults(processes, totalWaitingTime, totalTurnaroundTime, tablePanel, avgWaitPanel);
        });

        add(rrButton);

        // PriorityPreemptive Button
        Button ppButton = new Button("Priority Preemptive");
        ppButton.setBounds(50, 90, 150, 30); // Adjusted position

        ppButton.addActionListener((ActionEvent e) -> {
            String quantumInput = JOptionPane.showInputDialog(this, "Enter Time Quantum for Preemptive Scheduling:",
                    "Priority Preemptive", JOptionPane.PLAIN_MESSAGE);
            int timeQuantum = 1; // Default quantum value

            try {
                timeQuantum = Integer.parseInt(quantumInput);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Default Time Quantum = 1", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            PriorityPreemptive pp = new PriorityPreemptive();
            Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };
            for (Process process : processes) {
                pp.add(new Row(process.getName(), process.getArrivalTime(), process.getBurstTime(),
                        process.getPriority()));
            }

            pp.process();

            for (Row row : pp.getRows()) {
                Process process = Arrays.stream(processes)
                        .filter(p -> p.getName().equals(row.getProcessName()))
                        .findFirst()
                        .orElse(null);
                if (process != null) {
                    process.setStartTime(pp.getEvent(row).getStartTime());
                    process.setEndTime(pp.getEvent(row).getFinishTime());
                    process.setWaitingTime(row.getWaitingTime());
                    process.setTurnaroundTime(row.getTurnaroundTime());
                }
            }

            int totalWaitingTime = Arrays.stream(processes).mapToInt(Process::getWaitingTime).sum();
            int totalTurnaroundTime = Arrays.stream(processes).mapToInt(Process::getTurnaroundTime).sum();

            calculateAndDisplayResults(processes, totalWaitingTime, totalTurnaroundTime, tablePanel, avgWaitPanel);
        });

        add(ppButton);

        // SJF Button
        Button sjfButton = new Button("SJF");
        sjfButton.setBounds(50, 130, 150, 30); // Adjusted position

        sjfButton.addActionListener((ActionEvent e) -> {
            ShortestJobFirst sjf = new ShortestJobFirst();

            Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };
            for (Process process : processes) {
                sjf.add(new Row(process.getName(), process.getArrivalTime(), process.getBurstTime()));
            }

            sjf.process();

            for (Row row : sjf.getRows()) {
                Process process = Arrays.stream(processes)
                        .filter(p -> p.getName().equals(row.getProcessName()))
                        .findFirst()
                        .orElse(null);
                if (process != null) {
                    process.setStartTime(sjf.getEvent(row).getStartTime());
                    process.setEndTime(sjf.getEvent(row).getFinishTime());
                    process.setWaitingTime(row.getWaitingTime());
                    process.setTurnaroundTime(row.getTurnaroundTime());
                }
            }

            int totalWaitingTime = Arrays.stream(processes).mapToInt(Process::getWaitingTime).sum();
            int totalTurnaroundTime = Arrays.stream(processes).mapToInt(Process::getTurnaroundTime).sum();

            calculateAndDisplayResults(processes, totalWaitingTime, totalTurnaroundTime, tablePanel, avgWaitPanel);
        });

        add(sjfButton);

        // PriorityNonPreemptive Button
        Button pnpButton = new Button("Priority Non-Preemptive");
        pnpButton.setBounds(50, 170, 150, 30); // Adjusted position

        // Inside the ButtonsPanel class, inside the pnpButton's ActionListener

        pnpButton.addActionListener((ActionEvent e) -> {
            // Create a new PriorityNonPreemptive scheduler
            PriorityNonPreemptive pnp = new PriorityNonPreemptive();

            // Collect processes from the table
            Process[] processes = { tablePanel.p1, tablePanel.p2, tablePanel.p3, tablePanel.p4 };

            // Add rows to the scheduler, including priority
            for (Process process : processes) {
                pnp.add(new Row(process.getName(), process.getArrivalTime(), process.getBurstTime(),
                        process.getPriority()));
            }

            // Sort processes by priority (lowest priority number first)
    Collections.sort(pnp.getRows(), (row1, row2) -> Integer.compare(row1.getPriorityLevel(), row2.getPriorityLevel()));

            // Process the sorted processes
            pnp.process();

            // Update the process info with the computed times
            for (Row row : pnp.getRows()) {
                Process process = Arrays.stream(processes)
                        .filter(p -> p.getName().equals(row.getProcessName()))
                        .findFirst()
                        .orElse(null);
                if (process != null) {
                    process.setStartTime(pnp.getEvent(row).getStartTime());
                    process.setEndTime(pnp.getEvent(row).getFinishTime());
                    process.setWaitingTime(row.getWaitingTime());
                    process.setTurnaroundTime(row.getTurnaroundTime());
                }
            }

            // Calculate total waiting and turnaround times
            int totalWaitingTime = Arrays.stream(processes).mapToInt(Process::getWaitingTime).sum();
            int totalTurnaroundTime = Arrays.stream(processes).mapToInt(Process::getTurnaroundTime).sum();


            calculateAndDisplayResults(processes, totalWaitingTime, totalTurnaroundTime, tablePanel, avgWaitPanel);

            // Update the table panel to reflect the sorted order
            tablePanel.updateProcesses(processes);
        });

        add(pnpButton);

        setPreferredSize(new Dimension(960, 200)); // Adjusted size
    }

    private void calculateAndDisplayResults(Process[] processes, int totalWaitingTime, int totalTurnaroundTime,
            TablePanel tablePanel, AverageWaitPanel avgWaitPanel) {
        tablePanel.refreshTable();
        int numProcesses = processes.length;
        avgWaitPanel.setAverageWaitTime((double) totalWaitingTime / numProcesses);
        avgWaitPanel.setAverageTurnaroundTime((double) totalTurnaroundTime / numProcesses);
    }
}
