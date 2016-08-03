 
package com.aerolinea.controlador.vista;


import com.reservacion.archivos.Archivo;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
 
public class Administrador extends GenericForwardComposer{
    Window administrador;
    Textbox usuario,clave;
    Menuitem ruta0,ruta1,horario1,horario2,airbus1,airbus2,elalumno,elidicr; 
    Button login;     // Las variables son declaradas de la misma forma como se
                     // se hace en la vista mismo tipo(etiqueta) y nombre exacto
    public void onCreate$administrador() { // Evento al crear la ventana en la vista
      administrador.setTitle(Archivo.leerArchivo("administrador"));
    }
    public void onClick$ruta0(){
        try {
            Window window = (Window) Executions.createComponents(
                    "/ruta.zul", null, null); // aqui accede a la ventana Ruta
            window.doModal();
        } catch (Exception eu) {

        }
    }
    
      public void onClick$ruta1(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaRutas.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }
      
     public void onClick$horario1(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/nuevoHorario.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }   
    
     public void onClick$horario2(){ 
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaHorarios.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }   
     
     public void onClick$airbus1(){ 
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/nuevoAirbus.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    } 
     
     
     public void onClick$airbus2(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consultaAirbus.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }
     
      public void onClick$cnprofesor(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consuProfesor.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }  
      
       public void onClick$eliprofesor(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/conelProfesor.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }  
      
     
    public void onClick$inalumno(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/NuevoAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    } 
    
     public void onClick$edalumno(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/consuAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }
     
     public void onClick$elalumno(){
        try {
            //create a window programmatically and use it as a modal dialog.
            Window window = (Window) Executions.createComponents(
                    "/conelAlumno.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }
      
}
