package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import algorithms.Process;
import java.awt.*;

public class TablePanel extends JPanel {
    public Process p1 = new Process("P1", 0, 2, 1);
     public Process p2 = new Process("P2", 1, 1, 2);
     public Process p3 = new Process("P3", 2, 5, 3);
     public Process p4 = new Process("P4", 3, 3, 4);

    private JTable table;
    private DefaultTableModel tableModel;

    private String[] columnNames = {
            "Process", "Arrival Time", "Burst Time", "Priority",
            "Start Time", "End Time", "Turnaround Time"
    };

    public TablePanel() {
        setLayout(new BorderLayout());

        // Initialize table data
        updateTable();

        
        tableModel = new DefaultTableModel(updateTable(), columnNames);
        table = new JTable(tableModel);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(20);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.white);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 720, 300);

        setPreferredSize(new Dimension(720, 300));
        add(scrollPane);
    }

    public String[][] updateTable() {
        return new String[][] {
                p1.toStringArray(),
                p2.toStringArray(),
                p3.toStringArray(),
                p4.toStringArray()
        };
    }

    public void refreshTable() {
        tableModel.setDataVector(updateTable(), columnNames);
        tableModel.fireTableDataChanged();
    }
}
