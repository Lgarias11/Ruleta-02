package Ruleta.Vista;

import Ruleta.Controlador.RuletaController;
import Ruleta.Controlador.SessionController;
import Ruleta.Modelo.*;
import Ruleta.Modelo.ApuestaTipo.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JComboBox<String> cbTipo = new JComboBox<>(new String[] {"Color", "Paridad"});
    private final JComboBox<String> cbSeleccion = new JComboBox<>(new String[] {"Rojo", "Negro", "Par", "Impar"});
    private final JTextField txtMonto = new JTextField("100", 5);
    private final JButton btnGirar = new JButton("Girar");
    private final JButton btnVolver = new JButton("Volver");
    private final JLabel lblSaldo = new JLabel("Saldo: 1000"); // Lo actualizaremos en el constructor
    private final JLabel lblResultado =  new JLabel("Esperando.....");
    private final JButton btnRecargar = new JButton("Recargar");
    private final SessionController session;
    private final RuletaController controlador;

    public VentanaRuleta(SessionController session) {
        this.session = session;

        this.controlador = new RuletaController(session.getMotorRuleta(), session);

        configurarVentana();
        armarPanelSuperior();
        armarPanelInferior();
        configurarEventos();

        lblSaldo.setText("Saldo: " + controlador.getSaldoActual());
    }

    private void configurarVentana() {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void armarPanelSuperior() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Tipo:")); panel.add(cbTipo);
        panel.add(new JLabel("Elige")); panel.add(cbSeleccion);
        panel.add(new JLabel("Monto:")); panel.add(txtMonto);
        panel.add(btnGirar); panel.add(lblSaldo);
        frame.add(panel, BorderLayout.NORTH);
    }

    private void armarPanelInferior() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btnVolver);
        panel.add(btnRecargar);
        panel.add(lblResultado);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnGirar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volverAlMenu());
        btnRecargar.addActionListener(e -> procesarRecarga());
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String seleccionStr = cbSeleccion.getSelectedItem().toString().toUpperCase();
            ApuestaBase apuesta = getApuestaBase(seleccionStr, monto);
            Resultado res = controlador.procesarJugada(apuesta);

            String estado = res.isEsVictoria() ? "Ganaste" : "Perdiste";
            lblResultado.setText("Número " + res.getNumeroObtenido() + " (" + res.getColorObtenido() + ") | " + estado);

            lblSaldo.setText("Saldo: " + controlador.getSaldoActual());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto válido.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, "Tipo de apuesta no válida.");
        }
    }

    private static ApuestaBase getApuestaBase(String seleccionStr, int monto) {
        ApuestaBase apuesta = null;
        switch (seleccionStr) {
            case "ROJO":
                apuesta = new ApuestaRojo(monto);
                break;
            case "NEGRO":
                apuesta = new ApuestaNegro(monto);
                break;
            case "PAR":
                apuesta = new ApuestaPar(monto);
                break;
            case "IMPAR":
                apuesta = new ApuestaImpar(monto);
                break;
        }
        ;

        assert apuesta != null;
        return apuesta;
    }

    private void procesarRecarga() {
        try {
            String input = JOptionPane.showInputDialog(frame, "Ingrese el monto a recargar:", "Recargar Saldo", JOptionPane.QUESTION_MESSAGE);

            if (input != null && !input.trim().isEmpty()) {
                int monto = Integer.parseInt(input);

                if (monto > 0) {
                    controlador.recargarSaldo(monto);
                    lblSaldo.setText("Saldo: " + controlador.getSaldoActual());
                    JOptionPane.showMessageDialog(frame, "Recarga exitosa. Nuevo saldo: " + controlador.getSaldoActual());
                } else {
                    JOptionPane.showMessageDialog(frame, "El monto debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlMenu() {
        frame.dispose();
        new VentanaMenu(session).mostrarVentana();
    }

    public void mostrarVentana(){
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}