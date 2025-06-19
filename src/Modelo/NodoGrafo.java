package Modelo;

import java.util.*;

public class NodoGrafo<T> {
    private T dato;
    private List<NodoGrafo<T>> adyacentes;

    public NodoGrafo(T dato) {
        this.dato = dato;
        this.adyacentes = new ArrayList<>();
    }

    public T getDato() {
        return dato;
    }

    public List<NodoGrafo<T>> getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(NodoGrafo<T> nodo) {
        adyacentes.add(nodo);
    }
}

