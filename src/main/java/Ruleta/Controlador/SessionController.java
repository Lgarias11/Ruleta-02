package Ruleta.Controlador;

import Ruleta.Modelo.Usuario;
import Ruleta.Modelo.Ruleta;

public class SessionController {

    private Usuario usuarioActual;
    private final Ruleta motorRuleta;

    public SessionController() {
        this.usuarioActual = null;
        this.motorRuleta = new Ruleta();
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }


    public Ruleta getMotorRuleta() {
        return this.motorRuleta;
    }
}