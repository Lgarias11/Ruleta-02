package Ruleta.Vista;

import Ruleta.Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("RULETA - Menú Principal");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");

    private final SessionController session;

    public VentanaMenu(SessionController session) {
        this.session = session;
        configurarVentana();
        armarSidebar();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String nombre = session.getUsuarioActual() != null ? session.getUsuarioActual().getNombre() : "Jugador";
        frame.add(new JLabel("Bienvenido " + nombre + ". Usa el menú lateral.", SwingConstants.CENTER), BorderLayout.CENTER);
    }

    private void armarSidebar() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.add(new JButton("Inicio"));
        panel.add(btnJugar);
        panel.add(btnHistorial); // Se usa la variable extraída
        panel.add(btnSalir);
        frame.add(panel, BorderLayout.WEST);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> abrirRuleta());
        btnHistorial.addActionListener(e -> abrirHistorial());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void abrirRuleta() {
        frame.dispose();
        new VentanaRuleta(session).mostrarVentana();
    }

    private void abrirHistorial() {
        frame.dispose();

        new VentanaHistorial(session).mostrarVentana();
    }

    private void cerrarSesion() {
        session.cerrarSesion();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}