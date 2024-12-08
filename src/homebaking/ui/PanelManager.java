package homebaking.ui;

import homebaking.model.Cuenta;
import homebaking.model.User;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private JFrame frame;
    private PantallaAltaUsuarioPanel pantallaAltaUsuarioPanel;
    private TablaUsuariosPanel tablaUsuariosPanel;
    private PantallaAltaCuentaPanel pantallaAltaCuentaPanel;
    private PantallaInicioPanel pantallaInicioPanel;
    private PantallaLoginPanel pantallaLoginPanel;
    private PantallaUserPanel pantallaUserPanel;
    private PantallaAdminPanel pantallaAdminPanel;
    private TablaCuentasPanel tablaCuentasPanel;
    private User userLogueado;

    public PanelManager() {
        // TODO Auto-generated constructor stub
    }

    public void armarManager() {
        frame = new JFrame("Home Banking");
        frame.setBounds(100, 100, 500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        pantallaAltaUsuarioPanel = new PantallaAltaUsuarioPanel(this);
        tablaUsuariosPanel = new TablaUsuariosPanel(this);
        pantallaAltaCuentaPanel = new PantallaAltaCuentaPanel(this);
        pantallaInicioPanel = new PantallaInicioPanel(this);
        pantallaLoginPanel = new PantallaLoginPanel(this);
        pantallaUserPanel = new PantallaUserPanel(this);
        pantallaAdminPanel = new PantallaAdminPanel(this);
        tablaCuentasPanel = new TablaCuentasPanel(this);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void mostrarInicioPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaInicioPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarLoginPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaLoginPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarUserPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaUserPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarAdminPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAdminPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaUsuarioPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaUsuarioPanel);
        pantallaAltaUsuarioPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }


    public void mostrarPantallaAdminUsuarioPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaUsuariosPanel);
        tablaUsuariosPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEDicionUsuarioPanel(User usuarioAEditar) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaUsuarioPanel);
        pantallaAltaUsuarioPanel.llenarDatos(usuarioAEditar);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaUsuarioPanel(User u) {
        frame.getContentPane().removeAll();
        if(u != null) {
            frame.getContentPane().add(pantallaAltaUsuarioPanel);
        }
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminCuentaPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaCuentasPanel);
        tablaCuentasPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaCuentaPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaCuentaPanel);
        pantallaAltaCuentaPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEdicionCuentaPanel(Cuenta cuentaAEditar) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaCuentaPanel);
        pantallaAltaCuentaPanel.llenarDatos(cuentaAEditar);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public boolean isSesionIniciada() {
        return userLogueado != null;
    }

    public void setUserLogueado(User user) {
        this.userLogueado = user;
    }

    public User getUserLogueado() {
        return userLogueado;
    }

    public boolean isUserAdmin() {
        return userLogueado.isAdmin();
    }
}

