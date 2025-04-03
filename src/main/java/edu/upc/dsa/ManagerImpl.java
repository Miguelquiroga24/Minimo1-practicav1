package edu.upc.dsa;

import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ManagerImpl es la fachada que implementa la interfaz Manager.
 *
 * Corresponde a:
 *  - "Especificación del componente que implementará las operaciones descritas"
 *  - "Implementación de una Fachada (patrón de diseño) que implemente el interfaz"
 *
 * Además, se han cumplido los siguientes requisitos del enunciado:
 *  2.1 Elección de las estructuras de datos (listas y cola)
 *  2.3 La fachada se implementa como un Singleton.
 *  2.4 Todos los métodos incluyen trazas de Log4J (INFO, ERROR) que muestran los parámetros
 *      al inicio y el resultado al final.
 */
public class ManagerImpl implements Manager {

    // -----------------------------------------------------------
    // 1) PATRÓN SINGLETON (Requisito 2.3)
    // -----------------------------------------------------------
    // Declaramos la única instancia de ManagerImpl (declarada con su tipo concreto)
    private static ManagerImpl instance;

    // -----------------------------------------------------------
    // 2) LOGGER (Requisito 2.4: Trazas con Log4J)
    // -----------------------------------------------------------
    private final static Logger logger = Logger.getLogger(ManagerImpl.class);

    // -----------------------------------------------------------
    // 3) ESTRUCTURAS DE DATOS (Requisito 2.1)
    // -----------------------------------------------------------
    // Listas para almacenar drones, pilotos y reservas
    private List<Dron> drones;
    private List<Piloto> pilotos;
    private List<Reserva> reservas;
    // Cola para simular el almacén de mantenimiento (FIFO)
    private Queue<Dron> colaMantenimiento;

    // -----------------------------------------------------------
    // 4) CONSTRUCTOR PRIVADO (Singleton: Requisito 2.3)
    // -----------------------------------------------------------
    private ManagerImpl() {
        // Inicializamos las estructuras de datos
        this.drones = new ArrayList<>();
        this.pilotos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.colaMantenimiento = new LinkedList<>();
    }

    /**
     * Método estático para obtener la única instancia de ManagerImpl.
     * Cumple con el patrón Singleton.
     */
    public static ManagerImpl getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    // -----------------------------------------------------------
    // 5) IMPLEMENTACIÓN DE LOS MÉTODOS DE LA INTERFAZ (Operaciones del enunciado)
    // -----------------------------------------------------------

    /**
     * Devuelve el tamaño de la lista de drones operativos.
     * (Podrías ampliar para incluir pilotos y reservas si se desea.)
     */
    @Override
    public int size() {
        int total = this.drones.size();
        logger.info("size() => drones: " + total);
        return total;
    }

    /**
     * Añade un nuevo Dron.
     * Requisito: "Añadir un nuevo Dron" (con identificador, nombre, fabricante y modelo)
     */
    @Override
    public Dron añadirDron(String identificador, String nombre, String fabricante, String modelo) {
        logger.info(String.format("Inicio añadirDron(id=%s, nombre=%s, fab=%s, mod=%s)",
                identificador, nombre, fabricante, modelo));
        Dron newDron = new Dron(identificador, nombre, fabricante, modelo);
        this.drones.add(newDron);
        logger.info("Fin añadirDron: dron añadido -> " + newDron);
        return newDron;
    }

    /**
     * Añade un nuevo Piloto.
     * Requisito: "Añadir un nuevo Piloto" (con identificador, nombre y apellidos)
     */
    @Override
    public Piloto añadirPiloto(String identificador, String nombre, String apellidos) {
        logger.info(String.format("Inicio añadirPiloto(id=%s, nombre=%s, apellidos=%s)",
                identificador, nombre, apellidos));
        Piloto newPiloto = new Piloto(identificador, nombre, apellidos);
        this.pilotos.add(newPiloto);
        logger.info("Fin añadirPiloto: piloto añadido -> " + newPiloto);
        return newPiloto;
    }

    /**
     * Lista de drones ordenados descendentemente por horas de vuelo.
     * Requisito: "Listado de drones ordenado descendentemente por horas de vuelo"
     */
    @Override
    public List<Dron> listarDronesPorHorasDeVuelo() {
        logger.info("Inicio listarDronesPorHorasDeVuelo()");
        List<Dron> dronesOrdenados = new ArrayList<>(this.drones);
        // Usamos Double.compare si getHorasVuelo() devuelve double
        dronesOrdenados.sort((d1, d2) -> Double.compare(d2.getHorasVuelo(), d1.getHorasVuelo()));
        logger.info("Fin listarDronesPorHorasDeVuelo(): " + dronesOrdenados);
        return dronesOrdenados;
    }

    /**
     * Lista de pilotos ordenados descendentemente por horas de vuelo.
     * Requisito: "Listado de pilotos ordenado descendentemente por horas de vuelo"
     */
    @Override
    public List<Piloto> listarPilotosPorHorasDeVuelo() {
        logger.info("Inicio listarPilotosPorHorasDeVuelo()");
        List<Piloto> pilotosOrdenados = new ArrayList<>(this.pilotos);  // Copia de la lista
        pilotosOrdenados.sort((p1, p2) -> p2.getHorasVuelo() - p1.getHorasVuelo());
        logger.info("Fin listarPilotosPorHorasDeVuelo(): " + pilotosOrdenados);
        return pilotosOrdenados;
    }

    /**
     * Guarda un dron en el almacén para mantenimiento.
     * Requisito: "Guardar un dron en un almacén para realizar tareas de mantenimiento"
     */
    @Override
    public void guardarDronEnAlmacen(String identificadorDron) {
        logger.info("Inicio guardarDronEnAlmacen(idDron=" + identificadorDron + ")");
        for (Dron dron : this.drones) {
            if (dron.getIdDron().equals(identificadorDron)) {
                dron.setMantenimiento(true);  // Marcar el dron como en mantenimiento
                this.colaMantenimiento.add(dron);  // Añadirlo a la cola del almacén
                logger.info("Dron " + identificadorDron + " guardado en mantenimiento");
                return;
            }
        }
        logger.error("No se encontró el dron con ID: " + identificadorDron);
    }

    /**
     * Repara el dron que está más cerca de la puerta del almacén.
     * Requisito: "Reparar o realizar tareas de mantenimiento sobre un dron que está en el almacén"
     */
    @Override
    public Dron repararDronEnAlmacen() {
        logger.info("Inicio repararDronEnAlmacen()");
        if (!this.colaMantenimiento.isEmpty()) {
            Dron dron = this.colaMantenimiento.poll(); // Sacamos el dron de la cola (el primero en entrar)
            dron.setMantenimiento(false);  // Marcamos como reparado (fuera de mantenimiento)
            logger.info("Dron " + dron.getIdDron() + " sale del mantenimiento");
            return dron;
        } else {
            logger.error("No hay drones en mantenimiento");
            return null;
        }
    }

    /**
     * Añade una reserva para un plan de vuelo.
     * Requisito: "Añadir una reserva para un plan de vuelo" y comprobaciones de solapamiento y estado.
     */
    @Override
    public void añadirReservaPlanVuelo(String identificadorDron, Date fecha, int duracion,
                                       String posicionInicio, String posicionDestino,
                                       String identificadorPiloto) {
        logger.info(String.format("Inicio añadirReservaPlanVuelo(dron=%s, fecha=%s, dur=%d, ini=%s, dest=%s, piloto=%s)",
                identificadorDron, fecha, duracion, posicionInicio, posicionDestino, identificadorPiloto));

        // Buscar el dron y el piloto en las listas
        Dron dron = null;
        for (Dron d : this.drones) {
            if (d.getIdDron().equals(identificadorDron)) {
                dron = d;
                break;
            }
        }
        Piloto piloto = null;
        for (Piloto p : this.pilotos) {
            if (p.getIdPiloto().equals(identificadorPiloto)) {
                piloto = p;
                break;
            }
        }
        if (dron == null || piloto == null) {
            logger.error("No se encontró el dron o piloto (dron=" + dron + ", piloto=" + piloto + ")");
            return; // Podrías lanzar una excepción si lo deseas.
        }
        // Comprobar si el dron está en mantenimiento
        if (dron.isMantenimiento()) {
            logger.error("El dron " + dron.getIdDron() + " está en mantenimiento. No se puede reservar.");
            return;
        }
        // Calcular la fecha de finalización de la nueva reserva
        Date fechaFin = new Date(fecha.getTime() + duracion * 60L * 60L * 1000L);
        // Comprobar solapamientos con reservas existentes
        for (Reserva r : this.reservas) {
            if (r.getDronId().equals(dron.getIdDron()) || r.getPilotoId().equals(piloto.getIdPiloto())) {
                Date rFin = new Date(r.getFecha().getTime() + r.getDuracion() * 60L * 60L * 1000L);
                if (fecha.before(rFin) && fechaFin.after(r.getFecha())) {
                    logger.error("Solapamiento: ya existe una reserva en ese intervalo");
                    return;
                }
            }
        }
        // Crear la nueva reserva
        Reserva newReserva = new Reserva(dron.getIdDron(), fecha, duracion, posicionInicio, posicionDestino, piloto.getIdPiloto());
        this.reservas.add(newReserva);
        // (Opcional) Se añade la reserva a las listas internas del dron y del piloto
        dron.añadirReserva(newReserva);
        piloto.añadirReserva(newReserva);
        logger.info("Reserva añadida: " + newReserva);
    }

    /**
     * Devuelve la lista de reservas asignadas a un piloto concreto.
     * Requisito: "Listado de reservas de planes de vuelo que han sido asignados a un piloto."
     */
    @Override
    public List<Reserva> listarReservasPorPiloto(String identificadorPiloto) {
        logger.info("Inicio listarReservasPorPiloto(piloto=" + identificadorPiloto + ")");
        List<Reserva> resPiloto = new ArrayList<>();
        for (Reserva reserva : this.reservas) {
            if (reserva.getPilotoId().equals(identificadorPiloto))
                resPiloto.add(reserva);
        }
        logger.info("Reservas encontradas para el piloto " + identificadorPiloto + ": " + resPiloto.size());
        return resPiloto;
    }

    /**
     * Devuelve la lista de planes de vuelo asignados a un dron concreto.
     * Requisito: "Listado de planes de vuelo que han sido asignadas a un dron"
     */
    @Override
    public List<Reserva> listarPlanesVueloPorDron(String identificadorDron) {
        logger.info("Inicio listarPlanesVueloPorDron(dron=" + identificadorDron + ")");
        List<Reserva> resDron = new ArrayList<>();
        for (Reserva reserva : this.reservas) {
            if (reserva.getDronId().equals(identificadorDron))
                resDron.add(reserva);
        }
        logger.info("Reservas encontradas para el dron " + identificadorDron + ": " + resDron.size());
        return resDron;
    }
}
