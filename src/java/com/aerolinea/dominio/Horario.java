/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dominio;

 
import java.sql.Time;

/**
 *
 * @author mayra
 */
public class Horario {
   private Long id_horario;
   private Long id_ruta;
   private Time hora;
    public Horario() {
    }

    public Horario(Long id_horario, Long id_ruta, Time hora) {
        this.id_horario = id_horario;
        this.id_ruta = id_ruta;
        this.hora = hora;

    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public Long getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(Long id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
     
}
