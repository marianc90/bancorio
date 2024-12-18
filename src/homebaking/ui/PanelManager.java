package homebaking.ui;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;
import homebaking.model.User;
import homebaking.ui.Cuenta.PantallaAltaCuentaPanel;
import homebaking.ui.Cuenta.TablaCuentasPanel;
import homebaking.ui.CuentaUser.TablaCuentasUserPanel;
import homebaking.ui.Login.PantallaLoginPanel;
import homebaking.ui.Movimiento.PantallaAltaMovimientoPanel;
import homebaking.ui.Movimiento.TablaMovimientosPanel;
import homebaking.ui.MovimientoUser.PantallaAltaMovimUserPanel;
import homebaking.ui.MovimientoUser.TablaMovimCuentaPanel;
import homebaking.ui.MovimientoUser.TablaMovimTarjPanel;
import homebaking.ui.Tarjeta.PantallaAltaTarjetaPanel;
import homebaking.ui.Tarjeta.TablaTarjetasPanel;
import homebaking.ui.TarjetaUser.TablaTarjetasUserPanel;
import homebaking.ui.Usuario.PantallaAltaUsuarioPanel;
import homebaking.ui.Usuario.TablaUsuariosPanel;
import javax.swing.*;
import java.util.Stack;

public class PanelManager {

    private JFrame frame;
    private PantallaAltaUsuarioPanel pantallaAltaUsuarioPanel;
    private PantallaAltaCuentaPanel pantallaAltaCuentaPanel;
    private PantallaAltaTarjetaPanel pantallaAltaTarjetaPanel;
    private PantallaAltaMovimientoPanel pantallaAltaMovimientoPanel;
    private PantallaInicioPanel pantallaInicioPanel;
    private PantallaLoginPanel pantallaLoginPanel;
    private PantallaUserPanel pantallaUserPanel;
    private PantallaAdminPanel pantallaAdminPanel;
    private TablaUsuariosPanel tablaUsuariosPanel;
    private TablaCuentasPanel tablaCuentasPanel;
    private TablaTarjetasPanel tablaTarjetasPanel;
    private TablaMovimientosPanel tablaMovimientosPanel;
    private TablaCuentasUserPanel tablaCuentasUserPanel;
    private TablaMovimCuentaPanel tablaMovimCuentaPanel;
    private TablaTarjetasUserPanel tablaTarjetasUserPanel;
    private TablaMovimTarjPanel tablaMovimTarjPanel;
    private PantallaAltaMovimUserPanel pantallaAltaMovimUserPanel;
    private User userLogueado;
    private Stack<JPanel> pantallaAnterior = new Stack<>();

    public PanelManager() {
        // TODO Auto-generated constructor stub
    }

    public void armarManager() throws ServiceException {
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
        tablaTarjetasPanel = new TablaTarjetasPanel(this);
        pantallaAltaTarjetaPanel = new PantallaAltaTarjetaPanel(this);
        tablaMovimientosPanel = new TablaMovimientosPanel(this);
        pantallaAltaMovimientoPanel = new PantallaAltaMovimientoPanel(this);
        tablaCuentasUserPanel = new TablaCuentasUserPanel(this);
        tablaMovimCuentaPanel = new TablaMovimCuentaPanel(this);
        tablaTarjetasUserPanel = new TablaTarjetasUserPanel(this);
        tablaMovimTarjPanel = new TablaMovimTarjPanel(this);
        pantallaAltaMovimUserPanel = new PantallaAltaMovimUserPanel(this);

    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void mostrarInicioPanel() {
        if (frame.getContentPane().getComponentCount() > 0) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaInicioPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarLoginPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaLoginPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarUserPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaUserPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarAdminPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAdminPanel);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaUsuarioPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaUsuarioPanel);
        pantallaAltaUsuarioPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }


    public void mostrarPantallaAdminUsuarioPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaUsuariosPanel);
        tablaUsuariosPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEDicionUsuarioPanel(User usuarioAEditar) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaUsuarioPanel);
        pantallaAltaUsuarioPanel.llenarDatos(usuarioAEditar);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminCuentaPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaCuentasPanel);
        tablaCuentasPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaCuentaPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaCuentaPanel);
        pantallaAltaCuentaPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEdicionCuentaPanel(Cuenta cuentaAEditar) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaCuentaPanel);
        pantallaAltaCuentaPanel.llenarDatos(cuentaAEditar);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminTarjetaPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaTarjetasPanel);
        tablaTarjetasPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaTarjetaPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaTarjetaPanel);
        pantallaAltaTarjetaPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEdicionTarjetaPanel(Tarjeta tarjetaAEditar, String modo) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaTarjetaPanel);
        pantallaAltaTarjetaPanel.llenarDatos(tarjetaAEditar, modo);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminMovimientoPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaMovimientosPanel);
        tablaMovimientosPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaMovimCuentaPanel(Cuenta c) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaMovimientosPanel);
        tablaMovimientosPanel.refrescarTabla(c);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaMovimTarjetaPanel(Tarjeta t) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaMovimientosPanel);
        tablaMovimientosPanel.refrescarTabla(t);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAltaMovimientoPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaMovimientoPanel);
        pantallaAltaMovimientoPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaEdicionMovimientoPanel(Movimiento movimientoAEditar) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaMovimientoPanel);
        pantallaAltaMovimientoPanel.llenarDatos(movimientoAEditar);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminCuentaPanel(User u) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaCuentasPanel);
        tablaCuentasPanel.refrescarTabla(u);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaAdminTarjetaPanel(User u) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaTarjetasPanel);
        tablaTarjetasPanel.refrescarTabla(u);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaUserCuentaPanel(User u) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaCuentasUserPanel);
        tablaCuentasUserPanel.refrescarTabla(u);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaUserMovimCuentaPanel(Cuenta c) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaMovimCuentaPanel);
        tablaMovimCuentaPanel.refrescarTabla(c);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaUserTarjetaPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaTarjetasUserPanel);
        tablaTarjetasUserPanel.refrescarTabla();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaUserMovimTarjetaPanel(Tarjeta t) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(tablaMovimTarjPanel);
        tablaMovimTarjPanel.refrescarTabla(t);
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaNuevoMovimUserPanel() {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaMovimUserPanel);
        pantallaAltaMovimUserPanel.vaciarDatos();
        frame.getContentPane().validate();//RE-dispongo los elementos segun el layout
        frame.getContentPane().repaint();//RE-pinto los elementos dispuestos en el paso anterior

    }

    public void mostrarPantallaNuevoMovimUserPanel(Tarjeta t) {
        if (frame.getContentPane().getComponentCount() > 0 && !esPantallaAlta((JPanel) frame.getContentPane().getComponent(0))) {
            pantallaAnterior.push((JPanel) frame.getContentPane().getComponent(0));
        }
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pantallaAltaMovimUserPanel);
        pantallaAltaMovimUserPanel.vaciarDatos(t);
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

    public void mostrarPantallaAnterior() {
        while (!pantallaAnterior.isEmpty()) {
            JPanel panel = pantallaAnterior.pop();
            if (!esPantallaAlta(panel) && frame.getContentPane().getComponent(0) != panel) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panel);
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
                return;
            }
        }
    }

    private boolean esPantallaAlta(JPanel panel) {
        return panel instanceof PantallaAltaUsuarioPanel ||
                panel instanceof PantallaAltaCuentaPanel ||
                panel instanceof PantallaAltaTarjetaPanel ||
                panel instanceof PantallaAltaMovimientoPanel ||
                panel instanceof PantallaAltaMovimUserPanel;
    }
}

