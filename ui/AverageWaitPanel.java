package ui;


import javax.swing.*;
import java.awt.*;

public class AverageWaitPanel extends JPanel {
    private JLabel avgWaitLabel;

    public AverageWaitPanel() {
        setLayout(null); // Absolute positioning
        // setBackground(new Color(220, 220, 220)); // Optional background color

        // Create and configure the JLabel
        avgWaitLabel = new JLabel("Average Wait: 0.0");
        avgWaitLabel.setBounds(10, 0, 150, 50); // Position the label
        avgWaitLabel.setFont(new Font("Arial", Font.BOLD, 14));


        // Add the label to the panel
        add(avgWaitLabel);
    }


    // Method to update the average wait time
    public void setAverageWaitTime(double avgWait) {
        avgWaitLabel.setText(String.format("Average Waiting Time: %.2f", avgWait));
    }
}
