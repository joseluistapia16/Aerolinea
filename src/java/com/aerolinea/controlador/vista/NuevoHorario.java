/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Horario;
import java.sql.Time;
import java.util.ArrayList;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class NuevoHorario extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Combobox rutas;
    Intbox hora, minuto;
    Window Nhorario;
    Button closeBtn, grabar;
    int posi = 0;
    ArrayList<com.aerolinea.dominio.Ruta> lista = consu.listarRutas("lan_airlines");
    ArrayList<Long> idruta = new ArrayList<Long>();
    //////////////////////
    Horario objeto = new Horario();

    public void onCreate$Nhorario() {
        llenarComboRutas();
    }

    public void onChange$hora() {
        if (hora.getValue() >= 23) {
            hora.setValue(0);
        }
        if (hora.getValue() < 0) {
            hora.setValue(hora.getValue() * -1);
        }
    }

    public void onChange$minuto() {
        if (minuto.getValue() >= 60) {
            minuto.setValue(0);
        }
        if (minuto.getValue() < 0) {
            minuto.setValue(minuto.getValue() * -1);
        }
    }

    public void onOpen$rutas() {
        llenarComboRutas();
    }

    public void onClick$rutas() {
        llenarComboRutas();
        int pos = rutas.getSelectedIndex();
        if (pos >= 0) {
            hora.setDisabled(false);
            minuto.setDisabled(false);
            objeto.setId_ruta(idruta.get(pos));
            posi=pos;
        }
    }

    //////////////////////
    public void onClick$closeBtn() {
        idruta.clear();lista.clear();
        Nhorario.detach();

    }

    public void onClick$grabar() {
        int pos = rutas.getSelectedIndex();
        String hor = (hora.getValue() + ":" + minuto.getValue()+":"+0);
        Time hra = Time.valueOf(hor);
        objeto.setHora((Time) hra);
        objeto.setId_ruta(idruta.get(pos));
        if (consu.filtrarHorario("lan_airlines", objeto).isEmpty()) {
            System.out.println(" hey " + (Time) hra);
            if (pos > -1) {
                posi = pos;
                record();
            } else {
                Messagebox.show("Elija una Ruta!!");
            }
            }else{
          Messagebox.show("La Ruta y el Horario Ya Existe!!");
      }
        }

    

    public void record() {
        objeto.setId_ruta(idruta.get(posi));
        System.out.println(objeto.getHora() + " pilas id " + objeto.getId_ruta());
        int res = dao.insertarHorario("lan_airlines", objeto);
        if (res == 1) {
            Messagebox.show("Se grabaron los datos!!");
            Nhorario.detach();
        } else {
            Messagebox.show("No se grabaron los datos!!");
        }
    }

    public void llenarComboRutas() {
        if (rutas.getItemCount() > 0) {
            rutas.getChildren().clear();
        }

        if (lista.size() > 0) {
            Comboitem cmb = null;
            for (int i = 0; i < lista.size(); i++) {
                cmb = new Comboitem(lista.get(i).getRuta() + " " + lista.get(i).getPartida() + " - "
                        + lista.get(i).getDestino());
                idruta.add(lista.get(i).getId_ruta());
                rutas.appendChild(cmb);
            }
        }
    }

}
