package ui;

import javax.swing.*;
import java.awt.*;

public class ProcessScheduler extends JFrame {

    public ProcessScheduler() {
        // Frame setup
        setTitle("CPU Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 450);
        setLayout(null); // Use a BorderLayout for better organization

        // Add components
        TablePanel tablePanel = new TablePanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        AverageWaitPanel avgWaitPanel = new AverageWaitPanel();
        tablePanel.setBounds(10, 0, 730, 310); // Position the table panel
        buttonsPanel.setBounds(710, 10, 300, 300); // Position the buttons panel
        avgWaitPanel.setBounds(10, 350, 300, 50); // Position the average wait panel


        add(tablePanel);
        add(buttonsPanel);
        add(avgWaitPanel);

        // Make the frame visible
        setVisible(true);
    }

    // Main method

}
