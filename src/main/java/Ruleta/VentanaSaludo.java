package Ruleta;

import javax.swing.*;

public class VentanaSaludo {
    private final JFrame frame;

    public VentanaSaludo(String nombreUsuario) {
        frame = new JFrame("Casino Black Cat - Menú Principal");
        configurarVentana(nombreUsuario);
    }

    private void configurarVentana(String nombreUsuario) {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel saludo = new JLabel("Bienvenido a la Ruleta, " + nombreUsuario, SwingConstants.CENTER);
        frame.add(saludo);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
