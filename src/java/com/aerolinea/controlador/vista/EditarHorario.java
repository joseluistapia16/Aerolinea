/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Horario;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
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
public class EditarHorario extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Combobox rutas;
    Intbox hora, minuto;
    Window EDhorario, dialog;
    Button closeBtn, grabar,eliminar;
    int posi = 0;
    ArrayList<com.aerolinea.dominio.Ruta> lista = consu.listarRutas("lan_airlines");
    ArrayList<Long> idruta = new ArrayList<Long>();
    Horario objeto2 = new Horario();
    //////////////////////
    FiltroHorarioRuta objeto = null;

    public void onCreate$EDhorario() {
        init();
        llenarComboRutas();
        int h=devolverPosicionCombo(objeto);
        rutas.setSelectedIndex(h);
    }

    public void init() {
        Iterator it = arg.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            objeto = (FiltroHorarioRuta) arg.get(key);
            dialog = (Window) arg.get("PARENT_WINDOW");
           // System.out.println(" hola " + objeto.getRuta());
            String cadena[] = objeto.getHora().toString().split(":");
            hora.setValue(Integer.parseInt(cadena[0]));
            minuto.setValue(Integer.parseInt(cadena[1]));
        }

    }

      public void onClick$eliminar() {
        int po = dao.eliminarHorario("lan_airlines", objeto);
        
        if (po == 1) {
            eliminar.setDisabled(true);grabar.setDisabled(true);
            Messagebox.show("El Horario a sido Eliminado!!");            
        } else {
            Messagebox.show("No se eliminó los Datos!!");            
        }
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
            posi = pos;
        }
    }

    //////////////////////
    public void onClick$closeBtn() {
        EDhorario.detach();

    }

    public void onClick$grabar() {
        int pos = rutas.getSelectedIndex();
        String hor = (hora.getValue() + ":" + minuto.getValue() + ":" + 0);
        Time hra = Time.valueOf(hor);
        objeto2.setHora((Time) hra);
        objeto2.setId_ruta(idruta.get(pos));
        if (consu.filtrarHorario("lan_airlines", objeto2).isEmpty()) {
            System.out.println(" hey " + (Time) hra);
            if (pos > -1) {
                posi = pos;
                record();
            } else {
                Messagebox.show("Elija una Ruta!!");
            }
        } else {
            Messagebox.show("La Ruta y el Horario Ya Existe!!");
        }
    }

    public void record() {
        objeto2.setId_ruta(idruta.get(posi)); objeto2.setId_horario(objeto.getId_horario());
        System.out.println(objeto2.getHora() + " pilas grabar  " + objeto2.getId_ruta()+ " " + objeto2.getId_horario());
        int res = dao.actualizarHorario("lan_airlines", objeto2);
        if (res == 1) {
            Messagebox.show("Se grabaron los datos!!");
            EDhorario.detach();
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
                //System.out.println(" id ruta " + idruta.get(i));
                rutas.appendChild(cmb);
            }
        }
    }
    
    
    //
    private int devolverPosicionCombo( FiltroHorarioRuta obj){
        int p=0;
         for (int i = 0; i < lista.size(); i++) {
             if(obj.getId_ruta()==lista.get(i).getId_ruta() 
                     && obj.getPartida().equals(lista.get(i).getPartida()) &&
                     obj.getDestino().equals(lista.get(i).getDestino())){
                 p= i;  break;
             }
         }
        return p;
    }

}
