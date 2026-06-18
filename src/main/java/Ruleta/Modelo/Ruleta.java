package Ruleta.Modelo;

import java.util.Random;
import Ruleta.Modelo.ApuestaTipo.ApuestaBase;

public class Ruleta {
    public static final int numero_max = 36;
    private final Random random = new Random();
    private int saldo;
    private Estadisticas estadisticas;

    public Ruleta(IRepositorioResultados repositorio, int saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido");
        }
        this.saldo = saldoInicial;
        this.estadisticas = new Estadisticas(repositorio);
    }

    public int generarNum() {
        return random.nextInt(numero_max + 1);
    }

    public String obtenerColor(int numero) {
        if (numero == 0) return "Verde";
        return (numero % 2 == 0) ? "Rojo" : "Negro";
    }

    public void actualizarSaldo(int monto, boolean gano) {
        if (gano) saldo += monto;
        else saldo -= monto;
    }

    public void recargarSaldo(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    public int getSaldo() {
        return saldo;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void validarApuesta(ApuestaBase apuesta)  {
        if (apuesta == null) {
            throw new IllegalArgumentException("Apuesta requerida");
        }
        if (apuesta.getMonto() > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }
}