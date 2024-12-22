package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import algorithms.Process;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * TablePanel is a custom JPanel that displays a table of processes with their scheduling details.
 * It includes functionalities to update and refresh the table.
 */
public class TablePanel extends JPanel {
    private List<Process> processes;
    private JTable table; // Table to display process information
    private DefaultTableModel tableModel; // Model for the table
    private JTextField nameField, arrivalField, burstField, priorityField;
    private Button addButton, deleteButton;
    private int processCounter = 1;

    // Column names for the table
    private String[] columnNames = {
            "Process", "Arrival Time", "Burst Time", "Priority",
            "Waiting Time", "End Time", "Turnaround Time"
    };

    /**
     * Constructor initializes the TablePanel with a table displaying process information.
     */
    public TablePanel() {
        processes = new ArrayList<>();
        setLayout(new BorderLayout()); // Set layout for the panel

        // Create input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        nameField = new JTextField(5);
        arrivalField = new JTextField(5);
        burstField = new JTextField(5);
        priorityField = new JTextField(5);
        
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Arrival:"));
        inputPanel.add(arrivalField);
        inputPanel.add(new JLabel("Burst:"));
        inputPanel.add(burstField);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityField);

        addButton = new Button("Add");
        deleteButton = new Button("Delete");
        
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Initialize table model with data and column names
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel); // Create table with the model
        table.getTableHeader().setResizingAllowed(false); // Disable column resizing
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering
        table.setRowHeight(20); // Set row height

        // Customize table header appearance
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.white);

        // Add button listener
        addButton.addActionListener(e -> addProcess());
        
        // Delete button listener
        deleteButton.addActionListener(e -> deleteSelectedProcess());

        // Add table to scroll pane and set its size
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 720, 300);

        setPreferredSize(new Dimension(720, 300)); // Set preferred size of the panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the panel

        // Add key listeners to the table
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addNewProcess();
                } else if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    deleteLastProcess();
                }
            }
        });

        // Make the panel focusable
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addNewProcess();
                } else if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    deleteLastProcess();
                }
            }
        });
    }

    private void addProcess() {
        try {
            String name = nameField.getText();
            int arrival = Integer.parseInt(arrivalField.getText());
            int burst = Integer.parseInt(burstField.getText());
            int priority = Integer.parseInt(priorityField.getText());

            Process process = new Process(name, arrival, burst, priority);
            processes.add(process);
            tableModel.addRow(process.toStringArray());

            // Clear fields
            nameField.setText("");
            arrivalField.setText("");
            burstField.setText("");
            priorityField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }

    private void deleteSelectedProcess() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            processes.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        }
    }

    private void addNewProcess() {
        try {
            String processName = "P" + processCounter;
            tableModel.addRow(new Object[]{
                processName, 
                "0",  // Default arrival time
                "0",  // Default burst time
                "0",  // Default priority
                "0",  // Default start time
                "0",  // Default end time
                "0"   // Default turnaround time
            });
            processCounter++;
            
            // Focus on the newly added row
            int lastRow = tableModel.getRowCount() - 1;
            table.setRowSelectionInterval(lastRow, lastRow);
            table.scrollRectToVisible(table.getCellRect(lastRow, 0, true));
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error adding new process: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteLastProcess() {
        try {
            int rowCount = tableModel.getRowCount();
            if (rowCount > 0) {
                tableModel.removeRow(rowCount - 1);
                processCounter = Math.max(1, processCounter - 1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error deleting process: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public Process[] getProcesses() {
        try {
            Vector<Vector> data = tableModel.getDataVector();
            Process[] processes = new Process[data.size()];
            
            for (int i = 0; i < data.size(); i++) {
                Vector row = data.get(i);
                String name = (String) row.get(0);
                int arrivalTime = Integer.parseInt(row.get(1).toString());
                int burstTime = Integer.parseInt(row.get(2).toString());
                int priority = Integer.parseInt(row.get(3).toString());
                
                // Validate input
                if (arrivalTime < 0) throw new IllegalArgumentException("Arrival time cannot be negative");
                if (burstTime <= 0) throw new IllegalArgumentException("Burst time must be positive");
                if (priority < 0) throw new IllegalArgumentException("Priority cannot be negative");
                
                processes[i] = new Process(name, arrivalTime, burstTime, priority);
            }
            return processes;
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Invalid number format in table. Please check your input.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return new Process[0];
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error processing table data: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return new Process[0];
        }
    }

    /**
     * Refreshes the table with updated process information and recalculates average times.
     */
    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Process process : processes) {
            tableModel.addRow(process.toStringArray());
        }
    }
}
