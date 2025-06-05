package Modelo;

public class Persona {

    private String nombre;
    private String apellido;
    private int edad;
    private Lista vehiculosLista = new Lista();
    private int dni;



    public Persona(String nombre, String apellido, int edad, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;

    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void getVehiculos() {
        vehiculosLista.mostrarLista();
    }

    public void AgregarVehiculo(Vehiculo vehiculo) {
        vehiculosLista.listaAgregar(vehiculo);
    }

    public void BuscarVehiculo(String Patente) {
        vehiculosLista.buscarVehiculo(Patente);
    }

    public void EliminarVehiculo(String patente) {
        vehiculosLista.eliminarVehiculo(patente);
    }

    public void MostrarVehiculosInverso() {
        vehiculosLista.mostrarListaInversa();
    }

    public void VaciarVehiculos() {
        vehiculosLista.vaciarLista();
    }

    public Lista getVehiculosLista() {
        return vehiculosLista;
    }
}
