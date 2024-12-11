package homebaking.h2Impl;

import homebaking.dao.CuentaDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.User;
import resources.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuentaDaoH2Impl implements CuentaDao {

    public void crearCuenta(Cuenta unaCuenta) throws DAOException {
        Integer numero = unaCuenta.getNumero();
        String tipo = unaCuenta.getTipo();
        Integer titular = unaCuenta.getTitular().getId();
        double saldo = unaCuenta.getSaldo();

        Date d = new Date();

        Connection c = DBManager.connect();
        try {
//            Statement s = c.createStatement();
            PreparedStatement ps = c.prepareStatement("INSERT INTO cuentas (numero, tipo, titular, saldo) VALUES (?, ?, ?, ?)");
            ps.setInt(1, numero);
            ps.setString(2, tipo);
            ps.setInt(3, titular);
            ps.setDouble(4, saldo);

//            String sql = "INSERT INTO usuarios (user, email, pass) VALUES ('" + user + "', '" + email + "', '" + pass + "')";
//            s.executeUpdate(sql);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                if(e.getErrorCode() == 23505) {
                    throw new DAOException();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
                throw new DAOException();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        }
    }

    public void borrarCuenta(Integer numero) throws DAOException {
        String sql = "DELETE FROM cuentas WHERE numero = '" + numero + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
//            throw new DAOException("ATENCION NO SE PUEDO CERRAR LA CXN");
        }
    }

    public void actualizaSaldo(Cuenta unaCuenta) throws DAOException {
        Integer numero = unaCuenta.getNumero();
        double saldo = unaCuenta.getSaldo();

        String sql = "UPDATE cuentas set saldo = '" + saldo + "' WHERE numero = '" + numero + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                throw new DAOException();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        }
    }

    public List<Cuenta> listaTodasLasCuentas() throws DAOException {
        List<Cuenta> resultado = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Integer titular = rs.getInt("titular");
                Integer numero = rs.getInt("numero");
                double saldo = rs.getDouble("saldo");
                Cuenta u = new Cuenta(tipo, titular, numero, saldo);
                resultado.add(u);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        }
        return resultado;
    }
    public List<Cuenta> listaCuentasUser(Integer id) throws DAOException {
        List<Cuenta> resultado = new ArrayList<>();
        String sql = "SELECT * FROM cuentas where titular = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Integer titular = rs.getInt("titular");
                Integer numero = rs.getInt("numero");
                double saldo = rs.getDouble("saldo");
                Cuenta u = new Cuenta(tipo, titular, numero, saldo);
                resultado.add(u);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        }
        return resultado;
    }

    public Cuenta checkCuenta(Integer numero, String tipo) throws DAOException {
        String sql = "SELECT * FROM cuentas WHERE numero = '" + numero + "' AND tipo = '" + tipo + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                Integer num = rs.getInt("numero");
                String tip = rs.getString("tipo");
                Integer titular = rs.getInt("titular");
                double saldo = rs.getDouble("saldo");
                Cuenta cuenta = new Cuenta(tip, titular, num, saldo);
                return cuenta;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ServiceException e) {
            throw new DAOException(e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public Cuenta checkCuenta(Integer numero) throws DAOException {
        String sql = "SELECT * FROM cuentas WHERE numero = '" + numero + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                Integer num = rs.getInt("numero");
                String tip = rs.getString("tipo");
                Integer titular = rs.getInt("titular");
                double saldo = rs.getDouble("saldo");
                Cuenta cuenta = new Cuenta(tip, titular, num, saldo);
                return cuenta;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ServiceException e) {
            throw new DAOException(e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

}
