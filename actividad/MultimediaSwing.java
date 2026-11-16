package actividad;


import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class MultimediaSwing extends JFrame {

    public MultimediaSwing() {

        setTitle("Aplicacion Multimedia");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        JButton btnReproducir =
            new JButton("Reproducir Audio");

        add(btnReproducir);

        btnReproducir.addActionListener(e -> {
            try {
                File archivo = new File("musica.wav");

                AudioInputStream audio =
                    AudioSystem.getAudioInputStream(archivo);

                Clip clip = AudioSystem.getClip();

                clip.open(audio);
                clip.start();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al reproducir el audio: " +
                    ex.getMessage());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MultimediaSwing();
    }
}
