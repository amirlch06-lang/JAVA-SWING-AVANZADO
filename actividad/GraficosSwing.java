package actividad;


import javax.swing.*;
import java.awt.*;

public class GraficosSwing extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Activar suavizado de bordes
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Grafico simple: linea
        g2.setColor(Color.BLUE);
        g2.drawLine(30, 40, 180, 40);

        // Rectangulo
        g2.setColor(Color.RED);
        g2.fillRect(30, 70, 100, 60);

        // Circulo
        g2.setColor(Color.GREEN);
        g2.fillOval(160, 70, 80, 80);

        // Texto
        g2.setColor(Color.BLACK);
        g2.drawString("Graficos en Java Swing", 30, 190);

        // Grafico avanzado: rotacion
        g2.rotate(Math.toRadians(30), 300, 120);
        g2.setColor(Color.ORANGE);
        g2.fillRect(260, 90, 80, 50);

        g2.rotate(-Math.toRadians(30), 300, 120);

        // Escalado mediante dimensiones
        g2.setColor(Color.MAGENTA);
        g2.fillRect(260, 220, 120, 25);
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Graficos Swing");

        ventana.setSize(450, 350);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.add(new GraficosSwing());
        ventana.setVisible(true);
    }
}
