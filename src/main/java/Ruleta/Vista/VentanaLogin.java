package Ruleta.Vista;

import Ruleta.Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<Usuario> usuarios = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrarse");

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("donnie", "blackcat", "Don Donnie"));
            usuarios.add(new Usuario("manue", "gatonegro", "Manuela"));
        }
    }

    private void configurarVentana() {
        frame.setSize(300, 150 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));
        agregarComponentes();
    }

    private void agregarComponentes() {
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(new JLabel(""));
        frame.add(btnRegistrar);
        frame.add(btnIngresar);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String nombre = validarCredenciales(u, p);
        procesarAcceso(nombre);
    }

    private void procesarAcceso(String nombre) {
        if (!nombre.isEmpty()) {
            mostrarExito(nombre);
        } else {
            mostrarError();
        }
    }

    private void abrirRegistro() {
        frame.dispose(); // Cierra el login
        new VentanaRegistro().mostrarVentana();
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre();
            }
        }
        return "";
    }

    private void mostrarExito(String nombre) {
        JOptionPane.showMessageDialog(frame, "¡Bienvenido" + nombre + "!");
        frame.dispose();
        new VentanaMenu().mostrarVentana();
    }

    private void mostrarError() {
        JOptionPane.showMessageDialog(frame, "Credenciales incorrectas",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
