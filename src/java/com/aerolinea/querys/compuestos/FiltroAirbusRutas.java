/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.querys.compuestos;

/**
 *
 * @author mayra
 */
public class FiltroAirbusRutas {
    private Long id_airbus;
    private String codigo;
    private Integer capacidad;
    private Integer numero_airbus;
    private Long id_ruta;
    private String ruta;
    private String partida;
    private String destino;

    public FiltroAirbusRutas(Long id_airbus, String codigo, Integer capacidad, Integer numero_airbus, Long id_ruta, String ruta, String partida, String destino) {
        this.id_airbus = id_airbus;
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.numero_airbus = numero_airbus;
        this.id_ruta = id_ruta;
        this.ruta = ruta;
        this.partida = partida;
        this.destino = destino;
    }

    public Long getId_airbus() {
        return id_airbus;
    }

    public void setId_airbus(Long id_airbus) {
        this.id_airbus = id_airbus;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getNumero_airbus() {
        return numero_airbus;
    }

    public void setNumero_airbus(Integer numero_airbus) {
        this.numero_airbus = numero_airbus;
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

    
    
    
}
