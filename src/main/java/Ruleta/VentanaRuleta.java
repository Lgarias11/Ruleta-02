package Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final Ruleta motor = new Ruleta();
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JComboBox<String> cbTipo = new JComboBox<>(new String[] {"Color", "Paridad"});
    private final JComboBox<String> cbSeleccion = new JComboBox<>(new String[] {"Rojo", "Negro", "Par", "Impar"});
    private final JTextField txtMonto = new JTextField("100", 5);
    private final JButton btnGirar = new JButton("Girar");
    private final JButton btnVolver = new JButton("Volver");
    private final JLabel lblSaldo = new JLabel("Saldo: 1000");
    private final JLabel lblResultado =  new JLabel("Esperando.....");

    public VentanaRuleta() {
        configurarVentana();
        armarPanelSuperior();
        armarPanelInferior();
        configurarEventos();
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
        panel.add(lblResultado);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnGirar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volverAlMenu());
    }

    private void jugar() {
        int monto = Integer.parseInt(txtMonto.getText());
        String tipo = cbTipo.getSelectedItem().toString();
        String seleccion = cbSeleccion.getSelectedItem().toString();
        procesarGiro(tipo, seleccion, monto);
    }

    private void procesarGiro(String tipo, String seleccion, int monto) {
        int numero = motor.generarNum();
        boolean gano = motor.esGanador(numero, tipo, seleccion);
        motor.actualizarSaldo(monto, gano);
        mostrarResultado(numero, gano);
    }

    private void volverAlMenu() {
        frame.dispose();
        new VentanaMenu().mostrarVentana();
    }

    private void mostrarResultado(int numero, boolean gano) {
        String color = motor.obtenerColor(numero);
        String estado = gano ? "Ganaste" : "Perdiste";
        lblResultado.setText("Numero" + numero + " ("+ color +") | " + estado);
        lblSaldo.setText("Saldo:" + motor.getSaldo());
    }

    public void mostrarVentana(){
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

