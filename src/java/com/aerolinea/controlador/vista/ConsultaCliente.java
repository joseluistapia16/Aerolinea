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
import com.aerolinea.dominio.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class ConsultaCliente extends GenericForwardComposer {

    int ind = 0;
    Consultas consu = new Consultas();
    Map<String, Usuario> parametro = new HashMap<String, Usuario>();
    private static final long serialVersionUID = 1L;
    @Wire
    Listbox tabla;
    @Wire
    Window conselprofe;
    @Wire
    Button closeBtn, buscar;
    @Wire
    Textbox filtro;
    List<Usuario> lista = new ArrayList<Usuario>();

    public void onClick$closeBtn() {
        conselprofe.detach();
    }

    public void onClick$tabla() {
        try {
            if (lista.size() > 0) {
                int id = tabla.getSelectedIndex();
                Usuario objeto = lista.get(id);
                parametro.put("objeto", objeto);
                System.out.println(" objeto " + objeto.getNombreusuario() + " " + id);
                Window window = (Window) Executions.createComponents("/editarClientes.zul", null, parametro);
                window.doModal();
                conselprofe.detach();
            }
        } catch (Exception e) {

        }

    }

    public void onClick$buscar() {
        Tabla1b();
    }

    public void onCreate$conselprofe() {  // Ejecuta los procesos al arrancar la pagina

        Tabla();

    }

    public void Tabla1b() {

        if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }
        lista = consu.filtrarClientes("lan_airlines", filtro.getValue(), 1);
        tabla.setRows(7);

        Listhead cabeza = new Listhead();
        Listheader to = new Listheader("Cedula");
        to.setWidth("80px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Nombre");
//        to1.setWidth("180px");
        to1.setAlign("center");
        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {
            Listitem item = new Listitem();
            Listcell cell1 = new Listcell(lista.get(i).getCedula());
            Listcell cell2 = new Listcell(lista.get(i).getNombreusuario()+" "+lista.get(i).getApellido());
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            item.appendChild(cell1);
            item.appendChild(cell2);
            tabla.appendChild(item);
        }

    }

    public void Tabla() {
        if (tabla.getRows() > 0) {
            tabla.getChildren().clear();
        }

        lista = consu.filtrarClientes("lan_airlines", filtro.getValue(), 0);
        tabla.setRows(7);
        Listhead cabeza = new Listhead();
        Listheader to = new Listheader("Cedula");
        to.setWidth("80px");
        to.setAlign("center");
        Listheader to1 = new Listheader("Nombre");
//        to1.setWidth("180px");
        to1.setAlign("center");
        tabla.appendChild(cabeza);
        for (int i = 0; i < lista.size(); i++) {
            Listitem item = new Listitem();
            Listcell cell1 = new Listcell(lista.get(i).getCedula());
            Listcell cell2 = new Listcell(lista.get(i).getNombreusuario()+" "+lista.get(i).getApellido());
            cabeza.appendChild(to);
            cabeza.appendChild(to1);
            item.appendChild(cell1);
            item.appendChild(cell2);
            tabla.appendChild(item);
        }
    }
}
