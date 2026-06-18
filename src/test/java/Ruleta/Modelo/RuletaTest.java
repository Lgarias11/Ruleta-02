package Ruleta.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Ruleta.Modelo.ApuestaTipo.ApuestaRojo;

class RuletaTest {

    @Test
    void constructor_RechazaSaldoInicialNegativo() {
        IRepositorioResultados repoDummy = new RepositorioEnMemoria();
        int saldoInvalido = -500;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Ruleta(repoDummy, saldoInvalido)
        );

        assertEquals("Saldo inicial inválido", exception.getMessage());
    }

    @Test
    void recargarSaldo_MontoValido_IncrementaSaldo() {
        IRepositorioResultados repoDummy = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(repoDummy, 1000);
        int montoRecarga = 500;

        ruleta.recargarSaldo(montoRecarga);

        assertEquals(1500, ruleta.getSaldo(), "El saldo debería ser 1500 tras recargar 500");
    }

    @Test
    void validarApuesta_ApuestaNula_LanzaExcepcion() {
        IRepositorioResultados repoDummy = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(repoDummy, 1000);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.validarApuesta(null)
        );

        assertEquals("Apuesta requerida", exception.getMessage());
    }

    @Test
    void validarApuesta_MontoMayorAlSaldo_LanzaExcepcion() {
        IRepositorioResultados repoDummy = new RepositorioEnMemoria();
        Ruleta ruleta = new Ruleta(repoDummy, 1000);

        ApuestaRojo apuestaCara = new ApuestaRojo(5000);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.validarApuesta(apuestaCara)
        );

        assertEquals("Saldo insuficiente", exception.getMessage());
    }

}