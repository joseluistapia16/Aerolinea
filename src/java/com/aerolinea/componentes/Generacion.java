/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.componentes;

import java.util.ArrayList;

/**
 * 1716290059
 * @author TapiaSoftware
 */
public class Generacion {

    public static String generaUsuario(String v1, String v2) {
        String usuario = "", ape = "";
        for (int i = 0; i < v1.length(); i++) {
            if (v1.charAt(i) != ' ') {
               
                usuario = usuario + String.valueOf(v1.charAt(i)).toLowerCase();
                if(i==0){
                    usuario = usuario.toUpperCase();
                }
                break;
            }
        }

        for (int i = 0; i < v2.length(); i++) {
            if (!" ".equals(String.valueOf(v2.charAt(i)))) {
                ape = ape + String.valueOf(v2.charAt(i));

            } else {
                break;
            }
        }
        usuario = usuario + ape;
        return usuario;

    }

    public static ArrayList<String> descompnerCadena(String v1) {
        String cadena = "";
        ArrayList<String> lista = new ArrayList<String>();
        for (int i = 0; i < v1.length(); i++) {
            if (v1.charAt(i) != '-') {
                cadena = cadena + v1.charAt(i);
            } else {
                if (v1.charAt(i) == '-') {
                    lista.add(cadena);
                    cadena = "";
                }
            }
        }
        return lista;
    }
    
    

}
