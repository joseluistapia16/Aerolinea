/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

/**
 *
 * @author jtapia
 */
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Ruta;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ConsultaRuta extends GenericForwardComposer {

    int ind = 0;
    Consultas consu = new Consultas();
    Map<String, Ruta> parametro = new HashMap<String, Ruta>();
    private static final long serialVersionUID = 1L;
    @Wire
    Listbox tabla;
    @Wire
    Window consuRuta;
    @Wire
    Button closeBtn, buscar;
    @Wire
    Textbox salida, destino;
    List<Ruta> lista = new ArrayList<Ruta>();

    public void onClick$closeBtn() {
        consuRuta.detach();
    }

    public void onClick$tabla() {
        try {
            if (lista.size() > 0) {
                int id = tabla.getSelectedIndex();
                Ruta objeto = lista.get(id);
                parametro.put("objeto", objeto);
                //   Archivo.crearArchivo("curso", iden.toString());
                System.out.println(objeto.getRuta());
                System.out.println(objeto.getRuta());
                Window window = (Window) Executions.createComponents("/editarRuta.zul", null, parametro);
                window.doModal();
               
                consuRuta.detach();

            }
        } catch (Exception e) {

        }

    }

    public void onClick$buscar() {
        Tabla1b(salida.getText(), destino.getText());
    }

    public void onCreate$consuRuta() {  // Ejecuta los procesos al arrancar la pagina

        Tabla();

    }

    public void Tabla1b(String salida1, String destino1) {
        if (salida1 == null) {
            salida1 = "";
        }
        if (destino1 == null) {
            destino1 = "";
        }
           if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }
        lista = consu.filtrarRutas("lan_airlines", salida1, destino1);
        tabla.setRows(7);

        Listhead cabeza = new Listhead();
        Listheader ti = new Listheader("Id");
        ti.setAlign("center");
       // ti.setWidth("40px");
        Listheader to = new Listheader("Rutas");
        to.setWidth("120px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Salida");
        to1.setWidth("120px");
        to1.setAlign("center");
        Listheader to2 = new Listheader("Destino");
        to2.setWidth("110px");
        to2.setAlign("center");
        Listheader to3 = new Listheader("Duración");
        to3.setAlign("center");
        //to3.setWidth("40px");
        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {
            Listitem item = new Listitem();
            Listcell cell = new Listcell(lista.get(i).getId_ruta().toString());
            Listcell cell1 = new Listcell(lista.get(i).getRuta());
            Listcell cell2 = new Listcell(lista.get(i).getPartida());
            Listcell cell3 = new Listcell(lista.get(i).getDestino());
            Listcell cell4 = new Listcell("" + lista.get(i).getDuracion());
            cabeza.appendChild(ti);
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            cabeza.appendChild(to2);
            cabeza.appendChild(to3);
            item.appendChild(cell);
            item.appendChild(cell1);
            item.appendChild(cell2);
            item.appendChild(cell3);
            item.appendChild(cell4);
            tabla.appendChild(item);
        }
    }

    public void Tabla() {
        if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }

        lista = consu.listarRutas("lan_airlines");
        tabla.setRows(7);

        Listhead cabeza = new Listhead();
        Listheader ti = new Listheader("Id");
        ti.setAlign("center");
        Listheader to = new Listheader("Rutas");
        to.setWidth("120px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Salida");
        to1.setWidth("120px");
        to1.setAlign("center");
        Listheader to2 = new Listheader("Destino");
        to2.setWidth("110px");
        to2.setAlign("center");
        Listheader to3 = new Listheader("Duración");
        to3.setAlign("center");

        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {

            Listitem item = new Listitem();
            Listcell cell = new Listcell(lista.get(i).getId_ruta().toString());
            Listcell cell1 = new Listcell(lista.get(i).getRuta());
            Listcell cell2 = new Listcell(lista.get(i).getPartida());
            Listcell cell3 = new Listcell(lista.get(i).getDestino());
            Listcell cell4 = new Listcell("" + lista.get(i).getDuracion());
            cabeza.appendChild(ti);
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            cabeza.appendChild(to2);
            cabeza.appendChild(to3);

            item.appendChild(cell);
            item.appendChild(cell1);
            item.appendChild(cell2);
            item.appendChild(cell3);
            item.appendChild(cell4);
            tabla.appendChild(item);
        }
    }

}
