package com.oop.controller;

import com.oop.models.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reg_info extends JFrame implements ActionListener {
    private final JTextField username = new JTextField(15);
    private final JTextField user_roll = new JTextField(15);
    private final JPasswordField password = new JPasswordField(15);

    JCheckBox termsCheckBox = new JCheckBox("Accept Terms & Conditions");
    JButton registerButton = new JButton("Register");

    public Reg_info() {
        setTitle("Sign Up");
        ImageIcon logo = new ImageIcon("C:\\Users\\HP\\Downloads\\_ੈ✧‧₊˚༄.jpeg");
        setIconImage(logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel nameLabel = new JLabel("Name:");
        JLabel rollLabel = new JLabel("Roll:");
        JLabel passLabel = new JLabel("Password:");

        Font font = new Font("Times New Roman", Font.BOLD, 16);
        nameLabel.setFont(font);
        rollLabel.setFont(font);
        passLabel.setFont(font);
        registerButton.setFont(font);
        termsCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        Dimension fieldDimension = new Dimension(200, 30);

        username.setPreferredSize(fieldDimension);
        user_roll.setPreferredSize(fieldDimension);
        password.setPreferredSize(fieldDimension);
        registerButton.setPreferredSize(new Dimension(100, 30));

        formPanel.add(nameLabel);
        formPanel.add(username);
        formPanel.add(rollLabel);
        formPanel.add(user_roll);
        formPanel.add(passLabel);
        formPanel.add(password);
        formPanel.add(termsCheckBox);
        formPanel.add(registerButton);

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgImage = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\reg_bg.png");
                g.drawImage(bgImage.getImage(), 0, 0, 350, 450, this);
            }
        };
        imagePanel.setPreferredSize(new Dimension(350, 450));

        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);

        registerButton.addActionListener(this);

        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            if (!termsCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept the terms and conditions.");
                return;
            }
            String Name = username.getText();
            String Roll = user_roll.getText();
            String Pass = new String(password.getPassword());
            reg_user(Name, Roll, Pass);
        }
    }

    public void reg_user(String Name, String Roll, String Pass) {
        if (Name.isEmpty() || Pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and Password are required.");
            return;
        }
        try (Connection connection = DatabaseConnection.getconnection()) {
            String query = "INSERT INTO info (name, roll, pass, grade, semester) VALUES (?,?,?,null,null)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Name);
            statement.setInt(2, Integer.parseInt(Roll));
            statement.setString(3, Pass);

            int rowAffected = statement.executeUpdate();

            if (rowAffected > 0) {
                this.dispose();
                JOptionPane.showMessageDialog(this, "Registration completed successfully!!");
                new Semester_choice(Name, Pass);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
