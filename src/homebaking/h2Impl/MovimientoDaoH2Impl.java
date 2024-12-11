package homebaking.h2Impl;

import homebaking.dao.CuentaDao;
import homebaking.dao.MovimientoDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;
import resources.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoDaoH2Impl implements MovimientoDao {

    public void crearMovimiento(Movimiento unMovimiento) throws DAOException {
        Long id = unMovimiento.getId();
        Date fecha = unMovimiento.getFecha();
        String descripcion = unMovimiento.getDescripcion();
        double monto = unMovimiento.getMonto();
        String tipo = unMovimiento.getTipo();
        Connection c = DBManager.connect();
        String sql;
        try {
            if (tipo.equals("TRANSFERENCIA")) {
                sql = "INSERT INTO movimientos (id, fecha, descripcion, monto, tipo, cuentaOrigen, cuentaDestino) VALUES (?, ?, ?, ?, ?, ?, ?)";
            } else if (tipo.equals("CREDITO") || tipo.equals("DEBITO")) {
                sql = "INSERT INTO movimientos (id, fecha, descripcion, monto, tipo, cuentaOrigen) VALUES (?, ?, ?, ?, ?, ?)";
            } else if (tipo.equals("CONSUMO") || tipo.equals("PAGO")) {
                sql = "INSERT INTO movimientos (id, fecha, descripcion, monto, tipo, tarjeta) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                throw new DAOException("Tipo de movimiento no soportado");
            }
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setDate(2, new java.sql.Date(fecha.getTime()));
            ps.setString(3, descripcion);
            ps.setDouble(4, monto);
            ps.setString(5, tipo);
            if (tipo.equals("TRANSFERENCIA")) {
                if (unMovimiento.getCuentaOrigen() == null || unMovimiento.getCuentaDestino() == null) {
                    throw new DAOException("Cuenta origen o destino inválida para transferencia");
                }
                ps.setInt(6, unMovimiento.getCuentaOrigen().getNumero());
                ps.setInt(7, unMovimiento.getCuentaDestino().getNumero());
            } else if (tipo.equals("CREDITO") || tipo.equals("DEBITO")) {
                if (unMovimiento.getCuentaOrigen() == null) {
                    throw new DAOException("Cuenta origen inválida para crédito o débito");
                }
                ps.setInt(6, unMovimiento.getCuentaOrigen().getNumero());
            } else if (tipo.equals("CONSUMO") || tipo.equals("PAGO")) {
                if (unMovimiento.getTarjeta() == null) {
                    throw new DAOException("Tarjeta inválida para consumo o pago");
                }
                ps.setLong(6, unMovimiento.getTarjeta().getNumero());
            }
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            if (e.getErrorCode() == 23505) {
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
        }
    }

    public void borrarMovimiento(Long id) throws DAOException {
        String sql = "DELETE FROM movimientos WHERE id = '" + id + "'";
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

    public void editarMovimiento(Movimiento unMovimiento) throws DAOException {
        Long id = unMovimiento.getId();
        Date fecha = unMovimiento.getFecha();
        String descripcion = unMovimiento.getDescripcion();
        String tipo = unMovimiento.getTipo();

        String sql = "UPDATE movimientos SET fecha = ?, descripcion = ?, cuentaOrigen = ?, cuentaDestino = ?, tarjeta = ? WHERE id = ?";
        Connection c = DBManager.connect();
        try {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fecha.getTime()));
            ps.setString(2, descripcion);

            if (tipo.equals("TRANSFERENCIA")) {
                if (unMovimiento.getCuentaOrigen() == null || unMovimiento.getCuentaDestino() == null) {
                    throw new DAOException("Cuenta origen o destino inválida");
                }
                ps.setInt(3, unMovimiento.getCuentaOrigen().getNumero());
                ps.setInt(4, unMovimiento.getCuentaDestino().getNumero());
                ps.setNull(5, Types.BIGINT);
            } else if (tipo.equals("CREDITO") || tipo.equals("DEBITO")) {
                if (unMovimiento.getCuentaOrigen() == null) {
                    throw new DAOException("Cuenta origen inválida para crédito o débito");
                }
                ps.setInt(3, unMovimiento.getCuentaOrigen().getNumero());
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.BIGINT);
            } else if (tipo.equals("CONSUMO") || tipo.equals("PAGO")) {
                if (unMovimiento.getTarjeta() == null) {
                    throw new DAOException("Tarjeta inválida para consumo o pago");
                }
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.INTEGER);
                ps.setLong(5, unMovimiento.getTarjeta().getNumero());
            } else {
                throw new DAOException("Tipo de movimiento no soportado");
            }

            ps.setLong(6, id);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new DAOException();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DAOException();
            }
        }
    }

    public List<Movimiento> listaTodosLosMovimientos() throws DAOException {
        List<Movimiento> resultado = new ArrayList<>();
        String sql = "SELECT * FROM movimientos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                Long id = rs.getLong("id");
                Date fecha = rs.getDate("fecha");
                String descripcion = rs.getString("descripcion");
                double monto = rs.getDouble("monto");
                String tipo = rs.getString("tipo");
                Integer cuentaOrigen = rs.getInt("cuentaOrigen");
                Integer cuentaDestino = rs.getInt("cuentaDestino");
                Long tarjeta = rs.getLong("tarjeta");
                Movimiento m = new Movimiento(id, fecha, descripcion, monto, tipo, cuentaOrigen, cuentaDestino, tarjeta);
                resultado.add(m);

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

    public Movimiento checkMovimiento(Long id) throws DAOException {
        String sql = "SELECT * FROM movimientos WHERE id = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                Long id1 = rs.getLong("id");
                Date fecha = rs.getDate("fecha");
                String descripcion = rs.getString("descripcion");
                double monto = rs.getDouble("monto");
                String tip = rs.getString("tipo");
                Integer cuentaOrigen = rs.getInt("cuentaOrigen");
                Integer cuentaDestino = rs.getInt("cuentaDestino");
                Long tarjeta = rs.getLong("tarjeta");
                Movimiento m = new Movimiento(id1, fecha, descripcion, monto, tip, cuentaOrigen, cuentaDestino, tarjeta);
                return m;
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
