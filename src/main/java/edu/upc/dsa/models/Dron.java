package edu.upc.dsa.models;
import java.util.ArrayList;
import java.util.List;

//1. Añadimos las importaciones para poder trabajar con las "listas"

public class Dron {
    //2. Estos son los atributos de la clase "Dron". En ID ponemos "idDron" porque hay otro "id" que es el del piloto
    List<Reserva> reservas;

    private String idDron;
    private String nombre;
    private String fabricante;
    private String modelo;
    private double horasVuelo;
    private boolean mantenimiento;
    //3. Creamos el constructor. El constructor es un metodo especial que se usa para crear un objeto (en este caso, un dron). Se llama cuando decimos ¡Quiero un nuevo dron!
    public Dron(String idDron, String nombre, String fabricante, String modelo) {
        this.idDron = idDron;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.horasVuelo = 0;
        this.mantenimiento = false;
        this.reservas = new ArrayList<>(); //Creamos una nueva lista vacía para guardar las reservas. Es como comprar una libreta vacía para anotar las reservas.
    }

    //4. Estos métodos nos permiten obtener y cambiar el valor de cada atributo de forma segura
    // Set es para cambiar y Get es para consultar. Cada uno tiene su Set y su Get


    // Getter y Setter para idDron
    public String getIdDron() {
        return idDron;
    }
    public void setIdDron(String id) {
        this.idDron = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para fabricante
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    // Getter y Setter para modelo
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Getter y Setter para horasVuelo
    public double getHorasVuelo() {
        return horasVuelo;
    }
    public void setHorasVuelo(double horasVuelo) {
        this.horasVuelo = horasVuelo;
    }

    // Getter y Setter para mantenimiento
    public boolean isMantenimiento() {
        return mantenimiento;
    }
    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }


    //reservas (la lista que hemos creado donde se almacenan las reservas)

    // Metodo getter para obtener la lista de reservas del dron
    public List<Reserva> getReservas(){
        // Devuelve la lista de reservas almacenada en el atributo 'reservas'
        return reservas;
    }
    // Metodo setter para asignar una nueva lista de reservas al dron
    public void setReservas(List<Reserva> reservas){
        // Asigna el valor recibido (nueva lista de reservas) al atributo 'reservas'
        this.reservas = reservas;
    }
    // Metodo para añadir una nueva reserva a la lista de reservas existente
    public void añadirReserva(Reserva reserva){
        // Agrega la reserva pasada como parámetro a la lista 'reservas'
        this.reservas.add(reserva);
    }

}
