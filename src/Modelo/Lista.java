package Modelo;

public class Lista {

    private Nodo primero;

    public Lista() {
        primero = null;
    }

    public boolean esVacia(){
        return primero == null;
    }


    public void listaAgregar(Vehiculo vehiculo){
        if( esVacia() ) {
            primero = new Nodo(vehiculo);
        } else {
            Nodo actual = primero;
            while(actual.getSigNodo() != null) {
                actual = actual.getSigNodo();
            }
            Nodo nuevo = new Nodo(vehiculo);
            actual.setSigNodo(nuevo);
        }
    }

    public Nodo getPrimero(){
        if( esVacia() ) {
            return null;
        } else {
            return primero;
        }
    }

    public String buscarVehiculo(String patenteVehiculo) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getVehiculo().getPatente().equals(patenteVehiculo)) {
                return actual.getVehiculo().getPatente();
            }
            actual = actual.getSigNodo();
        }

        System.out.println("No se encontro el vehiculo");
        return null;
    }

    public void mostrarLista() {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null) {
            Vehiculo vehiculo = actual.getVehiculo();
            System.out.println("Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + " - " + vehiculo.getPatente());
            actual = actual.getSigNodo();
        }
    }



}
