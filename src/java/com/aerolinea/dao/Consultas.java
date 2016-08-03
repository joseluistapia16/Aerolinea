package com.aerolinea.dao;

import com.aerolinea.dominio.Airbus;
import com.aerolinea.dominio.Horario;
import com.aerolinea.dominio.Ruta;
import com.aerolinea.dominio.Usuario;
import com.aerolinea.querys.compuestos.FiltroAirbusRutas;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JOSE LUIS
 */
public class Consultas {

    Conectar con = new Conectar();
    Connection conect = null;
    ResultSet rs;
    java.sql.Statement st = null;
    String query;
    Connection connMY = null;

    public ArrayList<Usuario> listarUsuarios(String base) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call listarUsuarios() }");
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Usuario objeto = new Usuario(
                        BigInteger.valueOf(rs.getLong("id_usuario")),
                        rs.getString("nombre_rol"), rs.getString("cedula"), rs.getString("nombre_usuario"),
                        rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"),
                        rs.getString("fechas"), rs.getString("usuario"), rs.getString("clave"),
                        rs.getDate("fecha"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
/// filtrar 

    public ArrayList<Usuario> filtrarUsuarios(String base, String usuario, String clave) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroUsuarios(?,?) }");
            prcProcedimientoAlmacenado.setString(1, usuario);
            prcProcedimientoAlmacenado.setString(2, clave);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Usuario objeto = new Usuario(
                        BigInteger.valueOf(rs.getLong("id_usuario")),
                        rs.getString("nombre_rol"), rs.getString("cedula"), rs.getString("nombre_usuario"),
                        rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"),
                        rs.getString("fechas"), rs.getString("usuario"), rs.getString("clave"),
                        rs.getDate("fecha"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<Usuario> filtrarClientes(String base, String Cedula, int num) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroClientes(?,?) }");
            prcProcedimientoAlmacenado.setString(1, Cedula);
             prcProcedimientoAlmacenado.setInt(2 ,num);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Usuario objeto = new Usuario(
                        BigInteger.valueOf(rs.getLong("id_usuario")),
                        rs.getString("nombre_rol"), rs.getString("cedula"), rs.getString("nombre_usuario"),
                        rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"),
                        rs.getString("fechas"), rs.getString("usuario"), rs.getString("clave"),
                        rs.getDate("fecha"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    // Flitrar Rutas
    public ArrayList<Ruta> filtrarRutas(String base, String partida, String destino) {
        ArrayList<Ruta> lista = new ArrayList<Ruta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroRutas(?,?) }");
            prcProcedimientoAlmacenado.setString(1, partida);
            prcProcedimientoAlmacenado.setString(2, destino);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Ruta objeto = new Ruta(
                         rs.getLong("id_ruta"),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getDouble("duracion"), rs.getString("codigo"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    ///***
    public ArrayList<Ruta> filtrarRutas1(String base, String rutaD, String partida, String destino) {
        ArrayList<Ruta> lista = new ArrayList<Ruta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroRutas1(?,?,?) }");
            prcProcedimientoAlmacenado.setString(1, rutaD);
            prcProcedimientoAlmacenado.setString(2, partida);
            prcProcedimientoAlmacenado.setString(3, destino);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Ruta objeto = new Ruta(
                         rs.getLong("id_ruta"),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getDouble("duracion"), rs.getString("codigo"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<Ruta> listarRutas(String base) {
        ArrayList<Ruta> lista = new ArrayList<Ruta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call listarRutas() }");
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Ruta objeto = new Ruta(
                        rs.getLong("id_ruta"),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getDouble("duracion"), rs.getString("codigo"));
                lista.add(objeto);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

///*******************************************************    
    public ArrayList<Usuario> ConsultaUsuario(String base, String query) {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            conect = con.Conexion(base);
            st = conect.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                Usuario objeto = new Usuario(
                        BigInteger.valueOf(rs.getLong("id_usuario")),rs.getString("nombre_rol"),
                        rs.getString("cedula"),rs.getString("nombre_usuario"),
                        rs.getString("apellido"),
                        rs.getString("telefono"), rs.getString("direccion"), 
                        rs.getString("correo"),rs.getString("fechas"), rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getDate("fecha"));
                lista.add(objeto);
            }
            conect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    ///***********************************
    public ArrayList<Horario> filtrarHorario(String base, Horario objeto) {
        ArrayList<Horario> lista = new ArrayList<Horario>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroHorario(?,?) }");
            prcProcedimientoAlmacenado.setLong(1, objeto.getId_ruta());
            prcProcedimientoAlmacenado.setTime(2, objeto.getHora());
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Horario objeto1 = new Horario(
                        rs.getLong("id_horario"), rs.getLong("id_ruta"),
                        rs.getTime("hora"));

                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
//************

    public ArrayList<FiltroHorarioRuta> filtrarHorarioRuta(String base, String salida, String destino) {
        ArrayList<FiltroHorarioRuta> lista = new ArrayList<FiltroHorarioRuta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroHorario1P(?,?) }");
            prcProcedimientoAlmacenado.setString(1, salida);
            prcProcedimientoAlmacenado.setString(2, destino);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                FiltroHorarioRuta objeto1 = new FiltroHorarioRuta(
                        rs.getLong("id_ruta"), Long.valueOf(rs.getInt("id_horario")),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getTime("hora"));

                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    //*****
    public ArrayList<FiltroHorarioRuta> filtrarHorarioRuta(String base) {
        ArrayList<FiltroHorarioRuta> lista = new ArrayList<FiltroHorarioRuta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroHorarioRuta() }");
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                FiltroHorarioRuta objeto1 = new FiltroHorarioRuta(
                        rs.getLong("id_ruta"), Long.valueOf(rs.getInt("id_horario")),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getTime("hora"));

                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<FiltroHorarioRuta> filtrarHorarioXRuta(String base, Long id_ruta) {
        ArrayList<FiltroHorarioRuta> lista = new ArrayList<FiltroHorarioRuta>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call filtroHorarioXRuta(?) }");
            prcProcedimientoAlmacenado.setLong(1, id_ruta);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                FiltroHorarioRuta objeto1 = new FiltroHorarioRuta(
                        rs.getLong("id_ruta"), Long.valueOf(rs.getInt("id_horario")),
                        rs.getString("ruta"), rs.getString("partida"), rs.getString("destino"),
                        rs.getTime("hora"));

                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
/// airbus

    public ArrayList<Airbus> listarAirbus(String base, Long id_airbus,Long id_ruta, Integer numero, Integer valor) {
        ArrayList<Airbus> lista = new ArrayList<Airbus>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call listarAirbus(?,?,?,?) }");
            prcProcedimientoAlmacenado.setLong(1, id_airbus);
            prcProcedimientoAlmacenado.setLong(2, id_ruta);
            prcProcedimientoAlmacenado.setInt(3, numero);
            prcProcedimientoAlmacenado.setInt(4, valor);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                Airbus objeto1 = new Airbus(
                        rs.getLong("id_airbus"),
                        rs.getInt("numero_airbus"),
                        rs.getString("codigo"),
                        rs.getInt("capacidad"),
                        rs.getLong("id_ruta")
                );
       System.out.println(" vemos "+rs.getInt("numero_airbus")+" "+rs.getLong("id_ruta")+" "+
               rs.getString("codigo"));
                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public ArrayList<FiltroAirbusRutas> listarAirbusRuta(String base, Integer numero, Integer valor) {
        ArrayList<FiltroAirbusRutas> lista = new ArrayList<FiltroAirbusRutas>();
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call listarAirbusRuta(?,?) }");
            prcProcedimientoAlmacenado.setInt(1, numero);
            prcProcedimientoAlmacenado.setInt(2, valor);
            prcProcedimientoAlmacenado.execute();
            rs = prcProcedimientoAlmacenado.getResultSet();
            while (rs.next()) {
                FiltroAirbusRutas objeto1 = new FiltroAirbusRutas(
                        rs.getLong("id_airbus"),
                        rs.getString("codigo"),
                        rs.getInt("capacidad"),
                        rs.getInt("numero_airbus"),
                        rs.getLong("id_ruta"),
                        rs.getString("ruta"),
                        rs.getString("partida"),
                        rs.getString("Destino")
                );

                lista.add(objeto1);
            }
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

}
