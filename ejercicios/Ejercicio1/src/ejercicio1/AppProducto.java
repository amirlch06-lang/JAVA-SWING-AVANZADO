package ejercicio1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AppProducto extends JFrame {
    private Producto producto;
    private JTextField txtNombre, txtPrecio, txtStock, txtCategoria;
    private JLabel lblResultado;
    public AppProducto() {
        setTitle("Gestión de Producto - Binding Manual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 360);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 8, 8));
        producto = new Producto("Laptop Gamer", 1250.00, 15, "Computación");
        add(new JLabel(" Nombre del Producto:"));
        txtNombre = new JTextField(producto.getNombre(), 15);
        add(txtNombre);
        add(new JLabel(" Precio ($):"));
        txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 15);
        add(txtPrecio);
        add(new JLabel(" Cantidad en Stock:"));
        txtStock = new JTextField(String.valueOf(producto.getCantidadStock()), 15);
        add(txtStock);
        add(new JLabel(" Categoría:"));
        txtCategoria = new JTextField(producto.getCategoria(), 15);
        add(txtCategoria);
        JButton btnActualizar = new JButton("Actualizar Producto");
        add(btnActualizar);
        lblResultado = new JLabel("<html><b>Información Actualizada:</b><br>Esperando cambios...</html>");
        add(lblResultado);
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nuevoNombre = txtNombre.getText();
                    double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
                    int nuevoStock = Integer.parseInt(txtStock.getText());
                    String nuevaCategoria = txtCategoria.getText();
                    producto.setNombre(nuevoNombre);
                    producto.setPrecio(nuevoPrecio);
                    producto.setCantidadStock(nuevoStock);
                    producto.setCategoria(nuevaCategoria);
                    lblResultado.setText("<html><b>Información Actualizada:</b><br>" +
                            "• Nombre: " + producto.getNombre() + "<br>" +
                            "• Precio: $" + producto.getPrecio() + "<br>" +
                            "• Stock: " + producto.getCantidadStock() + " unidades<br>" +
                            "• Categoría: " + producto.getCategoria() + "</html>");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppProducto.this,
                            "Por favor, ingrese números válidos en Precio y Stock.",
                            "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppProducto().setVisible(true);
        });
    }
}
