package com.oop.controller;


import com.oop.models.DatabaseConnection;
import com.oop.views.UserDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_info extends JFrame {
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton;

    JLabel l1 = new JLabel("USER NAME");
    JLabel l2 = new JLabel("PASSWORD");

    public Login_info() {
        setTitle("Login Page");
        setSize(889, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bgImage = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\log_bg.png");
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);

        // Customize the username and password labels
        l1.setBounds(345, 180, 200, 25);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        bgPanel.add(l1);

        l2.setBounds(345, 240, 200, 25);
        l2.setFont(new Font("Arial", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        bgPanel.add(l2);

        // Username field
        usernameField.setBounds(325, 210, 250, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        bgPanel.add(usernameField);

        // Password field
        passwordField.setBounds(325, 270, 250, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        bgPanel.add(passwordField);

        // Login button
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(375, 330, 150, 35);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(58, 134, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        bgPanel.add(loginButton);

        // Set action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        // Set the content pane to the background panel
        setContentPane(bgPanel);
    }

    public void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection connection = DatabaseConnection.getconnection()) {
            String query = "SELECT roll, name, grade, semester FROM info WHERE name = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int roll = rs.getInt("roll");
                String name = rs.getString("name");
                double grade = rs.getDouble("grade");
                int semester = rs.getInt("semester");

                new UserDashboard(roll, name, grade, semester, password);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

