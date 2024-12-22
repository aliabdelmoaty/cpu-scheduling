package ui;

import javax.swing.*;

public class ProcessScheduler extends JFrame {

    // Constructor for the ProcessScheduler class
    public ProcessScheduler() {
        // Set the title of the window
        setTitle("CPU Scheduler");
        
        // Define the default close operation (exit the application when the window is closed)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adjust size to accommodate Gantt chart
        setSize(1000, 530);
        
        // Use null layout to manually position components
        setLayout(null); 

        // Create instances of the panels
        TablePanel tablePanel = new TablePanel();
        AverageWaitPanel avgWaitPanel = new AverageWaitPanel();
        ProcessGanttChart ganttChartPanel = new ProcessGanttChart();

        ButtonsPanel buttonsPanel = new ButtonsPanel(tablePanel, avgWaitPanel,ganttChartPanel);
        TeamMembersPanel teamMembersPanel = new TeamMembersPanel();

        // Adjust panel positions
        tablePanel.setBounds(10, 0, 730, 200); // Position the table panel
        buttonsPanel.setBounds(710, 10, 300, 300); // Position the buttons panel
        avgWaitPanel.setBounds(10, 310, 300, 300); // Position the average wait panel
        // teamMembersPanel.setBounds(760, 330, 150, 100); // Position the team members panel
        ganttChartPanel.setBounds(320, 310, 660, 200); // Adjusted to take remaining width

        // Add the panels to the frame
        add(tablePanel);
        add(buttonsPanel);
        add(avgWaitPanel);
        // add(teamMembersPanel);
        add(ganttChartPanel); // Add the Gantt chart panel

        // Make the frame visible
        setVisible(true);
    }
}