package com.reservacion.archivos;

 
import com.aerolinea.dominio.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {
      public static void crearArchivo(String archivo1, String msg) {
        try {
            File archivo = new File(archivo1 + ".clv");
            FileWriter escribir = new FileWriter(archivo, false);
            escribir.write(msg);
            escribir.close();
        } catch (IOException e) {
            System.out.println("Error al escribir");
        }
    }

    public static String leerArchivo(String archivo1) {
int op=0;
        String text = "";
        try {
            String texto = " ";
            FileReader lector = new FileReader(archivo1 + ".clv");
            BufferedReader contenido = new BufferedReader(lector);
            while ((texto = contenido.readLine()) != null) {
                if(!" ".equals(texto)){
                text = text +texto+"\n";  
                 op++;
                  System.out.println(" conta "+op);
                } 
            }
        } catch (IOException e) {
            System.out.println("Error al leer " );
        }
        String mm="";
        for (int i = 0; i < text.length()-1; i++) {
            mm= mm +text.charAt(i);
          // System.out.print(text.charAt(i)+ " - "+(i+1));
             
        }
        return mm;
    }

}
