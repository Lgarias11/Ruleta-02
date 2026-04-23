package Ruleta.Controlador;

import Ruleta.Modelo.Usuario;

public class SessionController {

    private Usuario usuarioActual;

    public SessionController() {
        this.usuarioActual = null;
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
}