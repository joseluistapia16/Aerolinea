/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Airbus;
import com.aerolinea.querys.compuestos.FiltroAirbusRutas;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
public class EditarAirbus extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Combobox rutas, horarios;

    Intbox numero, capacidad;
    Textbox codigo;
    Window Eairbus, dialog;
    Button closeBtn, grabar, eliminar;
    ArrayList<Airbus> listairbus = null;
    ArrayList<Long> idruta = new ArrayList<Long>();
    ArrayList<com.aerolinea.dominio.Ruta> listaruta = consu.listarRutas("lan_airlines");
    Map<String, FiltroAirbusRutas> parametro = new HashMap<String, FiltroAirbusRutas>();
    ArrayList<FiltroHorarioRuta> listaHR = null;
    int posi;
    FiltroAirbusRutas objeto = null;

    public void init() {
        Iterator it = arg.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            objeto = (FiltroAirbusRutas) arg.get(key);
            dialog = (Window) arg.get("PARENT_WINDOW");
            manejarCombos();
            numero.setValue(objeto.getNumero_airbus());
            capacidad.setValue(objeto.getCapacidad());
            codigo.setValue(objeto.getCodigo());
            rutas.setDisabled(true);
            numero.setDisabled(true);
        }

    }

    public void manejarCombos() {
        rutas.setSelectedIndex(getIntRuta(objeto.getId_ruta(), listaruta));
        llenarComboHorarioXRuta(objeto.getId_ruta());
        posi = rutas.getSelectedIndex();
//        System.out.println(listaruta.get(posi).getRuta()+"  POSI 1 "+posi);
    }

    public void onCreate$Eairbus() {
        llenarComboRutas();
        
        init();
    }

    public void onClick$closeBtn() {
        idruta.clear();
        listaruta.clear();
        Eairbus.detach();
    }

    public void onClick$eliminar() {
        Airbus obel = new Airbus();
        obel.setId_airbus(objeto.getId_airbus());
        System.out.println(" numero "+numero.getValue()+" grabar Eliminar "+obel.getId_airbus());
        int po = dao.eliminarAirbus("lan_airlines", obel);

        if (po == 1) {
            eliminar.setDisabled(true);grabar.setDisabled(true);
            Messagebox.show("La Ruta a sido Eliminada!!");
        } else {
            Messagebox.show("No se eliminó los Datos!!");
        }
    }

    public void onOpen$rutas() {
        llenarComboRutas();
    }

   

    public void onChange$numero() {
        listairbus = consu.listarAirbus("lan_airlines",objeto.getId_airbus(), idruta.get(posi), numero.getValue(), 1);
        System.out.println(" numero " + numero.getValue() + "  " + idruta.get(posi) + " vemos " + listairbus.size());
        if (listairbus.size() > 1) {
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
        listairbus = consu.listarAirbus("lan_airlines", objeto.getId_airbus(), idruta.get(posi), numero.getValue(), 1);
        if (listairbus.size() > 1) {
            Messagebox.show("AirBus y la ruta Ya Existe!!");
        } else { 
                record();
             
        }
    }

    public void record() {
        int pos = rutas.getSelectedIndex();
        Airbus objeto2;
        objeto2 = new Airbus(objeto.getId_airbus(), numero.getValue(), codigo.getText(),
                capacidad.getValue(),
                idruta.get(pos));

        int res = dao.actualizarAirbus("lan_airlines", objeto2);
        if (res != 1) {
            Messagebox.show("No se aceptan ningún valor Nulo!!  ");
        } else {
            Messagebox.show("Datos Guardados!!  ");
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
                System.out.println(listaruta.get(i).getRuta() + "  POSIcion 1 " + idruta.get(i) + " Posi Combo" + i);
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

    public int getIntRuta(Long id_ruta, ArrayList<com.aerolinea.dominio.Ruta> listaruta1) {
        int indice = 0;
        for (int i = 0; i < listaruta1.size(); i++) {
            if (listaruta1.get(i).getId_ruta() == id_ruta) {
                indice = i;
                break;
            }
        }
        listaruta1.clear();
        return indice;
    }

}
