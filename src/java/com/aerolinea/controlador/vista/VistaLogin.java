/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Base;
import com.aerolinea.dao.Consultas;
import com.aerolinea.dominio.Usuario;
import com.reservacion.archivos.Archivo;

import java.util.ArrayList;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class VistaLogin extends GenericForwardComposer {
    Consultas consu = new Consultas();
    Base dao = new Base();
    Textbox usuario, clave;
    Label mensaje;
    ArrayList<Usuario> lista = new ArrayList<Usuario>();
    ArrayList<Usuario> consulta = new ArrayList<Usuario>();
    Button login, registro;
    public void onClick$registro() {
        try {
            Window window = (Window) Executions.createComponents(
                    "/Registro.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$login() {
        mensaje.setValue("");
        lista = consu.filtrarUsuarios("lan_airlines", usuario.getText(), clave.getText());
        if (lista.size() > 0) {
            if ("ADMINISTRADOR".equals(lista.get(0).getNombrerol())) {                
                Archivo.crearArchivo("administrador",
                        lista.get(0).getNombrerol() + ": "
                        + lista.get(0).getNombreusuario() + " " + lista.get(0).getApellido());
             login.setHref("/administrador.zul"); // cuando se traslada a un pagina
            }      
             if ("DIGITADOR".equals(lista.get(0).getNombrerol())) {                
                Archivo.crearArchivo("administrador",
                        lista.get(0).getNombrerol() + ": "
                        + lista.get(0).getNombreusuario() + " " + lista.get(0).getApellido());
               login.setHref("/digitador.zul"); // cuando se traslada a un pagina
            }

        } else {
            // recordF();
            mensaje.setValue("Usuario o clave Incorrecta");
        }

    }

    
     

}
