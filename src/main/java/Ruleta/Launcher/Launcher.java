package Ruleta.Launcher;

import Ruleta.Controlador.SessionController;
import Ruleta.Vista.VentanaLogin;

public class Launcher {

    public static void main(String[] args) {

        SessionController session = new SessionController();

        VentanaLogin login = new VentanaLogin(session);

        login.mostrarVentana();
    }
}