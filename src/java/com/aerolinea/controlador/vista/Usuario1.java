package com.aerolinea.controlador.vista;

import com.aerolinea.dao.Consultas;
import com.reservacion.archivos.Archivo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Menuitem;
import com.aerolinea.dominio.Usuario;
import org.zkoss.zul.Window;

public class Usuario1 extends GenericForwardComposer {

    Window usuario1;

    Menuitem ruta0, ruta1, ruta00, horario2, airbus1, airbus2, elalumno, elidicr, salir,
            cliente1, cliente2;
    Consultas consu = new Consultas();
    Map<String, com.aerolinea.dominio.Usuario> parametro = new HashMap<String, com.aerolinea.dominio.Usuario>();
    String cedula;
    Button login;     // Las variables son declaradas de la misma forma como se
    // se hace en la vista mismo tipo(etiqueta) y nombre exacto

    public void onCreate$usuario1() { // Evento al crear la ventana en la vista
         cedula = Archivo.leerArchivo("UsuarioEC");
        usuario1.setTitle(Archivo.leerArchivo("usuario"));
       
       

    }

    public void onClick$ruta00() {
         cedula = Archivo.leerArchivo("UsuarioEC");
        ArrayList<Usuario> lista = consu.filtrarClientes("lan_airlines", cedula, 1); 
        parametro.put("objeto", lista.get(0));
        try {
            Window window = (Window) Executions.createComponents(
                    "/EdicionClientes.zul", null, parametro); // aqui accede a la ventana Ruta
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$ruta0() {
        try {
            Window window = (Window) Executions.createComponents(
                    "/Reservacion.zul", null, null); // aqui accede a la ventana Ruta
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$ruta1() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaRutas.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$salir() {
        try {
            Executions.sendRedirect("/index.zul");
        } catch (Exception eu) {

        }
    }

    public void onClick$horario1() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/nuevoHorario.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$horario2() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaHorarios.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$airbus1() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/nuevoAirbus.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$airbus2() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaAirbus.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$cliente2() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaCliente.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$cnprofesor() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consuProfesor.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$eliprofesor() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/conelProfesor.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$inalumno() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/NuevoAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$edalumno() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consuAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

    public void onClick$elalumno() {
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/conelAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }

}
