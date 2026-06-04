package Ruleta.Controlador;

import Ruleta.Modelo.ApuestaTipo.ApuestaBase; // Cambiamos TipoApuesta por ApuestaBase
import Ruleta.Modelo.Resultado;
import Ruleta.Modelo.Ruleta;
import Ruleta.Modelo.Usuario;

public class RuletaController {

    private final Ruleta motor;
    private final SessionController session;

    public RuletaController(Ruleta motor, SessionController session) {
        this.motor = motor;
        this.session = session;
    }

    public Resultado procesarJugada(ApuestaBase apuesta) {
        int numero = motor.generarNum();
        String color = motor.obtenerColor(numero);

        boolean gano = apuesta.acierta(numero, color);

        motor.actualizarSaldo(apuesta.getMonto(), gano);

        motor.getEstadisticas().registrarJugada(gano, apuesta.getEtiqueta());

        int montoGanado = gano ? apuesta.getMonto() * 2 : 0;

        Resultado nuevoResultado = new Resultado(numero, color, gano, montoGanado, apuesta.getEtiqueta());

        motor.getEstadisticas().guardarEnHistorial(nuevoResultado);

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