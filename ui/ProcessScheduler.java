package ui;

import javax.swing.*;
public class ProcessScheduler extends JFrame {

    public ProcessScheduler() {
        setTitle("CPU Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 430);
        setLayout(null); 

        // Add components
        TablePanel tablePanel = new TablePanel();
        AverageWaitPanel avgWaitPanel = new AverageWaitPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel(tablePanel, avgWaitPanel);
        
        TeamMembersPanel teamMembersPanel = new TeamMembersPanel();
        tablePanel.setBounds(10, 0, 730, 200); // Position the table panel
        buttonsPanel.setBounds(710, 10, 300, 200); // Position the buttons panel
        avgWaitPanel.setBounds(10, 210, 300, 300); // Position the average wait panel
        teamMembersPanel.setBounds(760, 230, 150, 100); // Position the team members panel

        add(tablePanel);
        add(buttonsPanel);
        add(avgWaitPanel);
        add(teamMembersPanel);
        // Make the frame visible
        setVisible(true);
    }

}
