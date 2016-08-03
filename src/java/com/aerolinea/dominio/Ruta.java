/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dominio;

import java.math.BigInteger;

/**
 *
 * @author mayra
 */
public class Ruta {
   private Long id_ruta;
   private String ruta;
   private String partida;
   private String destino;
   private double duracion;
   private String codigo;

    public Ruta() {
    }
   
   

    public Ruta(Long id_ruta, String ruta, String partida, String destino, double duracion, String codigo) {
        this.id_ruta = id_ruta;
        this.ruta = ruta;
        this.partida = partida;
        this.destino = destino;
        this.duracion = duracion;
        this.codigo = codigo;
    }

    

    public Ruta(String ruta, String partida, String destino, double duracion) {
        this.ruta = ruta;
        this.partida = partida;
        this.destino = destino;
        this.duracion = duracion;
 
    }

    public Long getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(Long id_ruta) {
        this.id_ruta = id_ruta;
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

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    

    
}
