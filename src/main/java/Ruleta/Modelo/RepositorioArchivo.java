package Ruleta.Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private final String RUTA_ARCHIVO = "historial_ruleta.csv";

    @Override
    public void guardarResultado(Resultado resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            String linea = String.format("%d,%s,%b,%d,%s",
                    resultado.getNumeroObtenido(),
                    resultado.getColorObtenido(),
                    resultado.isEsVictoria(),
                    resultado.getMontoGanado(),
                    resultado.getTipoApuesta());

            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        List<Resultado> historial = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return historial;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 5) {
                    int numero = Integer.parseInt(datos[0]);
                    String color = datos[1];
                    boolean victoria = Boolean.parseBoolean(datos[2]);
                    int monto = Integer.parseInt(datos[3]);
                    String tipo = datos[4];

                    historial.add(new Resultado(numero, color, victoria, monto, tipo));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el historial: " + e.getMessage());
        }

        return historial;
    }
}