/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.querys.compuestos;

import java.sql.Time;

/**
 *
 * @author mayra
 */
public class FiltroHorarioRuta {
    private long id_ruta;
    private long id_horario;
    private String ruta;
    private String partida;
    private String destino;
    private Time hora;

    public FiltroHorarioRuta() {
    }

    public FiltroHorarioRuta(long id_ruta, long id_horario, String ruta, String partida, String destino, Time hora) {
        this.id_ruta = id_ruta;
        this.id_horario = id_horario;
        this.ruta = ruta;
        this.partida = partida;
        this.destino = destino;
        this.hora = hora;
    }

    public long getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(long id_ruta) {
        this.id_ruta = id_ruta;
    }

    public long getId_horario() {
        return id_horario;
    }

    public void setId_horario(long id_horario) {
        this.id_horario = id_horario;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    
    
    
    
}
