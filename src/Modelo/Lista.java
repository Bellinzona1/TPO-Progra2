package Modelo;

public class Lista {
    private Nodo primero;

    public Lista() {
        primero = null;
    }

    public boolean esVacia() {
        return primero == null;
    }

    public void listaAgregar(Vehiculo vehiculo) {
        Nodo nuevo = new Nodo(vehiculo);

        if (esVacia()) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.getSigNodo() != null) {
                actual = actual.getSigNodo();
            }
            actual.setSigNodo(nuevo);
            nuevo.setAntNodo(actual);
        }
    }

    public Nodo getPrimero() {
        return primero;
    }

    public String buscarVehiculo(String patenteVehiculo) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getVehiculo().getPatente().equals(patenteVehiculo)) {
                System.out.println("Vehiculo Encontrado" + " " + actual.getVehiculo().getPatente());
                return actual.getVehiculo().getPatente();
            }
            actual = actual.getSigNodo();
        }
        System.out.println("No se encontró el vehículo.");
        return null;
    }

    public void mostrarLista() {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual != null) {
            Vehiculo v = actual.getVehiculo();
            System.out.println("Vehículo: " + v.getMarca() + " " + v.getModelo() + " - " + v.getPatente());
            actual = actual.getSigNodo();
        }
    }

    public boolean eliminarVehiculo(String patente) {
        Nodo actual = primero;

        while (actual != null) {
            if (actual.getVehiculo().getPatente().equals(patente)) {
                Nodo anterior = actual.getAntNodo();
                Nodo siguiente = actual.getSigNodo();

                if (anterior != null) {
                    anterior.setSigNodo(siguiente);
                } else {
                    primero = siguiente; // Si eliminás el primero
                }

                if (siguiente != null) {
                    siguiente.setAntNodo(anterior);
                }

                System.out.println("Vehículo eliminado: " + patente);
                return true;
            }

            actual = actual.getSigNodo();
        }

        System.out.println("No se encontró el vehículo con la patente: " + patente);
        return false;
    }

    public String buscarDesdeFinal(String patente) {
        if (esVacia()) return null;

        Nodo actual = primero;
        while (actual.getSigNodo() != null) {
            actual = actual.getSigNodo();
        }

        while (actual != null) {
            if (actual.getVehiculo().getPatente().equals(patente)) {
                return actual.getVehiculo().getPatente();
            }
            actual = actual.getAntNodo();
        }

        return null;
    }

    public void mostrarListaInversa() {
        if (esVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = primero;
        while (actual.getSigNodo() != null) {
            actual = actual.getSigNodo();
        }

        while (actual != null) {
            Vehiculo v = actual.getVehiculo();
            System.out.println("Vehículo: " + v.getMarca() + " " + v.getModelo() + " - " + v.getPatente());
            actual = actual.getAntNodo();
        }
    }

    public void vaciarLista() {
        primero = null;
        System.out.println("Lista vaciada correctamente.");
    }

}
