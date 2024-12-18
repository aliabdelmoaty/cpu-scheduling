package ui;

import javax.swing.*;

public class ProcessScheduler extends JFrame {

    // Constructor for the ProcessScheduler class
    public ProcessScheduler() {
        // Set the title of the window
        setTitle("CPU Scheduler");
        
        // Define the default close operation (exit the application when the window is closed)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the window
        setSize(1000, 430);
        
        // Use null layout to manually position components
        setLayout(null); 

        // Create instances of the panels
        TablePanel tablePanel = new TablePanel();
        AverageWaitPanel avgWaitPanel = new AverageWaitPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel(tablePanel, avgWaitPanel);
        TeamMembersPanel teamMembersPanel = new TeamMembersPanel();

        // Set the bounds (position and size) of each panel
        tablePanel.setBounds(10, 0, 730, 200); // Position the table panel
        buttonsPanel.setBounds(710, 10, 300, 200); // Position the buttons panel
        avgWaitPanel.setBounds(10, 210, 300, 300); // Position the average wait panel
        teamMembersPanel.setBounds(760, 230, 150, 100); // Position the team members panel

        // Add the panels to the frame
        add(tablePanel);
        add(buttonsPanel);
        add(avgWaitPanel);
        add(teamMembersPanel);

        // Make the frame visible
        setVisible(true);
    }
}