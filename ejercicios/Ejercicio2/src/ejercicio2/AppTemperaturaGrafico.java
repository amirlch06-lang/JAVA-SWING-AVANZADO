package ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AppTemperaturaGrafico extends JFrame {
    private JTextField[] txtTemperaturas = new JTextField[7];
    private String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private int[] temperaturas = {22, 24, 19, 21, 25, 23, 20}; // Valores iniciales de prueba
    private PanelGrafico panelGrafico;
    public AppTemperaturaGrafico() {
        setTitle("Registro y Gráfico de Temperaturas Semanales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panelEntrada = new JPanel(new GridLayout(2, 7, 4, 4));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Ingrese las temperaturas (°C)"));
        for (int i = 0; i < 7; i++) {
            panelEntrada.add(new JLabel(dias[i], SwingConstants.CENTER));
        }
        for (int i = 0; i < 7; i++) {
            txtTemperaturas[i] = new JTextField(String.valueOf(temperaturas[i]), 5);
            panelEntrada.add(txtTemperaturas[i]);
        }
        add(panelEntrada, BorderLayout.NORTH);
        panelGrafico = new PanelGrafico();
        add(panelGrafico, BorderLayout.CENTER);
        JPanel panelBoton = new JPanel();
        JButton btnMostrar = new JButton("Mostrar Gráfico");
        panelBoton.add(btnMostrar);
        add(panelBoton, BorderLayout.SOUTH);
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < 7; i++) {
                        temperaturas[i] = Integer.parseInt(txtTemperaturas[i].getText().trim());
                    }
                    panelGrafico.setTemperaturas(temperaturas);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppTemperaturaGrafico.this,
                            "Por favor, ingrese números enteros válidos en todos los días.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    class PanelGrafico extends JPanel {
        private int[] temps;
        public PanelGrafico() {
            this.temps = temperaturas;
        }
        public void setTemperaturas(int[] temps) {
            this.temps = temps;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int margenX = 70;
            int margenY = 60;
            int graficoAncho = width - 2 * margenX;
            int graficoAlto = height - 2 * margenY;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(margenX, height - margenY, width - margenX, height - margenY); 
            g2d.drawLine(margenX, margenY, margenX, height - margenY);
            g2d.drawString("Temp (°C)", margenX - 55, margenY - 10);
            g2d.drawString("Días de la Semana", width / 2 - 45, height - margenY + 40);
            int maxTemp = 10;
            for (int t : temps) {
                if (t > maxTemp) maxTemp = t;
            }
            maxTemp = (int) (maxTemp * 1.25); 
            int lineasY = 5;
            for (int i = 0; i <= lineasY; i++) {
                int valorY = (maxTemp * i) / lineasY;
                int posY = height - margenY - (graficoAlto * i / lineasY);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(margenX, posY, width - margenX, posY);
                g2d.setColor(Color.BLACK);
                g2d.drawString(valorY + "°C", margenX - 45, posY + 5);
            }
            int pasoX = graficoAncho / (temps.length + 1);
            int[] xPoints = new int[temps.length];
            int[] yPoints = new int[temps.length];
            for (int i = 0; i < temps.length; i++) {
                xPoints[i] = margenX + (i + 1) * pasoX;
                yPoints[i] = height - margenY - (int) ((double) temps[i] / maxTemp * graficoAlto);
            }
            g2d.setColor(new Color(30, 144, 255)); 
            g2d.setStroke(new BasicStroke(3.0f)); 
            for (int i = 0; i < temps.length - 1; i++) {
                g2d.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
            }
            for (int i = 0; i < temps.length; i++) {
                g2d.setColor(Color.RED);
                g2d.fillOval(xPoints[i] - 6, yPoints[i] - 6, 12, 12);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(xPoints[i] - 6, yPoints[i] - 6, 12, 12);
                g2d.drawString(temps[i] + "°C", xPoints[i] - 12, yPoints[i] - 15);
                g2d.drawString(dias[i], xPoints[i] - 20, height - margenY + 20);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppTemperaturaGrafico().setVisible(true);
        });
    }
}
