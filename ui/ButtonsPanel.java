package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
        setLayout(null);
        // Create and add buttons
        JButton fcfsButton = new JButton("FCFS");
        JButton SJFButton = new JButton("SJF");
        JButton priorityButton = new JButton("Priority");
        JButton roundRobinButton = new JButton("Round Robin");
        fcfsButton.setBounds(50, 0, 150, 30);
        SJFButton.setBounds(50, 50, 150, 30);
        priorityButton.setBounds(50, 100, 150, 30);
        roundRobinButton.setBounds(50, 150, 150, 30);

        // SJFButton.setBounds(750, 40, 100, 30);
        // priorityButton.setBounds(850, 80, 100, 30);
        // roundRobinButton.setBounds(850, 120, 100, 30);

        setPreferredSize(new Dimension(960, 300));
        add(SJFButton);
        add(priorityButton);
        add(roundRobinButton);
        add(fcfsButton);
    }
}
