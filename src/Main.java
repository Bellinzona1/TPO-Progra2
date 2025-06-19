import Modelo.Persona;
import Modelo.Vehiculo;
import Modelo.ArbolPersonas;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        // Si se corre ArbolPersonasGUI hay una interfaz gráfica para visualizar el árbol de personas.

        // PERSONAS
        Persona mateo = new Persona("Mateo", "Bellinzona", 20, 45628300);
        Persona tobi = new Persona("Tobias", "Bonomo", 17, 47797293);
        Persona benja = new Persona("Benja", "Cohen Bar-Natán Tercero", 30, 26654921);
        Persona lucas = new Persona("Lucas", "Ramirez", 28,872239372);

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

        // --- DEMOSTRACIÓN ÁRBOL DE PERSONAS ---
        ArbolPersonas arbol = new ArbolPersonas();
        Persona[] personas = {
                new Persona("Mateo", "Bellinzona", 20, 45628300),
                new Persona("Tobias", "Bonomo", 17, 47797293),
                new Persona("Benja", "Cohen", 30, 26654921),
                new Persona("Lucas", "Ramirez", 28, 87223937),
                new Persona("Ana", "Gomez", 25, 12345678),
                new Persona("Juan", "Perez", 40, 23456789),
                new Persona("Maria", "Lopez", 35, 34567890),
                new Persona("Pedro", "Martinez", 50, 45678901),
                new Persona("Sofia", "Diaz", 22, 56789012),
                new Persona("Carlos", "Sanchez", 29, 67890123),
                new Persona("Laura", "Fernandez", 33, 78901234),
                new Persona("Diego", "Torres", 27, 89012345),
                new Persona("Valentina", "Mendez", 31, 90123456),
                new Persona("Martin", "Suarez", 26, 11223344),
                new Persona("Florencia", "Rios", 24, 22334455)
        };
        // Insertar 15 personas
        for (Persona p : personas) {
            arbol.insertar(p);
        }
        System.out.println("\nÁrbol por DNI (inicial):");
        arbol.mostrarInorden();
        arbol.mostrarPreorden();
        arbol.mostrarPostorden();

        // Buscar por DNI
        Persona buscarDni = new Persona("", "", 0, 45628300); // Mateo
        System.out.println("\nBuscando persona con DNI 45628300:");
        Persona encontrada = arbol.buscar(buscarDni);
        if (encontrada != null) {
            System.out.println("Encontrada: " + encontrada.getNombre() + " " + encontrada.getApellido());
        } else {
            System.out.println("No encontrada");
        }

        // Eliminar por DNI
        System.out.println("\nEliminando persona con DNI 45628300 (Mateo)...");
        arbol.eliminar(buscarDni);
        arbol.mostrarInorden();



        // --- GRAFO DE PERSONAS ---
        System.out.println("\n--- GRAFO DE PERSONAS ---");
        Modelo.Grafo<Persona> grafo = new Modelo.Grafo<>(true); // true = dirigido
        // Agregar nodos
        Modelo.NodoGrafo<Persona> nMateo = grafo.agregarNodo(mateo);
        Modelo.NodoGrafo<Persona> nTobi = grafo.agregarNodo(tobi);
        Modelo.NodoGrafo<Persona> nBenja = grafo.agregarNodo(benja);
        Modelo.NodoGrafo<Persona> nLucas = grafo.agregarNodo(lucas);
        // Agregar aristas (conexiones)
        grafo.agregarArista(nMateo, nTobi);
        grafo.agregarArista(nTobi, nBenja);
        grafo.agregarArista(nBenja, nLucas);
        grafo.agregarArista(nLucas, nMateo); // ciclo
        // Recorrido DFS
        System.out.println("Recorrido DFS desde Mateo:");
        for (Persona p : grafo.recorridoDFS(nMateo)) {
            System.out.println(p.getNombre());
        }
        // Recorrido BFS
        System.out.println("Recorrido BFS desde Mateo:");
        for (Persona p : grafo.recorridoBFS(nMateo)) {
            System.out.println(p.getNombre());
        }
        // Matriz de adyacencia
        System.out.println("Matriz de adyacencia:");
        int[][] matriz = grafo.matrizAdyacencia();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
