package Ruleta.Controlador;

import Ruleta.Modelo.Usuario;
import Ruleta.Modelo.Ruleta;
import Ruleta.Modelo.IRepositorioResultados;
import Ruleta.Modelo.RepositorioArchivo; //hacer cambio aca

public class SessionController {

    private Usuario usuarioActual;
    private final Ruleta motorRuleta;

    public SessionController() {
        this.usuarioActual = null;

        IRepositorioResultados repositorio = new RepositorioArchivo();  //y aca

        this.motorRuleta = new Ruleta(repositorio);
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