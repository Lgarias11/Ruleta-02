package Ruleta.Controlador;

import Ruleta.Modelo.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SessionControllerTest {

    @Test
    void iniciarSesion_UsuarioNoRegistrado_DeniegaAcceso() {
        SessionController session = new SessionController();

        boolean loginExitoso = session.iniciarSesion("manue", "mondongo");

        assertFalse(loginExitoso, "El sistema debe rechazar el login de un usuario no registrado");
        assertNull(session.getUsuarioActual(), "No debe haber sesión iniciada");
    }

    @Test
    void registrarUsuario_UsernameNulo_LanzaExcepcion() {
        SessionController session = new SessionController();
        Usuario usuarioCorrupto = new Usuario(null, "claven't", "Usuarion't");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.registrarUsuario(usuarioCorrupto)
        );

        assertEquals("Username inválido", exception.getMessage());
    }
}