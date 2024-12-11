package homebaking.h2Impl;

import homebaking.dao.UserDao;
import homebaking.exceptions.DAOException;
import homebaking.exceptions.ObjectoDuplicadoException;
import homebaking.model.User;
import resources.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoH2Impl implements UserDao {

    public void crearUser(User unUser) throws DAOException {
        String username = unUser.getUsername();
        String email = unUser.getEmail();
        String password = unUser.getPassword();

        Date d = new Date();

        Connection c = DBManager.connect();
        try {
//            Statement s = c.createStatement();
            PreparedStatement ps = c.prepareStatement("INSERT INTO usuarios (username, email, password) VALUES (?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

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

    public void borraUser(String username) throws DAOException {
        String sql = "DELETE FROM usuarios WHERE username = '" + username + "'";
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

    public void actualizaUser(User unUser) throws DAOException {
        String user = unUser.getUsername();
        String email = unUser.getEmail();
        String pass = unUser.getPassword();

        String sql = "UPDATE usuarios set email = '" + email + "', password = '" + pass + "' WHERE username = '" + user + "'";
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

    public List<User> listaTodosLosUsers() throws DAOException {
        List<User> resultado = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreUser = rs.getString("username");
                String mail = rs.getString("email");
                User u = new User(id, nombreUser, mail);
                resultado.add(u);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
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
        return resultado;
    }

    public User checkUser(String email, String password) throws DAOException {
        String sql = "SELECT * FROM usuarios WHERE email = '" + email + "' AND password = '" + password + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                Integer idUser = rs.getInt("id");
                String nombreUser = rs.getString("username");
                String mail = rs.getString("email");
                String contrasenia = rs.getString("password");
                User u = new User(idUser, nombreUser, mail, contrasenia);
                return u;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public User getUser(Integer id) throws DAOException {
        String sql = "SELECT * FROM usuarios WHERE id = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                Integer idUser = rs.getInt("id");
                String nombreUser = rs.getString("username");
                String mail = rs.getString("email");
                String contrasenia = rs.getString("password");
                User u = new User(idUser, nombreUser, contrasenia, mail);
                return u;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
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
