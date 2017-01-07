/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;
import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import java.util.ArrayList;
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
public class Ruta extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox nuevaruta, partida, destino;
    Doublebox duracion;
    Window Nruta;
    Button closeBtn, grabar;

    public void onClick$closeBtn() {
        Nruta.detach();
    }

    public void onChange$nuevaruta() {
        nuevaruta.setText(nuevaruta.getText().toUpperCase());
    }

    public void onChange$partida() {
        partida.setText(partida.getText().toUpperCase());
    }

    public void onChange$destino() {
        destino.setText(destino.getText().toUpperCase());
    }

    public void onClick$grabar() {
        ArrayList<com.aerolinea.dominio.Ruta> lista = consu.listarRutas("lan_airlines",2, partida.getText(), destino.getText());
        System.out.println(" lista " + lista.size());
        if (lista.size() < 1) {
            record();
        } else {
            Messagebox.show("La Ruta " + partida.getText() + "  "+destino.getText()+ " Ya Existe");
        }
    }

    public void record() {
        com.aerolinea.dominio.Ruta objeto = new com.aerolinea.dominio.Ruta(nuevaruta.getText(), partida.getText(),
                destino.getText(), duracion.getValue());
        int res = dao.insertarRutas("lan_airlines", objeto);
        if (res != 1) {
            Messagebox.show("No se aceptan ningún valor Nulo!!  ");
        } else {
            Messagebox.show("Datos Guardados!!  ");
            grabar.setDisabled(true);
        }
    }
}
