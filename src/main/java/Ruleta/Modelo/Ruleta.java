package Ruleta.Modelo;

import java.util.Random;

public class Ruleta {
    public static final int numero_max = 36;
    private final Random random = new Random();
    private int saldo = 1000;
    private Estadisticas estadisticas;

    public Ruleta() {
        this.estadisticas = new Estadisticas();
    }

    public int generarNum() {
        return random.nextInt(numero_max + 1);
    }

    public String obtenerColor(int numero) {
        if (numero == 0) return "Verde";
        return (numero % 2 == 0) ? "Rojo" : "Negro";
    }

    public String obtenerParidad(int numero) {
        if (numero == 0) return "Cero";
        return (numero % 2 == 0) ? "Par" : "Impar";
    }

    public boolean esGanador(int numero, String tipo, String seleccion) {
        if (tipo.equals("Color")) return obtenerColor(numero).equals(seleccion);
        if (tipo.equals("Paridad")) return obtenerParidad(numero).equals(seleccion);
        return false;
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