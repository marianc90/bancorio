package homebaking.ui.Cuenta;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Tarjeta;
import homebaking.model.User;
import homebaking.service.CuentaService;
import homebaking.ui.PanelManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablaCuentasPanel extends JPanel implements ActionListener {

    private JTable tablaCuentas;
    private CuentaTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonEditar;
    private JButton botonMovimientos;
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

        tablaCuentas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para copiar
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    Object value = target.getValueAt(row, column);
                    StringSelection stringSelection = new StringSelection(value.toString());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "Texto copiado: " + value.toString());
                }
            }
        });

        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(this);
        this.add(botonVolver);

        botonAgregar = new JButton("Crear Cuenta");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);

        botonEditar = new JButton("Actualizar Saldo +/-");
        botonEditar.addActionListener(this);
        this.add(botonEditar);

        botonBorrar = new JButton("Eliminar");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

        botonMovimientos = new JButton("Ver Movimientos");
        botonMovimientos.addActionListener(this);
        this.add(botonMovimientos);

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

        } else if (e.getSource() == botonMovimientos) {
            int filaSeleccionada = this.tablaCuentas.getSelectedRow();
            if (filaSeleccionada != -1) {
                Cuenta cuenta = this.modelo.getContenido().get(filaSeleccionada);
                this.panelManager.mostrarPantallaMovimCuentaPanel(cuenta);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta para ver sus movimientos.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarPantallaAnterior();

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Cuenta> listaTodasLasCuentas = s.listaTodasLasCuentas();
            modelo.setContenido(listaTodasLasCuentas);
            modelo.fireTableDataChanged();
            tablaCuentas.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaCuentas.getColumnModel().getColumn(1).setPreferredWidth(15);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaCuentas.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
            tablaCuentas.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de cuentas.");
        }
    }

    public void refrescarTabla(User u) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Cuenta> listaCuentasUser = s.listaCuentasUser(u);
            modelo.setContenido(listaCuentasUser);
            modelo.fireTableDataChanged();
            tablaCuentas.getColumnModel().getColumn(0).setPreferredWidth(100);
            tablaCuentas.getColumnModel().getColumn(1).setPreferredWidth(15);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaCuentas.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
            tablaCuentas.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de cuentas.");
        }
    }

}
