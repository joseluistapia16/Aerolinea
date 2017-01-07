/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dominio;

/**
 *
 * @author MAYRA
 */
public class Clase {
    private Long id_clase;
    private String nombre;
    private Double valor;

    public Clase() {
    }

    public Clase(Long id_clase, String nombre, Double valor) {
        this.id_clase = id_clase;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Long getId_clase() {
        return id_clase;
    }

    public void setId_clase(Long id_clase) {
        this.id_clase = id_clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
