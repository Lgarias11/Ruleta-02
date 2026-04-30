package Ruleta.Vista;

import Ruleta.Modelo.Estadisticas;
import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas extends JFrame {

    public VentanaEstadisticas(Estadisticas estadisticas) {
        setTitle("Estadísticas");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel lblTotal = new JLabel("Total Jugadas: " + estadisticas.getTotalJugadas(), SwingConstants.CENTER);
        JLabel lblVictorias = new JLabel("Victorias: " + estadisticas.getVictorias(), SwingConstants.CENTER);
        JLabel lblPorcentaje = new JLabel("Porcentaje Victorias: " + String.format("%.2f", estadisticas.getPorcentajeVictorias()) + "%", SwingConstants.CENTER);
        JLabel lblRacha = new JLabel("Racha Máxima: " + estadisticas.getRachaMaxima(), SwingConstants.CENTER);
        JLabel lblTipo = new JLabel("Tipo Más Jugado: " + estadisticas.getTipoMasJugado(), SwingConstants.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> dispose());

        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblVictorias.setFont(new Font("Arial", Font.BOLD, 14));
        lblPorcentaje.setFont(new Font("Arial", Font.BOLD, 14));
        lblRacha.setFont(new Font("Arial", Font.BOLD, 14));
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));

        add(lblTotal);
        add(lblVictorias);
        add(lblPorcentaje);
        add(lblRacha);
        add(lblTipo);
        add(btnVolver);
    }
}