package ui;


import javax.swing.*;
import java.awt.*;

public class AverageWaitPanel extends JPanel {
    private JLabel avgWaitLabel;
    private JLabel teamName;
    private JLabel name1;
    private JLabel name2;
    private JLabel name3;
    private JLabel name4;

    public AverageWaitPanel() {
        setLayout(null); // Absolute positioning
        // setBackground(new Color(220, 220, 220)); // Optional background color

        // Create and configure the JLabel
        avgWaitLabel = new JLabel("Average Wait: 0.0");
        avgWaitLabel.setBounds(10, 0, 150, 50); // Position the label
        avgWaitLabel.setFont(new Font("Arial", Font.BOLD, 14));
        teamName = new JLabel("Team names:");
        teamName.setBounds(710, 300, 150, 50);
        teamName.setFont(new Font("Arial", Font.BOLD, 14));
        name1 = new JLabel("Alice");
        name1.setBounds(10, 100, 150, 50);
        name1.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
        name2 = new JLabel("Bob");
        name2.setBounds(10, 150, 150, 50);
        name2.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
        name3 = new JLabel("Charlie");
        name3.setBounds(10, 200, 150, 50);
        name3.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
        name4 = new JLabel("Diana");
        name4.setBounds(10, 250, 150, 50);
        name4.setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));



        // Add the label to the panel
        add(avgWaitLabel);
        // add(teamName);
        // add(name1);
        // add(name2);
        // add(name3);
        // add(name4);
    }


    // Method to update the average wait time
    public void setAverageWaitTime(double avgWait) {
        avgWaitLabel.setText(String.format("Average Waiting Time: %.2f", avgWait));
    }
}
