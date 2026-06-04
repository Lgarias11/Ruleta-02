package Ruleta.Modelo;

import java.util.Random;

public class Ruleta {
    public static final int numero_max = 36;
    private final Random random = new Random();
    private int saldo = 1000;
    private Estadisticas estadisticas;

    public Ruleta(IRepositorioResultados repositorio) {
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
}