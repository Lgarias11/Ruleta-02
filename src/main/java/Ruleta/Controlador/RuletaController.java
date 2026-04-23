package Ruleta.Controlador;

import Ruleta.Modelo.Resultado;
import Ruleta.Modelo.Ruleta;
import Ruleta.Modelo.TipoApuesta;
import Ruleta.Modelo.Usuario;

public class RuletaController {

    private final Ruleta motor;
    private final SessionController session;

    public RuletaController(Ruleta motor, SessionController session) {
        this.motor = motor;
        this.session = session;
    }

    public Resultado procesarJugada(TipoApuesta apuesta, int monto) {
        int numero = motor.generarNum();
        String color = motor.obtenerColor(numero);

        String tipo = (apuesta.name().equals("ROJO") || apuesta.name().equals("NEGRO")) ? "Color" : "Paridad";
        String seleccion = apuesta.name().charAt(0) + apuesta.name().substring(1).toLowerCase();

        boolean gano = motor.esGanador(numero, tipo, seleccion);

        motor.actualizarSaldo(monto, gano);

        int montoGanado = gano ? monto * 2 : 0;

        Resultado nuevoResultado = new Resultado(numero, color, gano, montoGanado, apuesta);

        Usuario usuarioActual = session.getUsuarioActual();
        if (usuarioActual != null) {
            usuarioActual.agregarResultado(nuevoResultado);
        }

        return nuevoResultado;
    }

    public int getSaldoActual() {
        return motor.getSaldo();
    }
    public void recargarSaldo(int monto) {
        motor.recargarSaldo(monto);
    }
}