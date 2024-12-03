package ui;

import javax.swing.*;
import java.awt.*;

public class ProcessScheduler extends JFrame {

    public ProcessScheduler() {
        // Frame setup
        setTitle("CPU Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 430);
        setLayout(null); // Use a BorderLayout for better organization

        // Add components
        TablePanel tablePanel = new TablePanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        AverageWaitPanel avgWaitPanel = new AverageWaitPanel();
        TeamMembersPanel teamMembersPanel = new TeamMembersPanel();
        tablePanel.setBounds(10, 0, 730, 310); // Position the table panel
        buttonsPanel.setBounds(710, 10, 300, 200); // Position the buttons panel
        avgWaitPanel.setBounds(10, 350, 200, 300); // Position the average wait panel
        teamMembersPanel.setBounds(760, 230, 150, 100); // Position the team members panel

        add(tablePanel);
        add(buttonsPanel);
        add(avgWaitPanel);
        add(teamMembersPanel);
        // Make the frame visible
        setVisible(true);
    }

    // Main method

}
