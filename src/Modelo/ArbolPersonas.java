package Modelo;

import java.util.Comparator;

public class ArbolPersonas {
    public enum Criterio { DNI, NOMBRE }

    private NodoArbolPersona raiz;
    private Criterio criterio;

    public ArbolPersonas() {
        this.criterio = Criterio.DNI;
        this.raiz = null;
    }

    public void setCriterio(Criterio criterio) {
        if (this.criterio != criterio) {
            this.criterio = criterio;
            reconstruirArbol();
        }
    }

    public Criterio getCriterio() {
        return criterio;
    }

    private int comparar(Persona p1, Persona p2) {
        if (criterio == Criterio.DNI) {
            return Integer.compare(p1.getDni(), p2.getDni());
        } else {
            return p1.getNombre().compareToIgnoreCase(p2.getNombre());
        }
    }

    public void insertar(Persona persona) {
        raiz = insertarRec(raiz, persona);
    }

    private NodoArbolPersona insertarRec(NodoArbolPersona nodo, Persona persona) {
        if (nodo == null) return new NodoArbolPersona(persona);
        int cmp = comparar(persona, nodo.getPersona());
        if (cmp < 0) nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), persona));
        else if (cmp > 0) nodo.setDerecho(insertarRec(nodo.getDerecho(), persona));
        // Si es igual, no inserta duplicados
        return nodo;
    }

    public Persona buscar(Persona persona) {
        NodoArbolPersona nodo = buscarRec(raiz, persona);
        return nodo != null ? nodo.getPersona() : null;
    }

    private NodoArbolPersona buscarRec(NodoArbolPersona nodo, Persona persona) {
        if (nodo == null) return null;
        int cmp = comparar(persona, nodo.getPersona());
        if (cmp == 0) return nodo;
        else if (cmp < 0) return buscarRec(nodo.getIzquierdo(), persona);
        else return buscarRec(nodo.getDerecho(), persona);
    }

    public void eliminar(Persona persona) {
        raiz = eliminarRec(raiz, persona);
    }

    private NodoArbolPersona eliminarRec(NodoArbolPersona nodo, Persona persona) {
        if (nodo == null) return null;
        int cmp = comparar(persona, nodo.getPersona());
        if (cmp < 0) nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), persona));
        else if (cmp > 0) nodo.setDerecho(eliminarRec(nodo.getDerecho(), persona));
        else {
            if (nodo.getIzquierdo() == null) return nodo.getDerecho();
            if (nodo.getDerecho() == null) return nodo.getIzquierdo();
            NodoArbolPersona min = encontrarMin(nodo.getDerecho());
            nodo.setPersona(min.getPersona()); // Copia los datos del mínimo
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), min.getPersona())); // Elimina el mínimo de la rama derecha
        }
        return nodo;
    }

    private NodoArbolPersona encontrarMin(NodoArbolPersona nodo) {
        while (nodo.getIzquierdo() != null) nodo = nodo.getIzquierdo();
        return nodo;
    }

    // Cambia el método eliminarMin para evitar NPE
    private NodoArbolPersona eliminarMin(NodoArbolPersona nodo) {
        if (nodo == null) return null;
        if (nodo.getIzquierdo() == null) return nodo.getDerecho();
        nodo.setIzquierdo(eliminarMin(nodo.getIzquierdo()));
        return nodo;
    }

    public void mostrarInorden() {
        System.out.println("Recorrido inorden:");
        mostrarInordenRec(raiz);
        System.out.println();
    }

    private void mostrarInordenRec(NodoArbolPersona nodo) {
        if (nodo != null) {
            mostrarInordenRec(nodo.getIzquierdo());
            mostrarPersona(nodo.getPersona());
            mostrarInordenRec(nodo.getDerecho());
        }
    }

    public void mostrarPreorden() {
        System.out.println("Recorrido preorden:");
        mostrarPreordenRec(raiz);
        System.out.println();
    }

    private void mostrarPreordenRec(NodoArbolPersona nodo) {
        if (nodo != null) {
            mostrarPersona(nodo.getPersona());
            mostrarPreordenRec(nodo.getIzquierdo());
            mostrarPreordenRec(nodo.getDerecho());
        }
    }

    public void mostrarPostorden() {
        System.out.println("Recorrido postorden:");
        mostrarPostordenRec(raiz);
        System.out.println();
    }

    private void mostrarPostordenRec(NodoArbolPersona nodo) {
        if (nodo != null) {
            mostrarPostordenRec(nodo.getIzquierdo());
            mostrarPostordenRec(nodo.getDerecho());
            mostrarPersona(nodo.getPersona());
        }
    }

    private void mostrarPersona(Persona p) {
        System.out.println("DNI: " + p.getDni() + ", Nombre: " + p.getNombre() + " " + p.getApellido() + ", Edad: " + p.getEdad());
    }

    // Métodos para recorridos con StringBuilder
    public void mostrarInorden(StringBuilder sb) {
        mostrarInordenRec(raiz, sb);
    }
    private void mostrarInordenRec(NodoArbolPersona nodo, StringBuilder sb) {
        if (nodo != null) {
            mostrarInordenRec(nodo.getIzquierdo(), sb);
            sb.append("DNI: ").append(nodo.getPersona().getDni())
              .append(", Nombre: ").append(nodo.getPersona().getNombre())
              .append(" ").append(nodo.getPersona().getApellido())
              .append(", Edad: ").append(nodo.getPersona().getEdad())
              .append("\n");
            mostrarInordenRec(nodo.getDerecho(), sb);
        }
    }
    public void mostrarPreorden(StringBuilder sb) {
        mostrarPreordenRec(raiz, sb);
    }
    private void mostrarPreordenRec(NodoArbolPersona nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append("DNI: ").append(nodo.getPersona().getDni())
              .append(", Nombre: ").append(nodo.getPersona().getNombre())
              .append(" ").append(nodo.getPersona().getApellido())
              .append(", Edad: ").append(nodo.getPersona().getEdad())
              .append("\n");
            mostrarPreordenRec(nodo.getIzquierdo(), sb);
            mostrarPreordenRec(nodo.getDerecho(), sb);
        }
    }
    public void mostrarPostorden(StringBuilder sb) {
        mostrarPostordenRec(raiz, sb);
    }
    private void mostrarPostordenRec(NodoArbolPersona nodo, StringBuilder sb) {
        if (nodo != null) {
            mostrarPostordenRec(nodo.getIzquierdo(), sb);
            mostrarPostordenRec(nodo.getDerecho(), sb);
            sb.append("DNI: ").append(nodo.getPersona().getDni())
              .append(", Nombre: ").append(nodo.getPersona().getNombre())
              .append(" ").append(nodo.getPersona().getApellido())
              .append(", Edad: ").append(nodo.getPersona().getEdad())
              .append("\n");
        }
    }

    // Reconstruye el árbol con el nuevo criterio
    private void reconstruirArbol() {
        java.util.List<Persona> personas = new java.util.ArrayList<>();
        recolectarPersonas(raiz, personas);
        raiz = null;
        for (Persona p : personas) {
            insertar(p);
        }
    }

    private void recolectarPersonas(NodoArbolPersona nodo, java.util.List<Persona> personas) {
        if (nodo != null) {
            recolectarPersonas(nodo.getIzquierdo(), personas);
            personas.add(nodo.getPersona());
            recolectarPersonas(nodo.getDerecho(), personas);
        }
    }
}
