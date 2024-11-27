package org.example.pharmacymanagmentfrontend.View;

import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import org.example.pharmacymanagmentfrontend.Model.UserLogs;

public class UserLogs extends javax.swing.JFrame {
    private JTextField searchField;
    private JButton searchButton, resetButton;
    private JTable logsTable;
    private JLabel totalLogsLabel;

    public UserLogs() {
        // Top Section
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel headerText = new JLabel("User Login Logs");
        headerText.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(headerText, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchField = new JTextField(20);
        searchField.setToolTipText("Search by Username");
        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);

        topPanel.add(searchPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Center Section (Table)
        String[] columnNames = {"Login Time", "Successful Login", "Username", "User Type"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        logsTable = new JTable(tableModel);

        // Add logs to check the code
        UserGenerator.Addlogs();  // This should add logs to the UserTracker
        for (org.example.pharmacymanagmentfrontend.Model.UserLogs userlog : UserGenerator.getLoginTracker()) {
            tableModel.addRow(new Object[]{userlog.getLoginTime(), userlog.getSuccessfulLogin(), userlog.getUsername(), userlog.getType()});
        }

        // Enhance table appearance
        logsTable.setRowHeight(30); // Set row height for better readability
        logsTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the table
        logsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Bold column headers
        logsTable.getTableHeader().setBackground(new Color(70, 130, 180)); // Header background color
        logsTable.getTableHeader().setForeground(Color.WHITE); // Header text color
        logsTable.setGridColor(new Color(200, 200, 200)); // Set grid color to make it lighter
        logsTable.setShowGrid(true); // Show gridlines
        logsTable.setSelectionBackground(new Color(135, 206, 250)); // Highlight selected row
        logsTable.setSelectionForeground(Color.BLACK); // Selected row text color

        // Add alternating row colors
        logsTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 248, 255)); // Light blue for even rows
                } else {
                    c.setBackground(Color.WHITE); // White for odd rows
                }
                if (isSelected) {
                    c.setBackground(new Color(0, 123, 255)); // Highlight selected row
                }
                return c;
            }
        });

        // Scroll pane for table
        JScrollPane tableScrollPane = new JScrollPane(logsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom Section (Total Logs Display)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setBackground(new Color(255, 165, 0)); // Orange background

        JLabel totalLogsTextLabel = new JLabel("Total Logs:");
        totalLogsTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLogsLabel = new JLabel(String.valueOf(UserGenerator.getLoginTracker().size()));
        totalLogsLabel.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(totalLogsTextLabel);
        bottomPanel.add(totalLogsLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button Actions
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSearch(tableModel);
            }
        });

        // Set the frame visible
        setVisible(true);
    }


    private void performSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase(); // Make search case insensitive
        if (!searchQuery.isEmpty()) {
            DefaultTableModel tableModel = (DefaultTableModel) logsTable.getModel();
            tableModel.setRowCount(0); // Clear existing rows before searching

            // Loop through all the logs and add matching ones to the table
            for (org.example.pharmacymanagmentfrontend.Model.UserLogs userlog : UserGenerator.getLoginTracker()) {
                if (userlog.getUsername().toLowerCase().contains(searchQuery)) {
                    tableModel.addRow(new Object[]{userlog.getLoginTime(), userlog.getSuccessfulLogin(), userlog.getUsername(), userlog.getType()});
                }
            }

            // If no results, display a message or handle it accordingly
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No results found for: " + searchQuery, "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void resetSearch(DefaultTableModel tableModel) {
        searchField.setText(""); // Clear search field
        totalLogsLabel.setText(String.valueOf(UserGenerator.getLoginTracker().size())); // Reset the total logs count
        // Reload all data into the table
        tableModel.setRowCount(0); // Clear the table
        for (org.example.pharmacymanagmentfrontend.Model.UserLogs userlog : UserGenerator.getLoginTracker()) {
            tableModel.addRow(new Object[]{userlog.getLoginTime(), userlog.getSuccessfulLogin(), userlog.getUsername(), userlog.getType()});
        }
    }

}
