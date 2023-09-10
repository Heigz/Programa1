package Programa1.Estructura;

import java.util.ArrayList;

public class Vertice {

    private String data;
    private ArrayList<Arista> aristas;

    public Vertice(String nombre) {
        this.data = nombre;
        this.aristas = new ArrayList<Arista>();
    }

    public void addArista(Vertice cabeza) {
        this.aristas.add(new Arista(this, cabeza));
    }

    public void removeArista(Vertice cabeza) {
        this.aristas.removeIf(arista -> arista.getCabeza().equals(cabeza));// quita la arista. Primero busca la arista
                                                                           // que tiene la cabeza igual a la cabeza que
                                                                           // se le paso (endVertex)
    }

    public String getData() {
        return this.data;
    }

    public ArrayList<Arista> getAristas() {
        return this.aristas;
    }

    public void setData(String dataV) {
        this.data = dataV;
    }

    public void setAristas(ArrayList<Arista> aristas) {
        this.aristas = aristas;
    }

    public void print() {
        String forma = "";

        for (int i = 0; i < this.aristas.size(); i++) {
            if (i == 0) {
                forma += this.aristas.get(i).getCola().data + " --> ";
            }

            forma += this.aristas.get(i).getCabeza().data;

            if (i != this.aristas.size() - 1) {
                forma += ", ";
            }
        }
        System.out.println(forma);
    }

}
