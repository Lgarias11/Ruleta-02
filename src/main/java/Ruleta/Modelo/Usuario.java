package Ruleta.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;

    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String user, String password) {
        if (this.username == null || user == null) return false;
        return this.username.equals(user) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }


    public void agregarResultado(Resultado r) {
        historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}