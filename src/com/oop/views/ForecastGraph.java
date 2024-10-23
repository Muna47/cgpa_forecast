package com.oop.views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class ForecastGraph extends JFrame {
    private static int fileCount = 0;
    public ForecastGraph(double[] cgpaValues) {
        setTitle("CGPA Forecast Graph");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("C:\\Users\\HP\\Downloads\\_ੈ✧‧₊˚༄.jpeg");
        setIconImage(logo.getImage());

        XYSeries series = new XYSeries("CGPA Over Semesters");

        for (int i = 0; i < cgpaValues.length; i++) {
            series.add(i + 1, cgpaValues[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "CGPA Forecast",
                "Semester",
                "CGPA",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        setContentPane(chartPanel);
    }
}
