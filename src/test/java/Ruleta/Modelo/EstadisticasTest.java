package Ruleta.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadisticasTest {

    @Test
    void estadisticas_HistorialMixto_CalculaCorrectamente() {

        IRepositorioResultados repoDummy = new RepositorioEnMemoria();
        Estadisticas est = new Estadisticas(repoDummy);

        est.registrarJugada(true, "Rojo");
        est.registrarJugada(true, "Rojo");
        est.registrarJugada(false, "Par");
        est.registrarJugada(true, "Negro");
        est.registrarJugada(false, null);    

        assertEquals(4, est.getTotalJugadas(), "Debe registrar 4 jugadas válidas");

        assertEquals(3, est.getVictorias(), "Deben registrarse 3 victorias");

        assertEquals(75.0, est.getPorcentajeVictorias(), 0.01, "El porcentaje debe ser 75.0%");

        assertEquals(2, est.getRachaMaxima(), "La racha máxima debe ser 2");

        assertEquals("Rojo", est.getTipoMasJugado(), "El tipo más jugado debe ser Rojo");
    }
}
