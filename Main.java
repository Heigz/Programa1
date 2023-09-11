package Programa1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Programa1.Estructura.*;

public class Main {

    public static boolean containtSpecialCharAristas(String initialSet) {
        Pattern p = Pattern.compile("[^a-z0-9.,]", Pattern.CASE_INSENSITIVE);// Se le pasa una expresion regular
                                                                             // con los characteres que si estan
                                                                             // permitidos
        Matcher m = p.matcher(initialSet);
        return m.find();

    }

    public static boolean containtSpecialCharVertices(String initialSet) {
        Pattern p = Pattern.compile("[^a-z0-9,]", Pattern.CASE_INSENSITIVE);// Se le pasa una expresion regular
                                                                            // con los characteres que si estan
                                                                            // permitidos
        Matcher m = p.matcher(initialSet);
        return m.find();

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        String conjuntoInicial;
        String aristasIniciales;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            reader.mark(1);// Marca el inicio de mi input file
            if (reader.readLine() != null) {
                reader.reset();// Para que lea de nuevo desde la primera linea
                conjuntoInicial = reader.readLine();
                aristasIniciales = reader.lines().collect(Collectors.joining(","));

                if (containtSpecialCharVertices(conjuntoInicial) == true) {
                    System.out.println(
                            "El conjunto inicial tiene se define separado co n comas, favor de corregir el .txt");
                    System.exit(0);
                }

                if (containtSpecialCharAristas(aristasIniciales) == true) {
                    System.out.println(
                            "El conjunto de aristas debe ir escrito en duplas separadas por coma y al finalizar un salto de linea, favor de corregir el archivo");
                    System.exit(0);
                }

                String[] verticesIniciales = conjuntoInicial.split("[,]", 0);// Se guardan los vertices en un arreglo de
                                                                             // Strings
                ArrayList<String> verticesTrans = new ArrayList<String>();
                Collections.addAll(verticesTrans, verticesIniciales);

                String[] aristasMedias = aristasIniciales.split("[,]", 0);// {"1,2"},{"5,6"}, ..., {8,4}
                String[] aristasColas = new String[aristasMedias.length];// Se agregan nada mas las colas de las aristas
                String[] aristasCabezas = new String[aristasMedias.length];// Se agregan nada mas las cabezas de las
                                                                           // aristas

                for (int i = 0; i < aristasMedias.length; i++) {
                    if (i % 2 == 0) {
                        aristasColas[i] = aristasMedias[i];
                    } else {
                        aristasCabezas[i] = aristasMedias[i];
                    }
                }

                // Para remover los nulls de los Arrays:

                List<String> colas = new ArrayList<String>();
                List<String> cabezas = new ArrayList<String>();
                for (String etiqueta : aristasColas) {
                    if (etiqueta != null) {
                        colas.add(etiqueta);
                    }
                }
                for (String etiqueta : aristasCabezas) {
                    if (etiqueta != null) {
                        cabezas.add(etiqueta);
                    }
                }

                Grafica problema = new Grafica(verticesTrans);// Se le pasan los vertices a la grafica

                for (int i = 0; i < colas.size(); i++) {// Se agregan todas las aristas a la grafica, haciendo que ya
                                                        // sea la grafica que se pasa en el archivo
                    problema.addArista(problema.getVerticePorValor(colas.get(i)),
                            problema.getVerticePorValor(cabezas.get(i)));
                }
                problema.print();
            } else {
                System.out.println(
                        "Favor de ingresar un archivo con una grafica definida por un conjunto de vertices en una sola linea y al terminar de definir sus vertices poner con saltos de linea duplas formadas por vertices las cuales representaran la conexion que hay entre si (aristas)");
                System.exit(0);
            }

            reader.close();
        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
