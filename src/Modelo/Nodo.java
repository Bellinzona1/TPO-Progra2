package Modelo;

public class Nodo {

    private Vehiculo Vehiculo;
    private Nodo SigNodo;


    public Nodo(Vehiculo Vehiculo) {
        this.Vehiculo = Vehiculo;
        this.SigNodo = null;
    }

    public Vehiculo getVehiculo() {
        return Vehiculo;
    }

    public Nodo getSigNodo() {
        return SigNodo;
    }

    public void setSigNodo(Nodo sigNodo) {
        SigNodo = sigNodo;
    }



}
