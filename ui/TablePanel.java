package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import algorithms.Process;
import java.awt.*;
import java.util.Arrays;

public class TablePanel extends JPanel {
    public Process p1 = new Process("P1", 0, 2, 1);
    public Process p2 = new Process("P2", 1, 1, 9);
    public Process p3 = new Process("P3", 2, 5, 3);
    public Process p4 = new Process("P4", 3, 3, 0);

    private JTable table;
    private DefaultTableModel tableModel;

    private final String[] columnNames = {
            "Process", "Arrival Time", "Burst Time", "Priority",
            "Start Time", "End Time", "Turnaround Time", "Waiting Time"
    };

    public TablePanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(getTableData(), columnNames);
        table = new JTable(tableModel);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(20);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(720, 300));

        setPreferredSize(new Dimension(720, 300));
        add(scrollPane, BorderLayout.CENTER);
    }

    private String[][] getTableData() {
        return new String[][] {
                p1.toStringArray(),
                p2.toStringArray(),
                p3.toStringArray(),
                p4.toStringArray()
        };
    }
public void updateProcesses(Process[] processes) {
    // Sort processes by their name or any other criteria you need
    Arrays.sort(processes, (p1, p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));

    // Update the table view with the sorted processes
    for (int i = 0; i < processes.length; i++) {
        // Update the table row with the new process data (e.g., process name, start time, etc.)
        tableModel.setValueAt(processes[i].getName(), i, 0);
        tableModel.setValueAt(processes[i].getStartTime(), i, 1);
        tableModel.setValueAt(processes[i].getEndTime(), i, 2);
        tableModel.setValueAt(processes[i].getWaitingTime(), i, 3);
        tableModel.setValueAt(processes[i].getTurnaroundTime(), i, 4);
    }
}

    public void refreshTable() {
        tableModel.setDataVector(getTableData(), columnNames);
        tableModel.fireTableDataChanged();
    }
}
