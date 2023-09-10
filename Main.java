package Programa1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Programa1.Estructura.*;

public class Main {

    public static boolean containtSpecialChar(String initialSet) {
        Pattern p = Pattern.compile("[^a-z0-9,]", Pattern.CASE_INSENSITIVE);// Se le pasa una expresion regular
                                                                            // con los characteres que si estan
                                                                            // permitidos
        Matcher m = p.matcher(initialSet);
        return m.find();

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        String conjuntoInicial;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            reader.mark(1);// Marca el inicio de mi input file
            if (reader.readLine() != null) {
                reader.reset();// Para que lea de nuevo desde la primera linea
                conjuntoInicial = reader.readLine();
                if (containtSpecialChar(conjuntoInicial) == true) {
                    System.out.println(
                            "El conjunto inicial tiene se define separado con comas, favor de corregir el .txt");
                    System.exit(0);
                }

                String[] verticesIniciales = conjuntoInicial.split("[,]", 0);// Se guardan los vertices en un arreglo de
                                                                             // Strings
                ArrayList<String> verticesTrans = new ArrayList<String>();
                Collections.addAll(verticesTrans, verticesIniciales);
                // System.out.println(verticesTrans);

                Grafica problema = new Grafica(verticesTrans);
                // Vertice uno = problema.addVertice(verticesTrans.get(0));
                // Vertice dos = problema.addVertice("2");

                problema.addArista(problema.getVertices().get(0), problema.getVertices().get(1));
                problema.print();

            } else {
                System.out.println(
                        "Favor de ingresar un archivo con una grafica definida por un conjunto de vertices en una sola linea y al terminar de definir sus vertices poner con saltos de linea duplas formadas por vertices las cuales representaran la conexion que hay entre si (aristas)");
                System.exit(0);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
