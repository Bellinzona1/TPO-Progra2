import Modelo.Persona;
import Modelo.Vehiculo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // PERSONAS
        Persona mateo = new Persona("Mateo", "Bellinzona", 20);
        Persona tobi = new Persona("Tobias", "Bonomo", 17);
        Persona benja = new Persona("Benja", "Cohen Bar-Natán Tercero", 30);
        Persona lucas = new Persona("Lucas", "Ramirez", 28);

        // VEHICULOS
        Vehiculo fiat = new Vehiculo("Fiat", "Idea", "HDD407");
        Vehiculo bmw = new Vehiculo("BMW", "320i sedan Sportline", "LLD123");
        Vehiculo porsche = new Vehiculo("Porsche", "911 GT", "123-45-678");
        Vehiculo audi = new Vehiculo("Audi", "A3", "AUD321");
        Vehiculo vw = new Vehiculo("Volkswagen", "Gol Trend", "VWT999");

        // ASIGNACIONES
        mateo.AgregarVehiculo(fiat);
        tobi.AgregarVehiculo(bmw);
        benja.AgregarVehiculo(porsche);
        benja.AgregarVehiculo(audi);
        benja.AgregarVehiculo(fiat);
        lucas.AgregarVehiculo(vw);
        lucas.AgregarVehiculo(bmw); // vehículo repetido intencionalmente

        // MOSTRAR LISTAS
        System.out.println("Autos de Mateo:");
        mateo.getVehiculos();
        System.out.println();

        System.out.println("Autos de Tobi:");
        tobi.getVehiculos();
        System.out.println("Buscando vehículo 'LLD123'...");
        tobi.BuscarVehiculo("LLD123");
        System.out.println();

        System.out.println("Autos de Benja:");
        benja.getVehiculos();
        System.out.println("Mostrando en orden inverso:");
        benja.MostrarVehiculosInverso();
        System.out.println("Eliminando vehículo con patente 'HDD407'...");
        benja.EliminarVehiculo("HDD407");
        System.out.println("Lista luego de eliminar:");
        benja.getVehiculos();
        System.out.println();

        System.out.println("Autos de Lucas:");
        lucas.getVehiculos();
        System.out.println("Vaciando lista de vehículos de Lucas...");
        lucas.VaciarVehiculos();
        lucas.getVehiculos();

        // Buscando desde final
        System.out.println("Benja busca '123-45-678' desde el final:");
        String encontrado = benja.getVehiculosLista().buscarDesdeFinal("123-45-678");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("No se encontró el vehículo.");
        }

    }
}
