/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador.vista;

 
import com.reservacion.archivos.Archivo;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author jtapia
 */
public class Digitador extends GenericForwardComposer{
    Textbox usuario,clave;
    Menuitem cl_ingreso,cl_edicion,rs1,rs2,cnprofesoreliprofesor,edalumno,elalumno,elidicr; 
    Button login;
    Window usuarios1;
    
     public void onCreate$usuarios1() {
      usuarios1.setTitle( Archivo.leerArchivo("window"));
    }
      
     public void onClick$cl_ingreso(){
        try {
            Window window = (Window) Executions.createComponents(
                    "/Registro.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }   
    
     public void onClick$cl_edicion(){ 
        try {
            Window window = (Window) Executions.createComponents(
                    "/consultaCliente.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }   
     
     public void onClick$rs1(){ 
        try {
            Window window = (Window) Executions.createComponents(
                    "/Reservacion.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    } 
     
          public void onClick$rs2(){ 
        try {
            Window window = (Window) Executions.createComponents(
                    "/consuDECurso.zul", null, null);
            window.doModal();
        } catch (Exception eu) {

        }
    }
     
     
      
}
