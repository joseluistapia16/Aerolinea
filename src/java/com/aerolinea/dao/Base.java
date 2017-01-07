package com.aerolinea.dao;

import com.aerolinea.dominio.Airbus;
import com.aerolinea.dominio.Horario;
import com.aerolinea.dominio.Ruta;
import com.aerolinea.dominio.Usuario;
import com.aerolinea.querys.compuestos.FiltroHorarioRuta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
public class Base {

    Connection connMY = null;
    Conectar con = new Conectar();
    ResultSet rs;

    public int eliminarClientes(String base, String cedula) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call eliminarUsuario(?,?) }");
            prcProcedimientoAlmacenado.setString(1, cedula);
            prcProcedimientoAlmacenado.setInt(2, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public void insertarUsuarios(String base, Usuario usuario) {
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call InsertarUsuario(?,?,?,?,?,?,?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setString(1, usuario.getNombrerol());
            prcProcedimientoAlmacenado.setString(2, usuario.getCedula());
            prcProcedimientoAlmacenado.setString(3, usuario.getNombreusuario());
            prcProcedimientoAlmacenado.setString(4, usuario.getApellido());
            prcProcedimientoAlmacenado.setString(5, usuario.getTelefono());
            prcProcedimientoAlmacenado.setString(6, usuario.getDireccion());
            prcProcedimientoAlmacenado.setString(7, usuario.getCorreo());
            prcProcedimientoAlmacenado.setString(8, usuario.getFechas());
            prcProcedimientoAlmacenado.setString(9, usuario.getUsuario());
            prcProcedimientoAlmacenado.setString(10, usuario.getClave());
            prcProcedimientoAlmacenado.setDate(11, usuario.getFecha());
            prcProcedimientoAlmacenado.executeUpdate();
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int actualizarUsuarios(String base, Usuario usuario, String clave) {
        int bandera=0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(true);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call actualizarUsuario(?,?,?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setString(1, clave);
            prcProcedimientoAlmacenado.setString(2, usuario.getNombreusuario());
            prcProcedimientoAlmacenado.setString(3, usuario.getApellido());
            prcProcedimientoAlmacenado.setString(4, usuario.getTelefono());
            prcProcedimientoAlmacenado.setString(5, usuario.getDireccion());
            prcProcedimientoAlmacenado.setString(6, usuario.getCorreo());
            prcProcedimientoAlmacenado.setInt(7, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();
        } catch (Exception e) {
//            try {
//               // connMY.rollback();
//                e.printStackTrace();
//            } catch (SQLException ex) {
//                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public int insertarRutas(String base, Ruta ruta) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call insertarRuta(?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setString(1, ruta.getRuta());
            prcProcedimientoAlmacenado.setString(2, ruta.getPartida());
            prcProcedimientoAlmacenado.setString(3, ruta.getDestino());
            prcProcedimientoAlmacenado.setDouble(4, ruta.getDuracion());
            prcProcedimientoAlmacenado.setDouble(5, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public int actualizarRutas(String base, Ruta ruta) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call actualizarRuta(?,?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setString(1, ruta.getRuta());
            prcProcedimientoAlmacenado.setString(2, ruta.getPartida());
            prcProcedimientoAlmacenado.setString(3, ruta.getDestino());
            prcProcedimientoAlmacenado.setDouble(4, ruta.getDuracion());
            prcProcedimientoAlmacenado.setString(5, ruta.getCodigo());
            prcProcedimientoAlmacenado.setInt(6, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    //
    public int eliminarRutas(String base, Ruta ruta) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call eliminarRuta(?,?) }");
            prcProcedimientoAlmacenado.setLong(1, ruta.getId_ruta());
            prcProcedimientoAlmacenado.setInt(2, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    //***********************
    public int insertarHorario(String base, Horario horario) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call insertarHorario(?,?,?) }");
            prcProcedimientoAlmacenado.setLong(1, horario.getId_ruta());
            prcProcedimientoAlmacenado.setTime(2, horario.getHora());
            prcProcedimientoAlmacenado.setInt(3, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }
// Actualizar

    public int actualizarHorario(String base, Horario horario) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call actualizarHorario(?,?,?,?) }");
            prcProcedimientoAlmacenado.setLong(1, horario.getId_ruta());
            prcProcedimientoAlmacenado.setTime(2, horario.getHora());
            prcProcedimientoAlmacenado.setLong(3, horario.getId_horario());
            prcProcedimientoAlmacenado.setInt(4, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public int eliminarHorario(String base, FiltroHorarioRuta objeto) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call eliminarHorario(?,?) }");
            prcProcedimientoAlmacenado.setLong(1, objeto.getId_horario());
            prcProcedimientoAlmacenado.setInt(2, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }
//***Airbus

    public int insertarAirbus(String base, Airbus airbus) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call insertarAirbus(?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setInt(1, airbus.getNumero_airbus());
            prcProcedimientoAlmacenado.setString(2, airbus.getCodigo());
            prcProcedimientoAlmacenado.setInt(3, airbus.getCapacidad());
            prcProcedimientoAlmacenado.setLong(4, airbus.getId_ruta());
            prcProcedimientoAlmacenado.setInt(5, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public int actualizarAirbus(String base, Airbus airbus) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call actualizarAirbus(?,?,?,?,?,?) }");
            prcProcedimientoAlmacenado.setInt(1, airbus.getNumero_airbus());
            prcProcedimientoAlmacenado.setString(2, airbus.getCodigo());
            prcProcedimientoAlmacenado.setInt(3, airbus.getCapacidad());
            prcProcedimientoAlmacenado.setLong(4, airbus.getId_ruta());
            prcProcedimientoAlmacenado.setLong(5, airbus.getId_airbus());
            prcProcedimientoAlmacenado.setInt(6, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();

        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

    public int eliminarAirbus(String base, Airbus objeto) {
        int bandera = 0;
        try {
            connMY = con.Conexion(base);
            connMY.setAutoCommit(false);
            CallableStatement prcProcedimientoAlmacenado = connMY.prepareCall(
                    "{ call eliminarAirbus(?,?) }");
            prcProcedimientoAlmacenado.setLong(1, objeto.getId_airbus());
            prcProcedimientoAlmacenado.setInt(2, 0);
            prcProcedimientoAlmacenado.registerOutParameter("bandera", Types.INTEGER);
            prcProcedimientoAlmacenado.execute();
            bandera = prcProcedimientoAlmacenado.getInt("bandera");
            connMY.commit();
        } catch (Exception e) {
            try {
                connMY.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                connMY.close();
            } catch (SQLException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bandera;
    }

}
