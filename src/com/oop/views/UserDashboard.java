package com.oop.views;

import com.oop.controller.Semester_choice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame {
    public UserDashboard(int roll, String name, double grade, int sem_no, String pass) {
        setTitle("User Dashboard");
        setSize(676, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set frame icon
        ImageIcon logo = new ImageIcon("C:\\Users\\HP\\Downloads\\_ੈ✧‧₊˚༄.jpeg");
        setIconImage(logo.getImage());

        // Load the provided image as background
        ImageIcon bgImage = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\dash.png");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null); // Absolute positioning

        // Font for values
        Font valueFont = new Font("SansSerif", Font.BOLD, 16);

        // Values to display
        JLabel nameValue = new JLabel(name, SwingConstants.CENTER);
        nameValue.setFont(valueFont);
        nameValue.setBounds(350, 100, 150, 30); // Position of the second column value (centered)

        JLabel rollValue = new JLabel(String.valueOf(roll), SwingConstants.CENTER);
        rollValue.setFont(valueFont);
        rollValue.setBounds(340, 140, 150, 30); // Roll value

        JLabel semValue = new JLabel(String.valueOf(sem_no), SwingConstants.CENTER);
        semValue.setFont(valueFont);
        semValue.setBounds(340, 170, 150, 30); // Semester value

        JLabel gradeValue = new JLabel(String.format("%.2f", grade), SwingConstants.CENTER);
        gradeValue.setFont(valueFont);
        gradeValue.setBounds(340, 210, 150, 30); // Predicted grade value

        // Create buttons
        JButton forecastButton = new JButton("New Forecast");
        forecastButton.setFocusable(false);
        forecastButton.setBounds(120, 270, 150, 40); // Position similar to image
        forecastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Semester_choice(name, pass); // Navigate to new forecast screen
            }
        });

        JButton closeButton = new JButton("Exit");
        closeButton.setFocusable(false);
        closeButton.setBounds(380, 270, 150, 40); // Position similar to image
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the application
            }
        });

        // Add all components to the panel
        panel.add(nameValue);
        panel.add(rollValue);
        panel.add(semValue);
        panel.add(gradeValue);
        panel.add(forecastButton);
        panel.add(closeButton);

        add(panel);
        setVisible(true);
    }
}
