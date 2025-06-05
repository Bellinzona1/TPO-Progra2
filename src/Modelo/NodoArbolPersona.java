package Modelo;

public class NodoArbolPersona {
    private Persona persona;
    private NodoArbolPersona izquierdo;
    private NodoArbolPersona derecho;

    public NodoArbolPersona(Persona persona) {
        this.persona = persona;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public NodoArbolPersona getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbolPersona izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbolPersona getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbolPersona derecho) {
        this.derecho = derecho;
    }
}
