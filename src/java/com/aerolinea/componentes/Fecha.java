package com.aerolinea.componentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JOSE LUIS
 */
public class Fecha {

    public static String Fecha() {
        String gui, me;
        Calendar c1 = Calendar.getInstance();
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String ano = Integer.toString(c1.get(Calendar.YEAR));
        if (c1.get(Calendar.MONTH) + 1 == 1) {
            mes = "Enero";
        }
        if (c1.get(Calendar.MONTH) + 1 == 2) {
            mes = "Febrero";
        }
        if (c1.get(Calendar.MONTH) + 1 == 3) {
            mes = "Marzo";
        }
        if (c1.get(Calendar.MONTH) + 1 == 4) {
            mes = "Abril";
        }
        if (c1.get(Calendar.MONTH) + 1 == 5) {
            mes = "Mayo";
        }
        if (c1.get(Calendar.MONTH) + 1 == 6) {
            mes = "Junio";
        }
        if (c1.get(Calendar.MONTH) + 1 == 7) {
            mes = "Julio";
        }
        if (c1.get(Calendar.MONTH) + 1 == 8) {
            mes = "Agosto";
        }
        if (c1.get(Calendar.MONTH) + 1 == 9) {
            mes = "Septiembre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 10) {
            mes = "Octubre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 11) {
            mes = "Noviembre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 12) {
            mes = "Diciembre";
        }

        gui = (dia + "/" + mes + "/" + ano);
        return gui;
    }

    public static int Anio() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.YEAR);
    }

    public static int Mes() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.MONTH) + 1;
    }

    public static int Dia() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.DATE);
    }

    public static String FechaSql() {
         String dias = ""+Dia(), mes = ""+Mes();
         if(dias.length()==1){
            dias = "0" +dias;
         }
         if(mes.length()==1){
            mes = "0" +mes;
         }
        return  ""+Anio()+"-"+mes+"-"+dias;
    }
    
    
    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }
}
