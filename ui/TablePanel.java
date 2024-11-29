package ui;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    // Table data
    private String[][] data = {
            { "P1", "0", "2", "1", "0", "0", "2" },
            { "P2", "1", "1", "2", "0", "0", "1" },
            { "P3", "2", "5", "3", "0", "0", "5" },
            { "P4", "3", "3", "4", "0", "0", "3" }
    };

    private String[] columnNames = {
            "Process", "Arrival Time", "Burst Time", "Priority",
            "Start Time", "End Time", "Turnaround Time"
    };

    public TablePanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for better alignment

        // Create the JTable
        JTable table = new JTable(data, columnNames);

        // Wrap JTable in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 720, 300);
        
        setPreferredSize(new Dimension(720, 300));
        // Add the scroll pane to the panel
        add(scrollPane);
    }
}
