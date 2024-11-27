package org.example.pharmacymanagmentfrontend.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagementDashboard extends JFrame {

    private UserLogs userLogs;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public ManagementDashboard() {
        // Set up the main frame
        setTitle("Management Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Left Navigation Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.decode("#eff2f1"));
        leftPanel.setPreferredSize(new Dimension(200, 600));

        // Add buttons to switch between screens
        JButton userLogsButton = new JButton("User Logs");
        userLogsButton.setBackground(Color.orange);
        userLogsButton.setForeground(Color.white);
        userLogsButton.setFocusPainted(false);
   //     userLogsButton.setBorderPainted(false);

        JButton reportsButton = new JButton("Reports");
        reportsButton.setBackground(Color.orange);
        reportsButton.setForeground(Color.white);
        reportsButton.setFocusPainted(false);

        JButton settingsButton = new JButton("Settings");
        reportsButton.setBackground(Color.orange);
        reportsButton.setForeground(Color.white);
        reportsButton.setFocusPainted(false);

        userLogsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reportsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(50)); // Spacer
        leftPanel.add(userLogsButton);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(reportsButton);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(settingsButton);

        add(leftPanel, BorderLayout.WEST);

        // Main Panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different screens to the main panel
        mainPanel.add(createUserLogsScreen(), "UserLogs");
        mainPanel.add(createReportsScreen(), "Reports");
        mainPanel.add(createSettingsScreen(), "Settings");

        add(mainPanel, BorderLayout.CENTER);

        // Button Actions
        userLogsButton.addActionListener(e -> cardLayout.show(mainPanel, "UserLogs"));
        reportsButton.addActionListener(e -> cardLayout.show(mainPanel, "Reports"));
        settingsButton.addActionListener(e -> cardLayout.show(mainPanel, "Settings"));

        // Set the frame visible
        setVisible(true);
    }

    // Screen 1: User Logs
    private JPanel createUserLogsScreen() {
        // Create an instance of the UserLogs class
        UserLogs userLogsFrame = new UserLogs();

        // Extract the content pane of the UserLogs frame
        JPanel userLogsPanel = new JPanel(new BorderLayout());
        userLogsPanel.add(userLogsFrame.getContentPane(), BorderLayout.CENTER);

        return userLogsPanel;
    }


    // Screen 2: Reports
    private JPanel createReportsScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("Reports", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));

        JTextArea textArea = new JTextArea("Reports Content Here...");
        panel.add(header, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        return panel;
    }

    // Screen 3: Settings
    private JPanel createSettingsScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("Settings", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Setting 1:"));
        formPanel.add(new JTextField());
        formPanel.add(new JLabel("Setting 2:"));
        formPanel.add(new JTextField());

        panel.add(header, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }




    public void setUserLogs() {
        userLogs = new UserLogs();
    }
}
