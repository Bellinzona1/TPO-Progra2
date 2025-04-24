package Modelo;

public class Nodo {
    private Vehiculo vehiculo;
    private Nodo sigNodo;
    private Nodo antNodo; // Nuevo atributo

    public Nodo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.sigNodo = null;
        this.antNodo = null;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Nodo getSigNodo() {
        return sigNodo;
    }

    public void setSigNodo(Nodo sigNodo) {
        this.sigNodo = sigNodo;
    }

    public Nodo getAntNodo() {
        return antNodo;
    }

    public void setAntNodo(Nodo antNodo) {
        this.antNodo = antNodo;
    }
}
