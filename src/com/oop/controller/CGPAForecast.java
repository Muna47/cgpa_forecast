package com.oop.controller;

import com.oop.models.DatabaseConnection;
import com.oop.views.ForecastGraph;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CGPAForecast extends JFrame {
    private List<JTextField> cgpaFields;
    private int n;
    private String name, pass;

    public CGPAForecast(int n, String name, String pass) {
        this.name = name;
        this.pass = pass;
        this.n = n;
        this.cgpaFields = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CGPA Info");  // Window title

        // Set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(n + 2, 2, 10, 10));  // n fields + 1 label + 1 button
        panel.setBackground(new Color(24, 11, 89));  // Background color similar to the gradient in your image
        panel.setOpaque(true);

        // Adding the "cgpa info" label at the top
        JLabel titleLabel = new JLabel("cgpa info");
        titleLabel.setForeground(Color.WHITE);  // White color for the text
        titleLabel.setFont(new Font("League Spartan", Font.BOLD, 24));  // League Spartan font, bold, size 24
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        // Empty label to keep the GridLayout balanced
        panel.add(new JLabel());

        // Adding CGPA input fields
        for (int i = 1; i <= n; i++) {
            JLabel label = new JLabel("Enter CGPA for semester " + i + ":");
            label.setForeground(Color.WHITE);
            panel.add(label);

            JTextField cgpaField = new JTextField(10);
            cgpaFields.add(cgpaField);
            panel.add(cgpaField);
        }

        // Submit button
        JButton confirmButton = new JButton("Submit");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                forecast();
            }
        });

        // Add the button to the panel
        panel.add(new JLabel());  // Empty space to align the button
        panel.add(confirmButton);

        // Set content pane and other properties
        setContentPane(panel);
        setSize(400, 300 + (n * 40));  // Adjust the size based on the number of fields
        setVisible(true);
        setLocationRelativeTo(null);  // Center the window on the screen
    }

    public void forecast() {
        try {
            SimpleRegression regression = new SimpleRegression();
            double[] cgpaValues = new double[n];

            for (int i = 1; i <= n; i++) {
                double cgpa = Double.parseDouble(cgpaFields.get(i - 1).getText());
                regression.addData(i, cgpa);  // Semester number as X, CGPA as Y
                cgpaValues[i - 1] = cgpa;
            }

            double CGPA = regression.predict(n + 1);
            double predictedCGPA = Math.round(CGPA * 100.0) / 100.0;

            try (Connection connection = DatabaseConnection.getconnection()) {
                if (connection == null) {
                    System.out.println("Connection is null. Check the DatabaseConnection class.");
                    return;
                }

                String sql = "UPDATE info SET grade = ?, semester = ? WHERE name = ? AND pass = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, predictedCGPA);
                statement.setInt(2, n + 1);
                statement.setString(3, name);
                statement.setString(4, pass);
                int rows_affected = statement.executeUpdate();

                if (rows_affected > 0) {
                    System.out.println("Update successful. Rows affected: " + rows_affected);
                } else {
                    System.out.println("No rows updated. Check if the user exists.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            JOptionPane.showMessageDialog(null, "Predicted CGPA for semester " + (n + 1) + " is: " + predictedCGPA);
            ForecastGraph graph = new ForecastGraph(cgpaValues);
            graph.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for CGPA.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }
}
