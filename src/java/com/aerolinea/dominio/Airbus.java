/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dominio;

/**
 *
 * @author mayra
 */
public class Airbus {
    private Long id_airbus;
    private Integer numero_airbus;
    private String codigo;
    private Integer capacidad;
    private Long id_ruta;

    public Airbus() {
    }
    

    public Airbus(Long id_airbus, Integer numero_airbus, String codigo, Integer capacidad, Long id_ruta) {
        this.id_airbus = id_airbus;
        this.numero_airbus = numero_airbus;
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.id_ruta = id_ruta;
    }

    public Airbus(Integer numero_airbus, String codigo, Integer capacidad, Long id_ruta) {
        this.numero_airbus = numero_airbus;
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.id_ruta = id_ruta;
    }
    
    

     
    public Long getId_airbus() {
        return id_airbus;
    }

    public void setId_airbus(Long id_airbus) {
        this.id_airbus = id_airbus;
    }

    public Integer getNumero_airbus() {
        return numero_airbus;
    }

    public void setNumero_airbus(Integer numero_airbus) {
        this.numero_airbus = numero_airbus;
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

    public Long getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(Long id_ruta) {
        this.id_ruta = id_ruta;
    }
    
    
    
}
