package Programa1;

import java.util.ArrayList;
import java.util.Stack;

import Programa1.Estructura.*;

public class Grafica {

    private ArrayList<Vertice> vertices;
    private ArrayList<Arista> aristas;
    Stack<Vertice> stack = new Stack<Vertice>();
    Grafica sustituta;// Se inicializa una grafica sustituta con los mismos vertices que la grafica
                      // inicial u original

    public Vertice getRandomVertice(ArrayList<Vertice> vertices) {
        int min = 0;
        int max = vertices.size() - 1;

        int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return this.vertices.get(randomInt);
    }

    public boolean esVecino(Vertice uno, Vertice dos) {// Return false si no existe una arista entre 2 vertices sin
                                                       // importar su direccion
        ArrayList<Vertice> vecindad = uno.getVecindad();
        ArrayList<Vertice> vecindad2 = dos.getVecindad();
        for (int i = 0; i < vecindad.size(); i++) {
            if (dos.equals(vecindad.get(i))) {
                return true;
            }
        }

        for (int i = 0; i < vecindad2.size(); i++) {
            if (uno.equals(vecindad2.get(i))) {
                return true;
            }
        }
        return false;
    }

    public Grafica conjuntoIndependiente(Grafica graficaInicial) {
        sustituta = graficaInicial;
        Grafica nueva = new Grafica();
        nueva.setVertices(this.getVertices());
        nueva.setAristas(this.getAristas());
        ArrayList<Vertice> verticesSustitutos = sustituta.getVertices();
        System.out.println("Vertices sustitutos es igual a " + verticesSustitutos.toString());

        // Caso base:
        if (sustituta.getVertices().size() == 1) {
            do {
                Vertice verticeTop = stack.pop();// saco al ultimo elemento de la lista para ver si existe una arista
                                                 // entre
                                                 // el si existe entonces no se agrega esa a la sustituta. Si no existe
                                                 // arista significa que lo puedo agregar

                verticeTop.getAristas();
                System.out.println("aveeeeeeer " + verticeTop.getAristas().toString());
                if (esVecino(verticeTop, nueva.getVertices().get(0)) == false
                        || esVecino(nueva.getVertices().get(0), verticeTop) == false) {
                    System.err.println("No existe la arista: " + verticeTop + " -- " + sustituta.getVertices().get(0));
                    sustituta.addVertice(verticeTop.getData());// No hay adyacencia entonces se agrega al conjunto
                                                               // independiente

                } else {
                    if (!stack.isEmpty())
                        stack.pop();
                }
            } while (!stack.isEmpty());

        } else {
            Vertice verticeARemover = getRandomVertice(verticesSustitutos);
            System.out.println("Vertice a remover: " + verticeARemover.toString());
            // Tengo que quitar tambien la vecindad de vertice a remover
            ArrayList<Vertice> vecindadARemover = verticeARemover.getVecindad();
            stack.push(verticeARemover);
            sustituta.removeVertice(verticeARemover);
            for (int i = 0; i < vecindadARemover.size(); i++) {

                sustituta.removeVertice(vecindadARemover.get(i));
                if (sustituta.vertices.size() == 0) {
                    sustituta.addVertice(vecindadARemover.get(0).getData());
                    conjuntoIndependiente(sustituta);
                }
            }

            conjuntoIndependiente(sustituta);

        }

        return sustituta;
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