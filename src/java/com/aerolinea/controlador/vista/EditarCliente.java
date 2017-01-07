/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.componentes.Validacion;
import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class EditarCliente extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox cedula, nombre, apellido, telefono, direccion, correo;
    Window Ecliente, dialog;
    Button closeBtn, grabar, buscar, eliminar;
    String codigo = "";
    int rep = 0;
    Integer op = 0;
    String mensaje = "";
    //  ArrayList<Usuario> lista = consu.filtrarClientes("lan_airlines", cedula.getText(), 1);
    ArrayList<String> validar = new ArrayList<String>();
    //////////////////////
    Usuario objeto = null;

    public void init() {
        Iterator it = arg.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            objeto = (Usuario) arg.get(key);
            dialog = (Window) arg.get("PARENT_WINDOW");
            cedula.setValue(objeto.getCedula());
            System.out.println(" uauario biggo " + objeto.getNombreusuario());
            nombre.setValue(objeto.getNombreusuario());
            apellido.setValue(objeto.getApellido());
            direccion.setValue(objeto.getDireccion());
            telefono.setValue(objeto.getTelefono());
            correo.setValue(objeto.getCorreo());
            cedula.setDisabled(true);
            buscar.setDisabled(true);
        }

    }

    public void onCreate$Ecliente() {

        init();
    }

    public void onChange$nombre() {
        nombre.setText(nombre.getText().toUpperCase());
    }

    public void onChange$apellido() {
        apellido.setText(apellido.getText().toUpperCase());
    }

    public void onChange$direccion() {
        direccion.setText(direccion.getText().toUpperCase());
    }

    public void onClick$eliminar() {
        int po = dao.eliminarClientes("lan_airlines", cedula.getText());

        if (po == 1) {
            eliminar.setDisabled(true);
            grabar.setDisabled(true);
            Messagebox.show("Este Cliente a sido Eliminado!!");
        } else {
            Messagebox.show("No se eliminó los Datos!!");
        }
    }

    public int validarCampos() {
        mensaje = "";
        int conta = 0;

        if ("".equals(nombre.getText())) {
            mensaje = mensaje + " Nombre ";
            conta++;
        }
        if ("".equals(apellido.getText())) {
            mensaje = mensaje + " Apellido ";
            conta++;
        }

        if ("".equals(direccion.getText())) {
            mensaje = mensaje + " Direccion ";
            conta++;
        }

        if (Validacion.Telefono(telefono.getText()) == false) {
            mensaje = mensaje + " Telefono ";
            conta++;
        }

        if (Validacion.Email(correo.getText()) == false) {
            mensaje = mensaje + " Correo ";
            conta++;
        }
        if (conta > 1) {
            mensaje = "Campos " + mensaje + " Invàlidos.";
        }
        if (conta == 1) {
            mensaje = "Campo " + mensaje + " Invàlido.";
        }
        if (conta < 1) {
            mensaje = "";
        }
        return conta;

    }

    public void inHabilitar(boolean valor) {
        nombre.setDisabled(valor);
        apellido.setDisabled(valor);
        telefono.setDisabled(valor);
        direccion.setDisabled(valor);
        correo.setDisabled(valor);;
    }

    //////////////////////
    public void onClick$closeBtn() {
        Ecliente.detach();
    }

    public void onClick$grabar() {
        String h = cedula.getText();
        int res = validarCampos();

        // System.out.println("Respuesta : "+res+  " Huy esto: " + rep);
        if (res == 0) {

            update(h);
            grabar.setDisabled(true);

        } else {
            Messagebox.show("" + mensaje);
        }

    }

    public void update(String cedulas)  {
        try {
            Usuario objeto = new Usuario("USUARIO_EXTERNO", nombre.getText(),
                    apellido.getText(), telefono.getText(), direccion.getText(), correo.getText());
            int res = dao.actualizarUsuarios("lan_airlines", objeto, cedula.getText());
//         System.out.println(cedula.getText()+" Bandera "+res+" "+nombre.getText()+" "+
//                    apellido.getText()+" "+ telefono.getText()+" "+ direccion.getText()+" "+ correo.getText() );
            if (res != 1) {
                Messagebox.show("No se aceptan ningún valor Nulo!!  ");
            } else {
                Messagebox.show("Datos Guardados!!  ");
            }
        } catch (Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
