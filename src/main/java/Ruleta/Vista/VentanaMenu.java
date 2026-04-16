package Ruleta.Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("RULETA - Menú Principal");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnSalir = new JButton("Salir");

    public VentanaMenu() {
        configurarVentana();
        armarSidebar();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Bienvenido. Usa el menú lateral.", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    private void armarSidebar() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.add(new JButton("Inicio"));
        panel.add(btnJugar);
        panel.add(new JButton("Historial"));
        panel.add(btnSalir);
        frame.add(panel, BorderLayout.WEST);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirRuleta());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void abrirRuleta() {
        frame.dispose();
        new VentanaRuleta().mostrarVentana();
    }

    private void cerrarSesion() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
