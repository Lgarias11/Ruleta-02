package Ruleta.Controlador;

import Ruleta.Modelo.Resultado;
import Ruleta.Modelo.Usuario;
import java.util.List;

public class ResultadoController {

    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorialUsuario() {
        Usuario actual = session.getUsuarioActual();
        if (actual != null) {
            return actual.getHistorial();
        }
        return null;
    }
}