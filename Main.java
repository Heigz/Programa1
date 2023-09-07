package Programa1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        String conjuntoInicial;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            reader.mark(1);// Marca el inicio de mi input file
            if (reader.readLine() != null) {
                reader.reset();// Para que lea de nuevo desde la primera linea
                conjuntoInicial = reader.readLine();
                String[] verticesIniciales = conjuntoInicial.split("[,]", 0);// Se guardan los vertices en un arreglo de
                                                                             // Strings
                char[] verticesInicialesOptimizado = new char[verticesIniciales.length];// Lo mismo pero de chars xd
                for (int i = 0; i < verticesIniciales.length; i++) {
                    verticesInicialesOptimizado[i] = verticesIniciales[i].charAt(0);
                }

                for (char si : verticesInicialesOptimizado) {
                    System.out.println(si);
                }
            } else {
                System.out.println(
                        "Favor de ingresar un archivo con una grafica definida por un conjunto de vertices en una sola linea y al terminar de definir sus vertices poner con saltos de linea duplas formadas por vertices las cuales representaran la conexion que hay entre si (aristas)");
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // try {// Sera para escribir en un nuevo .txt el cual contendra el Conjunto
        // // Independiente
        // BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        // writer.close();
        // } catch (IOException e) {

        // e.printStackTrace();
        // }
    }
}
