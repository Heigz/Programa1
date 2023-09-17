package Programa1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import Programa1.Estructura.*;

public class Grafica {

    private ArrayList<Vertice> vertices;
    private ArrayList<Arista> aristas;

    public Grafica copiarGrafica() {
        Grafica copia = new Grafica(); // Crea una nueva instancia para la copia

        // Copia los vértices
        for (Vertice vertice : this.vertices) {
            copia.addVertice(vertice.getData());
        }

        // Copia las aristas
        for (Arista arista : this.aristas) {
            Vertice cola = copia.getVerticePorValor(arista.getCola().getData());
            Vertice cabeza = copia.getVerticePorValor(arista.getCabeza().getData());
            copia.addArista(cola, cabeza);
        }

        return copia;
    }

    public Vertice getRandomVertice(ArrayList<Vertice> vertices) {
        int min = 0;
        int max = vertices.size() - 1;

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return this.vertices.get(randomInt);
    }

    public boolean esVecino(Vertice vertice, ArrayList<Vertice> listaVertices) {
        for (int i = 0; i < listaVertices.size(); i++) {
            if (vertice.esAdyacente(listaVertices.get(i))) {
                return true;
            }
        }
        return false;
    }

    public Grafica conjuntoIndependiente(Grafica original, Grafica graficaInicial, Stack<Vertice> verticesEliminados) {
        Grafica nueva = new Grafica();
        nueva.setAristas(new ArrayList<>(graficaInicial.getAristas()));
        nueva.setVertices(new ArrayList<>(graficaInicial.getVertices()));

        if (nueva.vertices.size() == 1) {// Caso base

            while (!verticesEliminados.isEmpty()) {
                boolean bandera = false;
                System.out.println("Pila: " + Arrays.toString(verticesEliminados.toArray()));
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Vertice verticeTop = verticesEliminados.peek();

                System.out.println("Caso base: ");
                System.out.println(nueva.getVertices().toString());
                System.out.println(verticeTop);
                System.out.println(original.esVecino(verticeTop, nueva.getVertices()));
                System.out.println(verticeTop + " es vecino de los vertices " + nueva.getVertices().toString() + "? "
                        + (original.esVecino(verticeTop, nueva.getVertices())));

                if (original.esVecino(verticeTop, nueva.getVertices()) == true) {
                    bandera = true;// si hay alguien vecino con los que ya estan en la nueva grafica(la
                                   // independiente)
                    System.out.println(" Entonces no se agregan a la nueva grafica");
                    verticesEliminados.pop();// saco ese elemento sin agregarlo

                }

                if (bandera == false) {

                    nueva.addVertice(verticeTop);
                    verticesEliminados.pop();
                }
                System.out.println("La nuevaNueva " + nueva.getVertices());
            }
            return nueva;
        } else

        {// Paso inductivo
            Vertice verticeARemover = getRandomVertice(nueva.getVertices());
            System.out.println("Vertice a remover: " + verticeARemover.toString());
            // Tengo que quitar tambien la vecindad de vertice a remover
            ArrayList<Vertice> vecindadARemover = new ArrayList<>(verticeARemover.getVecindad());
            verticesEliminados.push(verticeARemover);
            nueva.removeVertice(verticeARemover);
            for (int i = 0; i < vecindadARemover.size(); i++) {

                nueva.removeVertice(vecindadARemover.get(i));
                if (nueva.vertices.size() == 0) {
                    nueva.addVertice(vecindadARemover.get(i).getData());
                    verticesEliminados.push(verticeARemover);
                }
            }

            System.out.println("Grafica despues de eliminar vertices: " + nueva.getVertices().toString());
        }
        return nueva.conjuntoIndependiente(original, nueva, verticesEliminados);
    }

    public Grafica() {
        this.vertices = new ArrayList<Vertice>();
        this.aristas = new ArrayList<Arista>();

    }

    public Grafica(ArrayList<String> vertices) {
        this.vertices = new ArrayList<Vertice>();
        this.aristas = new ArrayList<Arista>();

        for (int i = 0; i < vertices.size(); i++) {
            this.addVertice(vertices.get(i));
        }
    }

    public Vertice addVertice(String data) {
        Vertice newVertice = new Vertice(data);
        this.vertices.add(newVertice);
        return newVertice;
    }

    public Vertice addVertice(Vertice vertice) {
        this.vertices.add(vertice);
        return vertice;
    }

    public void addArista(Vertice cola, Vertice cabeza) {
        this.aristas = new ArrayList<Arista>();
        this.aristas.add(new Arista(cola, cabeza));
        cola.addArista(cabeza);
    }

    public void removeArista(Vertice cola, Vertice cabeza) {
        cola.removeArista(cabeza);
    }

    public void removeVertice(Vertice vertice) {
        this.vertices.remove(vertice);
    }

    public Vertice getVerticePorValor(String value) {
        for (Vertice v : this.vertices) {
            if (v.getData().equals(value)) {
                return v;
            }
        }
        return null;
    }

    public void print() {
        for (Vertice v : this.vertices) {
            v.print();
        }
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Arista> getAristas() {
        return this.aristas;
    }

    public void setAristas(ArrayList<Arista> aristas) {
        this.aristas = aristas;
    }

}