/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class EditarRuta extends GenericForwardComposer {
    
    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox ruta, partida, destino;
    Doublebox duracion;
    Window Nruta, dialog;
    Button closeBtn, grabar, eliminar;
    com.aerolinea.dominio.Ruta objeto = null;
    
    public void onCreate$Nruta() {
        init();        
    }

    public void init() {
        Iterator it = arg.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            objeto = (com.aerolinea.dominio.Ruta) arg.get(key);
            dialog = (Window) arg.get("PARENT_WINDOW");
            System.out.println(" hola " + objeto.getRuta());
            ruta.setValue(objeto.getRuta());
            partida.setValue(objeto.getPartida());
            destino.setValue(objeto.getDestino());
            duracion.setValue(objeto.getDuracion());
        }
        
    }
    
    public void onClick$closeBtn() {
        Nruta.detach();
    }
    
    public void onChange$ruta() {
        ruta.setText(ruta.getText().toUpperCase());
    }
    
    public void onChange$partida() {
        partida.setText(partida.getText().toUpperCase());
    }
    
    public void onChange$destino() {
        destino.setText(destino.getText().toUpperCase());
    }
    
    public void onClick$grabar() {
        ArrayList<com.aerolinea.dominio.Ruta> lista = consu.filtrarRutas("lan_airlines", partida.getText(), destino.getText());
        System.out.println(" lista " + lista.size());
        if (lista.size() < 1 || (lista.size() == 1 && lista.get(0).getId_ruta() == objeto.getId_ruta())) {
            record();
        } else {
            Messagebox.show("La Ruta " + partida.getText() + "  " + destino.getText() + " Ya Existe");
        }
    }
    
    public void onClick$eliminar() {
        int po = dao.eliminarRutas("lan_airlines", objeto);
        
        if (po == 1) {
            eliminar.setDisabled(true);grabar.setDisabled(true);
            Messagebox.show("La Ruta a sido Eliminada!!");            
        } else {
            Messagebox.show("No se eliminó los Datos!!");            
        }
    }
    
    public void record() {
        
        objeto.setRuta(ruta.getText());
        objeto.setPartida(partida.getText());
        objeto.setDestino(destino.getText());
        objeto.setDuracion(duracion.getValue());
        System.out.println(objeto.getRuta() + " " + objeto.getPartida() + " " + objeto.getDestino() + " "
                + objeto.getDuracion() + " " + objeto.getCodigo());
        int res = dao.actualizarRutas("lan_airlines", objeto);
        if (res != 1) {
            Messagebox.show("No se aceptan ningún valor Nulo!!  ");
        } else {
            Messagebox.show("Datos Editar!!  ");
            //grabar.setDisabled(true);
        }
    }
}
