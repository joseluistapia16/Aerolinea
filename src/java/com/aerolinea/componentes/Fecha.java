package com.aerolinea.componentes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String dias = "" + Dia(), mes = "" + Mes();
        if (dias.length() == 1) {
            dias = "0" + dias;
        }
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        return "" + Anio() + "-" + mes + "-" + dias;
    }

    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }

    public static int comparacionFecha(String fecha1, String fecha2) {
        int val = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(fecha1);
            Date date2 = sdf.parse(fecha2);
            val = date1.compareTo(date2);
            //  fecha 1 mayor a 2 = -1   // fecha 2 es mayor a fecha 1 = 1 // fecha 1 es igual es fecha 2 = 0
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }
    
     public static String dividirFecha(String fecha){
        String cad[] =  fecha.split(" ");
        
        return cad[5]+"-"+intMes(cad[1])+"-"+cad[2];
        
    }

    public static int intMes(String mes) {
        mes = mes.toUpperCase();
        int gui = 0;

        if ("JAN".equals(mes)) {
            gui = 01;
        }
        if ("FEB".equals(mes)) {
            gui = 02;
        }
        if ("MAR".equals(mes)) {
            gui = 03;
        }
        if ("APR".equals(mes)) {
            gui = 04;
        }
        if ("MAY".equals(mes)) {
            gui = 05;
        }
        if ("JUN".equals(mes)) {
            gui = 06;
        }
        if ("JUL".equals(mes)) {
            gui = 07;
        }
        if ("AUG".equals(mes)) {
            gui = 8;
        }
 
        if ("SEP".equals(mes)) {
            gui = 9;
        }
        if ("OCT".equals(mes)) {
            gui = 10;
        }
        if ("NOV".equals(mes)) {
            gui = 11;
        }
        if ("DEC".equals(mes)) {
            gui = 12;
        }
        
        return gui;
    }
}
