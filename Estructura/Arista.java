package Programa1.Estructura;

public class Arista {
    private Vertice cola;
    private Vertice cabeza;

    public Arista(Vertice colaV, Vertice cabezaV) {
        this.cola = colaV;
        this.cabeza = cabezaV;
    }

    public Vertice getCola() {
        return cola;
    }

    public void setCola(Vertice cola) {
        this.cola = cola;
    }

    public Vertice getCabeza() {
        return cabeza;
    }

    public void setCabeza(Vertice cabeza) {
        this.cabeza = cabeza;
    }

    @Override
    public String toString() {
        return this.cola.getData() + "-->" + this.cabeza.getData();
    }

}
