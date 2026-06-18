package Ruleta.Controlador;

import Ruleta.Modelo.Usuario;
import Ruleta.Modelo.Ruleta;
import Ruleta.Modelo.IRepositorioResultados;
import Ruleta.Modelo.RepositorioArchivo; //hacer cambio aca

import java.util.HashMap;
import java.util.Map;

public class SessionController {

    private Usuario usuarioActual;
    private final Ruleta motorRuleta;
    private final Map<String, Usuario> usuariosRegistrados;

    public SessionController() {
        this.usuarioActual = null;
        this.usuariosRegistrados = new HashMap<>();
        IRepositorioResultados repositorio = new RepositorioArchivo();  //y aca
        this.motorRuleta = new Ruleta(repositorio, 1000);
    }

    public void registrarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getUsername() == null) {
            throw new IllegalArgumentException("Username inválido");
        }
        usuariosRegistrados.put(usuario.getUsername(), usuario);
    }

    public boolean iniciarSesion(String username, String password) {
        if (username == null) return false;

        Usuario user = usuariosRegistrados.get(username);
        if (user != null && user.validarCredenciales(username, password)) {
            this.usuarioActual = user;
            return true;
        }
        return false;
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