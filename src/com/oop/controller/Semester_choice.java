package com.oop.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Semester_choice extends JFrame implements ActionListener {
    private String name, pass;
    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon background = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\semester.png"); // Load the background image
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };

    JLabel label = new JLabel("Semester number:");
    JComboBox<Integer> combo;
    JButton confirmButton;

    public Semester_choice(String name, String pass) {
        this.name = name;
        this.pass = pass;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Semester Selection");

        Integer[] option = {2, 3, 4, 5, 6, 7};
        combo = new JComboBox<>(option);
        combo.setSelectedIndex(0);

        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setPreferredSize(new Dimension(230, 30));

        label.setFont(new Font("League Spartan", Font.BOLD, 18));
        label.setForeground(Color.WHITE);  // Label color on the background
        label.setHorizontalAlignment(SwingConstants.CENTER);

        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(100, 30));
        confirmButton.addActionListener(this);

        panel.setLayout(null);

        label.setBounds(100, 70, 200, 30);
        combo.setBounds(80, 100, 230, 30);
        confirmButton.setBounds(150, 200, 100, 30);

        panel.add(label);
        panel.add(combo);
        panel.add(confirmButton);

        setSize(400, 300);
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            int num = (int) combo.getSelectedItem();
            new CGPAForecast(num, name, pass);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new Semester_choice("user_name", "user_pass");
    }
}
