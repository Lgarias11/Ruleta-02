package Ruleta.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

    private IRepositorioResultados repositorio;
    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private String tipoMasJugado;
    private int rachaActual;
    private Map<String, Integer> frecuenciaApuestas;

    public Estadisticas(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
        this.totalJugadas = 0;
        this.victorias = 0;
        this.porcentajeVictorias = 0.0;
        this.rachaMaxima = 0;
        this.rachaActual = 0;
        this.tipoMasJugado = "Ninguna";
        this.frecuenciaApuestas = new HashMap<>();
    }

    public void registrarJugada(boolean esVictoria, String tipoApuesta) {
        this.totalJugadas++;

        if (esVictoria) {
            this.victorias++;
            this.rachaActual++;
            if (this.rachaActual > this.rachaMaxima) {
                this.rachaMaxima = this.rachaActual;
            }
        } else {
            this.rachaActual = 0;
        }

        this.porcentajeVictorias = ((double) this.victorias / this.totalJugadas) * 100.0;

        int conteo = this.frecuenciaApuestas.getOrDefault(tipoApuesta, 0) + 1;
        this.frecuenciaApuestas.put(tipoApuesta, conteo);
        actualizarTipoMasJugado();
    }

    private void actualizarTipoMasJugado() {
        int maxFrecuencia = 0;
        String masJugado = this.tipoMasJugado;

        for (Map.Entry<String, Integer> entry : this.frecuenciaApuestas.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                masJugado = entry.getKey();
            }
        }
        this.tipoMasJugado = masJugado;
    }

    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public String getTipoMasJugado() { return tipoMasJugado; }


    public void guardarEnHistorial(Resultado resultado) {
        if (repositorio != null) {
            repositorio.guardarResultado(resultado);
        }
    }

    public List<Resultado> recuperarHistorial() {
        if (repositorio != null) {
            return repositorio.obtenerHistorial();
        }
        // Si por alguna razón no hay repositorio, devolvemos una lista vacía para no romper nada
        return new ArrayList<>();
    }
}