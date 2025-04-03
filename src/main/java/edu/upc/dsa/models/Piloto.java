package edu.upc.dsa.models; // Indica que esta clase está en el paquete "edu.upc.dsa.models"

import java.util.ArrayList; // Importa la clase ArrayList para poder usar listas
import java.util.List;      // Importa la interfaz List para trabajar con listas

/**
 * La clase Piloto representa a un piloto en la aplicación.
 * Cada piloto tiene un identificador, nombre, apellidos, y horas de vuelo acumuladas.
 * También tiene una lista de reservas asociadas.
 */
public class Piloto {
    // Atributo para almacenar las reservas que tiene este piloto.
    // Es una lista (como una libreta donde se apuntan todas las reservas).
    List<Reserva> reservas;

    // Atributo para almacenar el identificador único del piloto.
    String idPiloto;

    // Atributo para almacenar el nombre del piloto.
    String nombre;

    // Atributo para almacenar los apellidos del piloto.
    String apellidos;

    // Atributo para almacenar las horas de vuelo que ha acumulado el piloto.
    // Se usa para ordenar los pilotos de forma descendente.
    int horasVuelo;

    /**
     * Constructor de la clase Piloto.
     * Se utiliza para crear un nuevo piloto, recibiendo su identificador, nombre y apellidos.
     * Inicializa las horas de vuelo a 0 y crea una lista vacía de reservas.
     *
     * @param identificador El identificador único del piloto.
     * @param nombre El nombre del piloto.
     * @param apellidos Los apellidos del piloto.
     */
    public Piloto(String identificador, String nombre, String apellidos){
        this.idPiloto = identificador;     // Guarda el identificador en el atributo idPiloto.
        this.nombre = nombre;              // Guarda el nombre en el atributo nombre.
        this.apellidos = apellidos;        // Guarda los apellidos en el atributo apellidos.
        this.horasVuelo = 0;               // Inicializa las horas de vuelo a 0.
        this.reservas = new ArrayList<>();  // Crea una nueva lista vacía para las reservas.
    }

    // Getter para obtener el identificador del piloto.
    public String getIdPiloto() {
        return idPiloto;
    }

    // Setter para cambiar el identificador del piloto.
    public void setIdPiloto(String id) {
        this.idPiloto = id;
    }

    // Getter para obtener el nombre del piloto.
    public String getNombre(){
        return nombre;
    }

    // Setter para cambiar el nombre del piloto.
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    // Getter para obtener los apellidos del piloto.
    public String getApellidos(){
        return apellidos;
    }

    // Setter para cambiar los apellidos del piloto.
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    // Getter para obtener las horas de vuelo acumuladas.
    public int getHorasVuelo(){
        return horasVuelo;
    }

    // Setter para actualizar las horas de vuelo.
    public void setHorasVuelo(int horasVuelo){
        this.horasVuelo = horasVuelo;
    }

    // Getter para obtener la lista de reservas asociadas al piloto.
    public List<Reserva> getReservas(){
        return reservas;
    }

    // Setter para asignar una nueva lista de reservas al piloto.
    public void setReservas(List<Reserva> reservas){
        this.reservas = reservas;
    }

    // Método para añadir una nueva reserva a la lista de reservas del piloto.
    public void añadirReserva(Reserva reserva){
        this.reservas.add(reserva);
    }

    /**
     * Método estático que busca y devuelve un piloto en una lista, según su identificador.
     * Si no se encuentra, devuelve null.
     *
     * @param pilotos Lista de pilotos en la que buscar.
     * @param pilotoId El identificador del piloto que se busca.
     * @return El piloto encontrado o null si no existe.
     */
    public static Piloto buscarPilotoPorId(List<Piloto> pilotos, String pilotoId){
        for(Piloto piloto : pilotos){
            // Compara el identificador del piloto actual con el buscado.
            if(piloto.getIdPiloto().equals(pilotoId)) {
                return piloto; // Si coincide, devuelve ese piloto.
            }
        }
        return null; // Si no se encontró, devuelve null.
    }
}
