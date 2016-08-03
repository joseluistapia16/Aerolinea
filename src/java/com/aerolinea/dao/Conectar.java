package com.aerolinea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conectar {

    Connection Conect = null;

    public Connection Conexion(String base) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String str = "jdbc:mysql://localhost:3306/" + base + 
                    "?zeroDateTimeBehavior=convertToNull";
            Conect = DriverManager.getConnection(str, "root", "");
        } catch (Exception ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conect;
    }

}
