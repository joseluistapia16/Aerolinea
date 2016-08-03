/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dominio;

import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author mayra
 */
public class Usuario {

    private BigInteger idusuario;
    private String nombrerol;
    private String cedula;
    private String nombreusuario;
    private String apellido;
    private String telefono;
    private String direccion;
    private String Correo;
    private String fechas;
    private String usuario;
    private String clave;
    private Date fecha;
    
     public Usuario() {
    }

    public Usuario(String nombrerol, String nombreusuario, String apellido, String telefono, String direccion, String Correo) {
        this.nombrerol = nombrerol;
        this.nombreusuario = nombreusuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.Correo = Correo;
    }
    
    

    public Usuario(BigInteger idusuario, String nombrerol, String cedula, String nombreusuario, String apellido, String telefono, String direccion, String Correo, String fechas, String usuario, String clave, Date fecha) {
        this.idusuario = idusuario;
        this.nombrerol = nombrerol;
        this.cedula = cedula;
        this.nombreusuario = nombreusuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.Correo = Correo;
        this.fechas = fechas;
        this.usuario = usuario;
        this.clave = clave;
        this.fecha = fecha;
    }

    public Usuario(String nombrerol, String cedula, String nombreusuario, String apellido, String telefono, String direccion, String Correo, String fechas, String usuario, String clave, Date fecha) {
        this.nombrerol = nombrerol;
        this.cedula = cedula;
        this.nombreusuario = nombreusuario;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.Correo = Correo;
        this.fechas = fechas;
        this.usuario = usuario;
        this.clave = clave;
        this.fecha = fecha;
    }
    
    

    public BigInteger getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(BigInteger idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    
}
