package Programa1;

import java.util.ArrayList;
import Programa1.Estructura.*;

public class Grafica {

    private ArrayList<Vertice> vertices;
    private ArrayList<Arista> aristas;

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