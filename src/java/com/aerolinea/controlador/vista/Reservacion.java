/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.componentes.Fecha;
import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Airbus;
import com.aerolinea.dominio.Clase;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.util.ArrayList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author mayra
 */
public class Reservacion extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Window Vreservacion, dialog;
    Button closeBtn, grabar, bpasajeros;
    Combobox rutas, airbus, horarios, rutasV, airbusV, horariosV, tipo, cabina, asientos, pasajeros;
    Datebox fec1, fec2;
    Intbox npas;
    String vueloI;
    Textbox asintosR, pagar;
    ArrayList<Airbus> listairbus = new ArrayList<Airbus>();
    ArrayList<Long> idruta = new ArrayList<Long>();
    ArrayList<com.aerolinea.dominio.Ruta> listaruta = consu.listarRutas("lan_airlines", 0, "", "");
    ArrayList<FiltroHorarioRuta> listaHR = null;
     ArrayList<Clase> listaclase = consu.listarClases("lan_airlines");
    int posi;
    String fecha1, fecha2;

    public void onCreate$Vreservacion() {
        llenarComboRutas();
        llenarComboTipo();
        llenarCabiinas();
        grabar.setDisabled(true);
        rutasV.setDisabled(true);
        airbusV.setDisabled(true);
        horariosV.setDisabled(true);
        fec2.setDisabled(true);
    }
  public void llenarCabiinas() {
     if (cabina.getItemCount() > 0) {
            cabina.getChildren().clear();
        }

        if (listaruta.size() > 0) {
            Comboitem cmb = null;
            for (int i = 0; i < listaclase.size(); i++) {
                cmb = new Comboitem(listaclase.get(i).getNombre() + " " + listaclase.get(i).getValor());
                cabina.appendChild(cmb);
            }
        }   
  }
    
    
    /// llenar combos 
    public void llenarComboRutas() {
        if (rutas.getItemCount() > 0) {
            rutas.getChildren().clear();
        }

        if (listaruta.size() > 0) {
            Comboitem cmb = null;
            for (int i = 0; i < listaruta.size(); i++) {
                cmb = new Comboitem(listaruta.get(i).getRuta() + " " + listaruta.get(i).getPartida() + " - "
                        + listaruta.get(i).getDestino());
                idruta.add(listaruta.get(i).getId_ruta());
                rutas.appendChild(cmb);
            }
        }
    }

    public void llenarComboTipo() {
        Comboitem cmb = null;
        cmb = new Comboitem("IDA");
        tipo.appendChild(cmb);
        cmb = new Comboitem("IDA y VUELTA");
        tipo.appendChild(cmb);
    }

    public void llenarComboHorarioXRuta(Long id_ruta) {

        if (horarios.getItemCount() > 0) {
            horarios.getChildren().clear();
        }
        listaHR = consu.filtrarHorarioXRuta("lan_airlines", id_ruta);
        if (listaHR.size() > 0) {
            Comboitem cmb1 = null;
            cmb1 = null;
            for (int i = 0; i < listaHR.size(); i++) {
                cmb1 = new Comboitem(listaHR.get(i).getHora().toString());
                horarios.appendChild(cmb1);
            }
            horarios.setValue(listaHR.get(0).getHora().toString());
        }

    }

    public void llenarComboHorarioXRutaV(Long id_ruta) {

        if (horariosV.getItemCount() > 0) {
            horariosV.getChildren().clear();
        }
        listaHR = consu.filtrarHorarioXRuta("lan_airlines", id_ruta);
        if (listaHR.size() > 0) {
            Comboitem cmb1 = null;
            cmb1 = null;
            for (int i = 0; i < listaHR.size(); i++) {
                cmb1 = new Comboitem(listaHR.get(i).getHora().toString());
                horariosV.appendChild(cmb1);
            }
            horariosV.setValue(listaHR.get(0).getHora().toString());
        }
    }

    /// seleccionar tipo de vuelo
    public void onChange$tipo() {
        int pos = tipo.getSelectedIndex();
        if (pos >= 0) {
            if (pos == 1) {
                rutasV.setDisabled(false);
                airbusV.setDisabled(false);
                horariosV.setDisabled(false);
            } else {
                rutasV.setDisabled(true);
                airbusV.setDisabled(true);
                horariosV.setDisabled(true);
            }
        }
    }

    public void onClick$closeBtn() {
        idruta.clear();
        listaruta.clear();
        Vreservacion.detach();
    }

    public void onOpen$rutas() {
        llenarComboRutas();
    }

    public void onChange$rutas() {
        try {
            llenarComboRutas();
            int pos = rutas.getSelectedIndex();
            if (pos >= 0) {
                grabar.setDisabled(false);
                llenarComboHorarioXRuta(listaruta.get(pos).getId_ruta());
                System.out.println(" Reser 1 " + listaruta.get(pos).getDestino() + " " + listaruta.get(pos).getPartida());
                com.aerolinea.dominio.Ruta obje = rutaInversa(listaruta.get(pos).getDestino(), listaruta.get(pos).getPartida(), listaruta);
                listairbus = consu.listarAirbus("lan_airlines", Long.parseLong("0"), obje.getId_ruta(), 0, 3);
                if (listairbus.size() > 0) {
                    airbus.setValue(listairbus.get(0).getCodigo() + " Capacidad :" + listairbus.get(0).getCapacidad());
                    if (obje != null) {
                        rutasV.setValue(obje.getRuta() + " " + obje.getPartida() + " " + obje.getDestino());
                        llenarComboHorarioXRutaV(obje.getId_ruta());
                        listairbus = consu.listarAirbus("lan_airlines", Long.parseLong("0"), obje.getId_ruta(), 0, 3);
                        airbusV.setValue(listairbus.get(0).getCodigo() + " Capacidad :" + listairbus.get(0).getCapacidad());
                    }
                }
                posi = pos;
            }
        } catch (Exception e) {

        }
    }

    public com.aerolinea.dominio.Ruta rutaInversa(String destino, String origen, ArrayList<com.aerolinea.dominio.Ruta> listaruta1) {
        com.aerolinea.dominio.Ruta obje = null;
        for (int i = 0; i < listaruta1.size(); i++) {
            if (destino.equals(listaruta1.get(i).getPartida()) && origen.equals(listaruta1.get(i).getDestino())) {
                obje = new com.aerolinea.dominio.Ruta();
                obje.setId_ruta(listaruta1.get(i).getId_ruta());
                obje.setRuta(listaruta1.get(i).getRuta());
                obje.setPartida(listaruta1.get(i).getPartida());
                obje.setDestino(listaruta1.get(i).getDestino());
                obje.setDuracion(listaruta1.get(i).getDuracion());
                break;
            }
        }
        return obje;
    }

    public void onChange$fec1() {
        if (fec1.getValue() != null) {
            fecha1 = fec1.getValue().toString();
            // System.out.println("Reloj 1 biggo " + fec1.getValue());
            System.out.println(Fecha.FechaSql()+ " biggo Reloj 1 " + fecha1);
       
           int f= Fecha.comparacionFecha(Fecha.FechaSql(),Fecha.dividirFecha(fecha1));
            if(f==1){
                  Messagebox.show("Fecha invàlida!!");
            }else{
                    fec2.setDisabled(false); 
            }
        }

    }

    public void onChange$fec2() {
        if (fec2.getValue() != null) {
//            System.out.println("Reloj 2 " + (fec2.getValue().getYear() + 1900)
//                    + "-" + (fec2.getValue().getMonth() + 1) + "-" + (fec2.getValue().getDate() + 1));
            fecha2 = fec2.getValue().toString();
             System.out.println("Reloj 2 " + fecha2);
           int f= Fecha.comparacionFecha(Fecha.dividirFecha(fecha1),Fecha.dividirFecha(fecha2));
            System.out.println("Resultado "+f+" Reloj 2 " + fecha2);
            if(f==1 ){
                  Messagebox.show("Fecha invàlida!!");
            }
           
        }
    }

    public void onChange$npas() {
          if (npas.getValue() == null || npas.getValue()<1 || npas.getValue()>5) {
             //      System.out.println("Resultado "+npas.getValue()+" Pasajeros ");
               Messagebox.show("Numero de Pasajeros no puede ser nulo, no puede ser mayores a 5!!");
          }
    }
}
