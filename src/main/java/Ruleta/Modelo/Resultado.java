package Ruleta.Modelo;

public class Resultado {
    private int numeroObtenido;
    private String colorObtenido;
    private boolean esVictoria;
    private int montoGanado;

    private TipoApuesta tipoApuesta;

    public Resultado(int numeroObtenido, String colorObtenido, boolean esVictoria, int montoGanado, TipoApuesta tipoApuesta) {
        this.numeroObtenido = numeroObtenido;
        this.colorObtenido = colorObtenido;
        this.esVictoria = esVictoria;
        this.montoGanado = montoGanado;
        this.tipoApuesta = tipoApuesta;
    }

    public int getNumeroObtenido() {
        return numeroObtenido;
    }

    public String getColorObtenido() {
        return colorObtenido;
    }

    public boolean isEsVictoria() {
        return esVictoria;
    }

    public int getMontoGanado() {
        return montoGanado;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }
}