package com.unlu.modelos.montecarlopi.view;

import com.unlu.modelos.montecarlopi.model.Point;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MontecarloPIView extends JFrame {
    private final DrawingPanel drawingPanel;
    private final JLabel piLabel;
    private final JLabel pointsInsideLabel;
    private final JLabel totalPointsLabel;
    @Getter
    private final JButton startBtn;
    @Getter
    private final JButton stopBtn;
    @Getter
    private final JButton resetBtn;

    public MontecarloPIView() {
        super("Simulación Montecarlo π");

        drawingPanel = new DrawingPanel();
        piLabel = new JLabel("π ≈ 0");
        pointsInsideLabel = new JLabel("Puntos dentro del círculo: 0");
        totalPointsLabel = new JLabel("Puntos totales: 0");

        startBtn = new JButton("Iniciar");
        stopBtn = new JButton("Detener");
        resetBtn = new JButton("Reiniciar");

        setLayout(new BorderLayout());
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));

        JPanel labelsPanel = new JPanel(new GridLayout(1, 3));
        labelsPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        labelsPanel.add(piLabel);
        labelsPanel.add(pointsInsideLabel);
        labelsPanel.add(totalPointsLabel);

        piLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pointsInsideLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(startBtn);
        buttonsPanel.add(stopBtn);
        buttonsPanel.add(resetBtn);

        controlPanel.add(labelsPanel);
        controlPanel.add(buttonsPanel);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(700, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void update(List<Point> points, double pi, long inside, long total) {
        drawingPanel.setPoints(points);
        piLabel.setText(String.format("π ≈ %.6f", pi));
        pointsInsideLabel.setText("Puntos dentro del círculo: " + inside);
        totalPointsLabel.setText("Puntos totales: " + total);
    }

    private static class DrawingPanel extends JPanel {
        private List<Point> points;

        public void setPoints(List<Point> points) {
            this.points = points;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int size = Math.min(getWidth(), getHeight());
            int offsetX = (getWidth() - size) / 2;
            int offsetY = (getHeight() - size) / 2;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.drawOval(offsetX, offsetY, size, size);

            if (points != null) {
                for (Point p : points) {
                    int x = (int) ((p.getX() + 1) * size / 2) + offsetX;
                    int y = (int) ((1 - p.getY()) * size / 2) + offsetY;
                    g2d.setColor(p.isInside() ? Color.GREEN : Color.RED);
                    g2d.drawLine(x, y, x, y);
                }
            }
        }
    }
}
