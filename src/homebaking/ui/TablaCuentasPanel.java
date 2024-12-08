package homebaking.ui;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.User;
import homebaking.service.CuentaService;
import homebaking.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TablaCuentasPanel extends JPanel implements ActionListener {

    private JTable tablaCuentas;
    private CuentaTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonEditar;
    private PanelManager panelManager;
    CuentaService s = new CuentaService();

    public TablaCuentasPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new CuentaTableModel();
        tablaCuentas = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaCuentas);
        this.add(scrollPaneParaTabla);

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(this);
        this.add(botonVolver);

        botonAgregar = new JButton("Crear Cuenta");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);

        botonEditar = new JButton("Actualizar Saldo");
        botonEditar.addActionListener(this);
        this.add(botonEditar);

        botonBorrar = new JButton("Eliminar");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEditar) {
            int filaSeleccionada = this.tablaCuentas.getSelectedRow();

            if (filaSeleccionada != -1) {
                Cuenta cuentaSeleccionada = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaEdicionCuentaPanel(cuentaSeleccionada);

            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una cuenta para actualizar su saldo.");
            }

        } else if (e.getSource() == botonAgregar) {
            this.panelManager.mostrarPantallaAltaCuentaPanel();

        } else if (e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaCuentas.getSelectedRow();
            if (filaSeleccionada != -1) {
                Cuenta cuenta = this.modelo.getContenido().get(filaSeleccionada);
                try {
                    s.borrarCuenta(cuenta);
                    this.modelo.getContenido().remove(filaSeleccionada);
                    modelo.fireTableDataChanged();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(this, "LA CUENTA "+cuenta.getNumero()+" NO PUDO SER BORRADA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una cuenta para borrar.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarAdminPanel();

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Cuenta> listaTodasLasCuentas = s.listaTodasLasCuentas();
            modelo.setContenido(listaTodasLasCuentas);
            modelo.fireTableDataChanged();
            tablaCuentas.getColumnModel().getColumn(0).setPreferredWidth(15);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaCuentas.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de cuentas.");
        }
    }

}
