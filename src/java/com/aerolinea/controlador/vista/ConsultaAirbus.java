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
import com.aerolinea.querys.compuestos.FiltroAirbusRutas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

public class ConsultaAirbus extends GenericForwardComposer {

    int ind = 0;
    Consultas consu = new Consultas();
    Map<String, FiltroAirbusRutas> parametro = new HashMap<String, FiltroAirbusRutas>();
    private static final long serialVersionUID = 1L;
    @Wire
    Listbox tabla;
    @Wire
    Window consuAirbus;
    @Wire
    Button closeBtn, buscar;
    @Wire
    Textbox ruta;
    @Wire    
    Textbox numeroruta;
    ArrayList<FiltroAirbusRutas> lista = null;

    public void onClick$closeBtn() {
        consuAirbus.detach();
    }

    public void onClick$tabla() {
        try {
            if (lista.size() > 0) {
                int id = tabla.getSelectedIndex();
                FiltroAirbusRutas objeto = lista.get(id);
                parametro.put("objeto", objeto);
                System.out.println(objeto.getNumero_airbus());
                System.out.println(objeto.getId_ruta());
                Window window = 
                (Window) Executions.createComponents("/editarAirbus.zul", null, parametro);
                window.doModal();
              consuAirbus.detach();
            // En este código cuando se abre un listado de registros
            // al dar un click, por ejemplo se elije un Airbus para su edición    
            }
        } catch (Exception e) {

        }
         
    }

    public void onClick$buscar() {
        Integer num=0 ;
                try{
                 num= Integer.parseInt(numeroruta.getText());
                }catch(Exception e){
                    num=0;
                }
                System.out.println(numeroruta.getText()+" plan b "+num);
        if(num==0){
            Tabla(); 
          
        }else{
        Tabla1b(num);}
    }

    public void onCreate$consuAirbus() {  // Ejecuta los procesos al arrancar la pagina

        Tabla();

    }

    public void Tabla1b(Integer numero) {

        if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }
        lista = consu.listarAirbusRuta("lan_airlines", numero, 1);
        tabla.setRows(7);

        Listhead cabeza = new Listhead();
        Listheader to = new Listheader("Airbus");
        to.setWidth("80px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Capacidad");
        to1.setWidth("180px");
        to1.setAlign("center");
        Listheader to2 = new Listheader("Ruta");
        to2.setWidth("190px");
        to2.setAlign("center");
        Listheader to3 = new Listheader("Salida");
        to2.setWidth("120px");
        to3.setAlign("center");
        Listheader to4 = new Listheader("Destino");
        to2.setWidth("120px");
        to3.setAlign("center");
        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {
            Listitem item = new Listitem();
            Listcell cell1 = new Listcell(lista.get(i).getCodigo());
            Listcell cell2 = new Listcell(lista.get(i).getCapacidad().toString());
            Listcell cell3 = new Listcell(lista.get(i).getRuta());
            Listcell cell4 = new Listcell("" + lista.get(i).getPartida());
            Listcell cell5 = new Listcell("" + lista.get(i).getDestino());
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            cabeza.appendChild(to2);
            cabeza.appendChild(to3);
            cabeza.appendChild(to4);
            item.appendChild(cell1);
            item.appendChild(cell2);
            item.appendChild(cell3);
            item.appendChild(cell4);
            item.appendChild(cell5);
            tabla.appendChild(item);
        }

    }

    public void Tabla() {
        if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }

        lista = consu.listarAirbusRuta("lan_airlines", 34, 0);
        tabla.setRows(7);
        Listhead cabeza = new Listhead();
        Listheader to = new Listheader("Airbus");
        to.setWidth("80px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Capacidad");
        to1.setWidth("180px");
        to1.setAlign("center");
        Listheader to2 = new Listheader("Ruta");
        to2.setWidth("190px");
        to2.setAlign("center");
        Listheader to3 = new Listheader("Salida");
        to2.setWidth("120px");
        to3.setAlign("center");
        Listheader to4 = new Listheader("Destino");
        to2.setWidth("120px");
        to3.setAlign("center");
        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {
            Listitem item = new Listitem();
            Listcell cell1 = new Listcell(lista.get(i).getCodigo());
            Listcell cell2 = new Listcell(lista.get(i).getCapacidad().toString());
            Listcell cell3 = new Listcell(lista.get(i).getRuta());
            Listcell cell4 = new Listcell("" + lista.get(i).getPartida());
            Listcell cell5 = new Listcell("" + lista.get(i).getDestino());
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            cabeza.appendChild(to2);
            cabeza.appendChild(to3);
            cabeza.appendChild(to4);
            item.appendChild(cell1);
            item.appendChild(cell2);
            item.appendChild(cell3);
            item.appendChild(cell4);
            item.appendChild(cell5);
            tabla.appendChild(item);
        }
    }

}
