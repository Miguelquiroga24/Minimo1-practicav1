package edu.upc.dsa.models;

import java.util.Date;

public class Reserva {
    String idDron;
    Date fecha;
    int duracion;
    String posicionInicio;
    String posicionDestino;
    String idPiloto;

    public Reserva(String idDron, Date fecha, int duracion, String posicionInicio, String posicionDestino, String idPiloto){
        this.idDron = idDron;
        this.fecha = fecha;
        this.duracion = duracion;
        this.posicionInicio = posicionInicio;
        this.posicionDestino = posicionDestino;
        this.idPiloto = idPiloto;
    }

    public String getDronId(){
        return idDron;
    }
    public void setIdDron(String idDron){
        this.idDron = idDron;
    }
    public String getPilotoId(){
        return idPiloto;
    }
    public void setPilotoId(String idPiloto){
        this.idPiloto = idPiloto;
    }
    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public int getDuracion(){
        return duracion;
    }
    public void setDuracion(int duracion){
        this.duracion = duracion;
    }
    public String getInicio(){
        return posicionInicio;
    }
    public void setIncio(String pInicio){
        this.posicionInicio = pInicio;
    }
    public String getDestino(){
        return posicionDestino;
    }
    public void setDestino(String pDestino){
        this.posicionDestino = pDestino;
    }
}
