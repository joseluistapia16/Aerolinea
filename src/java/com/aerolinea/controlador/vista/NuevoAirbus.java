/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Airbus;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.util.ArrayList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class NuevoAirbus extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Combobox rutas, horarios;

    Intbox numero, capacidad;
    Textbox codigo;
    Window Nairbus;
    Button closeBtn, grabar;
    ArrayList<Airbus> listairbus = null;
    ArrayList<Long> idruta = new ArrayList<Long>();
    ArrayList<com.aerolinea.dominio.Ruta> listaruta = consu.listarRutas("lan_airlines");
    ArrayList<FiltroHorarioRuta> listaHR = null;
    int posi;

    public void onCreate$Nairbus() {
        llenarComboRutas();
        numero.setDisabled(true);
        capacidad.setDisabled(true);
        grabar.setDisabled(true);
    }

    public void onClick$closeBtn() {
        idruta.clear();
        listaruta.clear();
        Nairbus.detach();
    }

    public void onOpen$rutas() {
        llenarComboRutas();
    }

    public void onChange$rutas() {
        llenarComboRutas();
        int pos = rutas.getSelectedIndex();
        if (pos >= 0) {
            numero.setDisabled(false);
            capacidad.setDisabled(false);
            grabar.setDisabled(false);
            llenarComboHorarioXRuta(listaruta.get(pos).getId_ruta());
            posi = pos;
        }
    }

    public void onChange$numero() {
        // System.out.println("Pinche ruta "+idruta.get(posi));
        listairbus = consu.listarAirbus("lan_airlines",Long.parseLong("-1") , idruta.get(posi), numero.getValue(), 1);
               System.out.println(" numero "+numero.getValue()+"  "+idruta.get(posi)+ " vemos "+ listairbus.size());
        if (listairbus.size() > 0) {
            Messagebox.show("AirBus y la ruta Ya Existe!!");
        } else {
            if (numero.getValue() < 1) {
                codigo.setText("");
            } else {
                codigo.setText("LAN-" + numero.getText());
            }
        }
    }

    public void onClick$grabar() {
        System.out.println(" numero "+numero.getValue()+" grabar Pinche ruta "+idruta.get(posi));
        listairbus = consu.listarAirbus("lan_airlines", Long.parseLong("-1"), idruta.get(posi), numero.getValue(), 1);
        if (listairbus.size() > 0) {
            Messagebox.show("AirBus y la ruta Ya Existe!!");
        } else { 
                record();
             
        }
    }

    public void record() {
        Airbus objeto2 = new Airbus(numero.getValue(), codigo.getText(),
                capacidad.getValue(), idruta.get(posi));
        int res = dao.insertarAirbus("lan_airlines", objeto2);
        if (res != 1) {
            Messagebox.show("No se aceptan ningún valor Nulo!!  ");
        } else {
            Messagebox.show("Datos Guardados!!  ");
            grabar.setDisabled(true);
        }
    }

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
        }
    }

}
