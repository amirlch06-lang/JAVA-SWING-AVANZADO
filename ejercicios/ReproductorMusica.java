package ejercicios;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.*;

public class ReproductorMusica extends JFrame {

    private Clip clip;
    private long posicion = 0;

    public ReproductorMusica() {

        setTitle("Reproductor de Musica");
        setSize(400, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titulo = new JLabel("MI REPRODUCTOR MUSICAL");

        JButton btnReproducir =
            new JButton("Reproducir");

        JButton btnPausar =
            new JButton("Pausar");

        JButton btnReanudar =
            new JButton("Reanudar");

        add(titulo);
        add(btnReproducir);
        add(btnPausar);
        add(btnReanudar);

        // Cargar archivo de audio
        try {
            File archivo = new File("musica.wav");

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(archivo);

            clip = AudioSystem.getClip();
            clip.open(audio);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar la musica: " +
                ex.getMessage());
        }

        // Boton reproducir
        btnReproducir.addActionListener(e -> {
            if (clip != null) {
                posicion = 0;
                clip.setFramePosition(0);
                clip.start();
            }
        });

        // Boton pausar
        btnPausar.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                posicion = clip.getLongFramePosition();
                clip.stop();
            }
        });

        // Boton reanudar
        btnReanudar.addActionListener(e -> {
            if (clip != null && !clip.isRunning()) {
                if (posicion >= clip.getFrameLength()) {
                    posicion = 0;
                }

                clip.setFramePosition((int) posicion);
                clip.start();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ReproductorMusica();
    }
}
