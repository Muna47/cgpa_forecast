package com.oop.views;

import com.oop.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame implements ActionListener {
    JButton button;

    public Welcome() {
        setTitle("CGPA Forecast");
        setSize(978, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bgImage = new ImageIcon("C:\\Users\\HP\\Pictures\\forecast_pics\\WELCOME.png");
        ImageIcon logo = new ImageIcon("C:\\Users\\HP\\Downloads\\_ੈ✧‧₊˚༄.jpeg");

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);

        Main main = new Main();
        button = main.button_func("Continue", 395, 320, 120, 40, 123, 134, 238);
        button.addActionListener(this);
        bgPanel.add(button);

        setContentPane(bgPanel);
        setIconImage(logo.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            dispose();
            SwingUtilities.invokeLater(() -> new Login().setVisible(true));
        }
    }
}
