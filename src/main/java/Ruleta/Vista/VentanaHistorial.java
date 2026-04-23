package Ruleta.Vista;

import Ruleta.Controlador.ResultadoController;
import Ruleta.Controlador.SessionController;
import Ruleta.Modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {

    private final JFrame frame = new JFrame("Historial de Jugadas - Casino Black Cat");
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final JTable tablaHistorial;
    private final DefaultTableModel modeloTabla;

    private final SessionController session;
    private final ResultadoController controlador;

    public VentanaHistorial(SessionController session) {
        this.session = session;
        this.controlador = new ResultadoController(session);

        String[] columnas = {"Número", "Color", "Apuesta", "Resultado", "Monto"};
        this.modeloTabla = new DefaultTableModel(columnas, 0);
        this.tablaHistorial = new JTable(modeloTabla);

        configurarVentana();
        cargarDatos();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnVolver);
        frame.add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        List<Resultado> historial = controlador.obtenerHistorialUsuario();

        if (historial != null) {
            for (Resultado r : historial) {
                Object[] fila = {
                        r.getNumeroObtenido(),
                        r.getColorObtenido(),
                        r.getTipoApuesta(),
                        r.isEsVictoria() ? "Ganó" : "Perdió",
                        r.getMontoGanado()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}