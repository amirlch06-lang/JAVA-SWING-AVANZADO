package ejercicios;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.*;
import java.io.IOException;

public class EfectosSonido extends JFrame {

    public EfectosSonido() {

        setTitle("Reproductor de Efectos de Sonido");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel titulo = new JLabel("EFECTOS DE SONIDO");

        JButton btnAplausos = new JButton("Aplausos");
        JButton btnCampana = new JButton("Campana");
        JButton btnExplosion = new JButton("Explosion");

        add(titulo);
        add(btnAplausos);
        add(btnCampana);
        add(btnExplosion);

        btnAplausos.addActionListener(e ->
            reproducir("aplausos.wav"));

        btnCampana.addActionListener(e ->
            reproducir("campana.wav"));

        btnExplosion.addActionListener(e ->
            reproducir("explosion.wav"));

        setVisible(true);
    }

    public void reproducir(String archivo) {

        try {
            File sonido = new File(archivo);

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(sonido);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);
            clip.start();

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException ex) {

            JOptionPane.showMessageDialog(this,
                "No se pudo reproducir: " + archivo);
        }
    }

    public static void main(String[] args) {
        new EfectosSonido();
    }
}
