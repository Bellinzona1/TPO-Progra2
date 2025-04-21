import Modelo.Persona;
import Modelo.Vehiculo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {



        // PERSONAS

        Persona Mateo = new Persona("Mateo", "Bellinzona", 20);
        Persona TobiPelado = new Persona("Tobias", "Bonomo", 17); // 17 años de aporte
        Persona Benja = new Persona("Benja", "Cohen Bar-Natán Tercero", 30);


        // VEHICULOS
        Vehiculo vehiculo = new Vehiculo("Fiat", "Idea", "HDD407");
        Vehiculo vehiculo1 = new Vehiculo("BMW", "320i sedan Sportline", "ACM 001 PT");
        Vehiculo vehiculo2 = new Vehiculo("porsche", "911 GT", "123-45-678");


        // ACCIONES

        Mateo.AgregarVehiculo(vehiculo);
        TobiPelado.AgregarVehiculo(vehiculo1);
        Benja.AgregarVehiculo(vehiculo2);

            // benja se afana todos los autos
                Benja.AgregarVehiculo(vehiculo);
                Benja.AgregarVehiculo(vehiculo1);




        // PRINTS

        System.out.println("Autos Mateo: ---------------------------------------------");
        Mateo.getVehiculos();
        System.out.println();


        System.out.println("Autos Pelado: ---------------------------------------------");
        TobiPelado.getVehiculos();
        System.out.println();

        System.out.println("Autos Benja de Nazaret: ---------------------------------------------");
        Benja.getVehiculos();
        System.out.println();








    }
}