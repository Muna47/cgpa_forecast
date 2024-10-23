package com.oop.views;

import com.oop.Main;
import com.oop.controller.Login_info;
import com.oop.controller.Reg_info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JButton login, reg;

    Login() {
        setTitle("CGPA Forecast");
        setSize(889, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon logo = new ImageIcon("C:\\Users\\HP\\Downloads\\_ੈ✧‧₊˚༄.jpeg");
        setIconImage(logo.getImage());

        ImageIcon bgImage = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\login.png");
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);

        Main main = new Main();
        login = main.button_func("Login", 555, 195, 140, 50, 123, 134, 238);
        login.addActionListener(this);
        bgPanel.add(login);

        reg = main.button_func("Registration", 545, 265, 160, 50, 123, 134, 238);
        reg.addActionListener(this);
        bgPanel.add(reg);

        setContentPane(bgPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            dispose();
            SwingUtilities.invokeLater(() -> new Login_info().setVisible(true));
        }
        if (e.getSource() == reg) {
            dispose();
            SwingUtilities.invokeLater(() -> new Reg_info().setVisible(true));
        }
    }
}
