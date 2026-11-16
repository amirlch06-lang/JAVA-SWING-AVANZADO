package actividad;

import javax.swing.*;
import java.awt.*;

class Persona {
    private String nombre;
    private int edad;
    private String sexo;

    public Persona(String nombre, int edad, String sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getSexo() { return sexo; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}

public class BindingDatos extends JFrame {

    JTextField txtNombre = new JTextField(15);
    JTextField txtEdad = new JTextField(15);
    JTextField txtSexo = new JTextField(15);
    JLabel lblResultado = new JLabel("Ingrese los datos");

    Persona persona = new Persona("", 0, "");

    public BindingDatos() {
        setTitle("Binding de Datos");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Nombre:"));
        add(txtNombre);

        add(new JLabel("Edad:"));
        add(txtEdad);

        add(new JLabel("Sexo:"));
        add(txtSexo);

        JButton btnActualizar = new JButton("Actualizar Modelo");
        add(btnActualizar);
        add(lblResultado);

        btnActualizar.addActionListener(e -> {
            try {
                persona.setNombre(txtNombre.getText());
                persona.setEdad(Integer.parseInt(txtEdad.getText()));
                persona.setSexo(txtSexo.getText());

                lblResultado.setText(
                    "<html>Nombre: " + persona.getNombre() +
                    "<br>Edad: " + persona.getEdad() +
                    "<br>Sexo: " + persona.getSexo() +
                    "</html>"
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Ingrese una edad válida");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BindingDatos();
    }
}
