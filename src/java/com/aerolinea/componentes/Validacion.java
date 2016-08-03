/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aerolinea.componentes;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TapiaSoftware
 */
public class Validacion {
     public static boolean Telefono(String cel) {
        boolean Cel;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(cel);

        if (matcher.matches()) {

            Cel = true;
        } else {
            //  System . out . println ( "Numero Invalido" ) ;
            Cel = false;
        }
        return Cel;
    }
    
    public static boolean validarVariables(String valor){
        int conta=0;
         for(int i=0;i<valor.length();i++){
             if(valor.charAt(i)==' '){
                 conta++;
             }
         }
         if(conta>0){
            return false; 
         }else{
             return true;
         }
        
    }
    
    
      public static boolean Cedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
//System.out.println("Ocurrio un error en el proceso de validadcion de cedula");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
//System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }
 

    public static boolean Email(String Mail) {

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(Mail);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }
    
}
