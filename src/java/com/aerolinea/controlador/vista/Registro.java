/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.componentes.Fecha;
import com.aerolinea.componentes.Generacion;
import com.aerolinea.componentes.Validacion;
import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Usuario;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class Registro extends GenericForwardComposer {

    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox cedula, nombre, apellido, telefono, direccion, correo;
    Window registroN;
    Button closeBtn, grabar, buscar;
    String codigo = "";
    int rep = 0;
    Integer op = 0;
    String mensaje = "";
    ArrayList<Usuario> lista = consu.ConsultaUsuario("lan_airlines", "select * from usuarios  order by nombre_usuario");
    ArrayList<String> validar = new ArrayList<String>();
    //////////////////////
    Map<String, Integer> parametro = new HashMap<String, Integer>();

    public void onCreate$registroN() {
        inHabilitar(true);
    }

    public void onClick$buscar() {
        ValidarCedula();
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

    public int validarCampos() {
        mensaje = "";
        int conta = 0;
        if (Validacion.Cedula(cedula.getText()) == false) {
            mensaje = mensaje + " Cedula ";
            conta++;
        } else {
            if (consu.ConsultaUsuario("lan_airlines", "select * from usuarios WHERE  cedula='"
                    + cedula.getText() + "'").size() > 0) {
                mensaje = mensaje + " Cedula Ya Existe ";
                conta++;
            }
        }

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

    public void ValidarCedula() {
        if (Validacion.Cedula(cedula.getText()) == true) {
            if (consu.ConsultaUsuario("lan_airlines", "select * from usuarios where cedula='"
                    + cedula.getText() + "'").size() > 0) {
                Messagebox.show(cedula.getText() + " ! ! " + "\n" + " Ya Existe");
                inHabilitar(true);
            } else {
                inHabilitar(false);
            }
        } else {
            inHabilitar(true);
            Messagebox.show(" Cedula Invàlida !");

        }

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
        registroN.detach();
//        if (rep == 0 && validarCampos() > 0) {
//            nprofesor.detach();
//        }
//        String h = cedula.getText();
//        if (rep > 0 && validarCampos() == 0) {
//
//            record(h);
//            nprofesor.detach();
//        }
//        if (rep == 0 && validarCampos() == 0) {
//            Messagebox.show("Desea Grabar ?", "Confirm Dialog", Messagebox.OK | Messagebox.IGNORE | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
//                public void onEvent(Event evt) throws InterruptedException {
//                    String hi = cedula.getText();
//                    if (evt.getName().equals("onOK")) {
//                        record(hi);
//                        alert("Data Saved !");
//                        nprofesor.detach();
//                    }  // if sec    
//
//                    if (evt.getName().equals("onCANCEL")) {
//                        alert("Data Saved !");
//                        nprofesor.detach();
//                    }  // if sec  
//                }  // imetod sec               
//            }); // metodo mensaje        
//        } // if princ

    }

    public void onClick$grabar() {
        String h = cedula.getText();
        int res = validarCampos();
       
        // System.out.println("Respuesta : "+res+  " Huy esto: " + rep);
        if (res == 0) {
          
                record(h);
                grabar.setDisabled(true);
 
             
        }else{
            Messagebox.show(""+mensaje);
        }
         

    }

    public void record(String cadena) {
        try {
            ArrayList<Usuario> lista2 = consu.ConsultaUsuario("lan_airlines", "SELECT * from usuarios");
            int pos = lista2.size();
          //  System.out.println(" hey " + pos);
            String usuario = Generacion.generaUsuario(nombre.getText(), apellido.getText()) + pos;
            Usuario objeto = new Usuario("USUARIO_EXTERNO", cedula.getText(), nombre.getText(),
                    apellido.getText(), telefono.getText(), direccion.getText(), correo.getText(),
                    Fecha.Fecha(), usuario, usuario + cedula.getText(), Date.valueOf(Fecha.FechaSql()));
            //System.out.println(objeto.getFecha());
            dao.insertarUsuarios("lan_airlines", objeto);
            lista2.clear();
        } catch (Exception ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void update(String cedulas) {
//        try {
//            Usuario objeto = new Usuario("USUARIO_EXTERNO", nombre.getText(),
//                    apellido.getText(), telefono.getText(), direccion.getText(), correo.getText());
//            dao.actualizarUsuarios("lan_airlines", objeto, cedula.getText());
//        } catch (Exception ex) {
//            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    
    
}
