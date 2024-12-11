package homebaking.h2Impl;

import homebaking.dao.TarjetaDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.model.Tarjeta;
import resources.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarjetaDaoH2Impl implements TarjetaDao {

    public void crearTarjeta(Tarjeta unaTarjeta) throws DAOException {
        String tipo = unaTarjeta.getTipo();
        Long numero = unaTarjeta.getNumero();
        Integer titular;
        try {
            titular = unaTarjeta.getTitular().getId();
        } catch (NullPointerException e) {
            throw new DAOException("El titular no existe", e);
        }
        double disponible = unaTarjeta.getDisponible();
        double saldo = unaTarjeta.getSaldo();

        Date d = new Date();

        Connection c = DBManager.connect();
        try {
//            Statement s = c.createStatement();
            PreparedStatement ps = c.prepareStatement("INSERT INTO tarjetas (numero, tipo, disponible, saldo, titular) VALUES (?, ?, ?, ?, ?)");
            ps.setLong(1, numero);
            ps.setString(2, tipo);
            ps.setDouble(3, disponible);
            ps.setDouble(4, saldo);
            ps.setInt(5, titular);

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

    public void borrarTarjeta(Long numero, String tipo) throws DAOException {
        String sql = "DELETE FROM tarjetas WHERE numero = '" + numero + "' AND tipo = '" + tipo + "'";
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

    public void actualizaSaldo(Tarjeta unaTarjeta) throws DAOException {
        Long numero = unaTarjeta.getNumero();
        String tipo = unaTarjeta.getTipo();
        double saldo = unaTarjeta.getSaldo();

        String sql = "UPDATE tarjetas set saldo = '" + saldo + "' WHERE numero = '" + numero + "' AND tipo = '" + tipo + "'";
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

    public void actualizaDisponible(Tarjeta unaTarjeta) throws DAOException {
        Long numero = unaTarjeta.getNumero();
        String tipo = unaTarjeta.getTipo();
        double disponible = unaTarjeta.getDisponible();

        String sql = "UPDATE tarjetas set disponible = '" + disponible + "' WHERE numero = '" + numero + "' AND tipo = '" + tipo + "'";
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

    public List<Tarjeta> listaTodasLasTarjetas() throws DAOException {
        List<Tarjeta> resultado = new ArrayList<>();
        String sql = "SELECT * FROM tarjetas";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Long numero = rs.getLong("numero");
                String tipo = rs.getString("tipo");
                double disponible = rs.getDouble("disponible");
                double saldo = rs.getDouble("saldo");
                Integer titular = rs.getInt("titular");
                Tarjeta u = new Tarjeta(tipo, titular, numero, disponible, saldo);
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

    public List<Tarjeta> listaTarjetasUser(Integer id) throws DAOException {
        List<Tarjeta> resultado = new ArrayList<>();
        String sql = "SELECT * FROM tarjetas WHERE titular = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Long numero = rs.getLong("numero");
                String tipo = rs.getString("tipo");
                double disponible = rs.getDouble("disponible");
                double saldo = rs.getDouble("saldo");
                Integer titular = rs.getInt("titular");
                Tarjeta u = new Tarjeta(tipo, titular, numero, disponible, saldo);
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

    public Tarjeta checkTarjeta(Long numero, String tipo) throws DAOException {
        String sql = "SELECT * FROM tarjetas WHERE numero = ? AND tipo = ?";
        return executeCheckTarjetaQuery(sql, numero, tipo);
    }

    public Tarjeta checkTarjeta(Long numero) throws DAOException {
        String sql = "SELECT * FROM tarjetas WHERE numero = ?";
        return executeCheckTarjetaQuery(sql, numero, null);
    }

    private Tarjeta executeCheckTarjetaQuery(String sql, Long numero, String tipo) throws DAOException {
        Connection c = DBManager.connect();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, numero);
            if (tipo != null) {
                ps.setString(2, tipo);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractTarjetaFromResultSet(rs);
            }
        } catch (SQLException | ServiceException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    private Tarjeta extractTarjetaFromResultSet(ResultSet rs) throws SQLException, ServiceException {
        Long num = rs.getLong("numero");
        String tip = rs.getString("tipo");
        Integer titular = rs.getInt("titular");
        double saldo = rs.getDouble("saldo");
        double disponible = rs.getDouble("disponible");
        return new Tarjeta(tip, titular, num, disponible, saldo);
    }

}
