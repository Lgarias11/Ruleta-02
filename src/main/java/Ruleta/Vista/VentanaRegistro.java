package Ruleta.Vista;

import Ruleta.Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    // --- UI ---
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JLabel lblNombre = new JLabel("Nombre Completo:");
    private final JTextField txtNombre = new JTextField();
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRegistro() {
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));
        agregarComponentes();
    }

    private void agregarComponentes() {
        frame.add(lblNombre);  frame.add(txtNombre);
        frame.add(lblUsuario); frame.add(txtUsuario);
        frame.add(lblClave);   frame.add(txtClave);
        frame.add(btnVolver);  frame.add(btnGuardar);
    }

    private void configurarEventos() {
        btnGuardar.addActionListener(e -> intentarRegistro());
        btnVolver.addActionListener(e -> volverAlLogin());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void intentarRegistro() {
        String nombre = txtNombre.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (camposValidos(nombre, usuario, clave)) {
            procesarExito(nombre, usuario, clave);
        } else {
            mostrarError();
        }
    }

    private boolean camposValidos(String n, String u, String c) {
        return !n.isEmpty() && !u.isEmpty() && !c.isEmpty();
    }

    private void procesarExito(String n, String u, String c) {
        VentanaLogin.usuarios.add(new Usuario(u, c, n));
        JOptionPane.showMessageDialog(frame, "¡Cuenta creada exitosamente!");
        volverAlLogin();
    }

    private void mostrarError() {
        JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios",
                "Error de Validación", JOptionPane.WARNING_MESSAGE);
    }

    private void volverAlLogin() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}