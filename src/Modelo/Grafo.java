package Modelo;

import java.util.*;

public class Grafo<T> {
    private List<NodoGrafo<T>> nodos;
    private boolean dirigido;

    public Grafo(boolean dirigido) {
        this.nodos = new ArrayList<>();
        this.dirigido = dirigido;
    }

    public NodoGrafo<T> agregarNodo(T dato) {
        NodoGrafo<T> nodo = new NodoGrafo<>(dato);
        nodos.add(nodo);
        return nodo;
    }

    public void agregarArista(NodoGrafo<T> origen, NodoGrafo<T> destino) {
        origen.agregarAdyacente(destino);
        if (!dirigido) {
            destino.agregarAdyacente(origen);
        }
    }

    public List<T> recorridoDFS(NodoGrafo<T> inicio) {
        List<T> resultado = new ArrayList<>();
        Set<NodoGrafo<T>> visitados = new HashSet<>();
        dfs(inicio, visitados, resultado);
        return resultado;
    }

    private void dfs(NodoGrafo<T> nodo, Set<NodoGrafo<T>> visitados, List<T> resultado) {
        if (nodo == null || visitados.contains(nodo)) return;
        visitados.add(nodo);
        resultado.add(nodo.getDato());
        for (NodoGrafo<T> ady : nodo.getAdyacentes()) {
            dfs(ady, visitados, resultado);
        }
    }

    public List<T> recorridoBFS(NodoGrafo<T> inicio) {
        List<T> resultado = new ArrayList<>();
        Set<NodoGrafo<T>> visitados = new HashSet<>();
        Queue<NodoGrafo<T>> cola = new LinkedList<>();
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            NodoGrafo<T> actual = cola.poll();
            resultado.add(actual.getDato());
            for (NodoGrafo<T> ady : actual.getAdyacentes()) {
                if (!visitados.contains(ady)) {
                    cola.add(ady);
                    visitados.add(ady);
                }
            }
        }
        return resultado;
    }

    public int[][] matrizAdyacencia() {
        int n = nodos.size();
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            NodoGrafo<T> nodo = nodos.get(i);
            for (NodoGrafo<T> ady : nodo.getAdyacentes()) {
                int j = nodos.indexOf(ady);
                if (j != -1) {
                    matriz[i][j] = 1;
                }
            }
        }
        return matriz;
    }

    public List<NodoGrafo<T>> getNodos() {
        return nodos;
    }

    public boolean esDirigido() {
        return dirigido;
    }
}

