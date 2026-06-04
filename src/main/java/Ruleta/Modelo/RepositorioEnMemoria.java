package Ruleta.Modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private List<Resultado> historial;

    public RepositorioEnMemoria() {
        this.historial = new ArrayList<>();
    }

    @Override
    public void guardarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        return historial;
    }
}