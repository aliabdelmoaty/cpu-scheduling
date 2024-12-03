package ui;


import javax.swing.*;
import java.awt.*;

public class AverageWaitPanel extends JPanel {
    private JLabel avgWaitLabel;
    private JLabel avgTurnaroundLabel;
    public AverageWaitPanel() {
        setLayout(null); 
        avgWaitLabel = new JLabel("Average Wait: 0.0");
        avgWaitLabel.setBounds(10, 0, 300, 50); // Position the label
        avgWaitLabel.setFont(new Font("Arial", Font.BOLD, 14));
        avgTurnaroundLabel = new JLabel("Average Turnaround: 0.0");
        avgTurnaroundLabel.setBounds(10, 20, 300, 50); // Position the label
        avgTurnaroundLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(avgTurnaroundLabel);
        add(avgWaitLabel);
    }


    // Method to update the average wait time
    public void setAverageWaitTime(double avgWait) {
        avgWaitLabel.setText(String.format("Average Waiting Time: %.2f", avgWait));
    }
    public void setAverageTurnaroundTime(double avgTurnaround) {
        avgTurnaroundLabel.setText(String.format("Average Turnaround Time: %.2f", avgTurnaround));
    }
}
