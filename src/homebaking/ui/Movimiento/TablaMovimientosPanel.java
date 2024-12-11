package homebaking.ui.Movimiento;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;
import homebaking.service.MovimientoService;
import homebaking.service.TarjetaService;
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

public class TablaMovimientosPanel extends JPanel implements ActionListener {

    private JTable tablaMovimientos;
    private MovimientoTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonEditarMovimiento;
    private PanelManager panelManager;
    MovimientoService s = new MovimientoService();

    public TablaMovimientosPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new MovimientoTableModel();
        tablaMovimientos = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaMovimientos);
        this.add(scrollPaneParaTabla);

        tablaMovimientos.addMouseListener(new MouseAdapter() {
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

        botonAgregar = new JButton("Crear Movimiento");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);

        botonEditarMovimiento = new JButton("Editar Movimiento");
        botonEditarMovimiento.addActionListener(this);
        this.add(botonEditarMovimiento);

        botonBorrar = new JButton("Eliminar");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEditarMovimiento) {
            int filaSeleccionada = this.tablaMovimientos.getSelectedRow();

            if (filaSeleccionada != -1) {
                Movimiento MovimientoSeleccionado = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaEdicionMovimientoPanel(MovimientoSeleccionado);

            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un movimiento para modificarlo.");
            }

        } else if (e.getSource() == botonAgregar) {
            this.panelManager.mostrarPantallaAltaMovimientoPanel();

        } else if (e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaMovimientos.getSelectedRow();
            if (filaSeleccionada != -1) {
                Movimiento movimiento = this.modelo.getContenido().get(filaSeleccionada);
                try {
                    s.borrarMovimiento(movimiento);
                    this.modelo.getContenido().remove(filaSeleccionada);
                    modelo.fireTableDataChanged();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(this, "EL MOVIMIENTO "+movimiento.getId()+" NO PUDO SER BORRADO");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un movimiento para borrar.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarAdminPanel();

        }
    }

    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Movimiento> listaTodosLosMovimientos = s.listaTodosLosMovimientos();
            modelo.setContenido(listaTodosLosMovimientos);
            modelo.fireTableDataChanged();
            tablaMovimientos.getColumnModel().getColumn(0).setPreferredWidth(100);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaMovimientos.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de movimientos.");
        }
    }

    public void refrescarTabla(Cuenta c) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Movimiento> listaMovimCuenta = s.listaMovimCuenta(c);
            modelo.setContenido(listaMovimCuenta);
            modelo.fireTableDataChanged();
            tablaMovimientos.getColumnModel().getColumn(0).setPreferredWidth(100);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaMovimientos.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de movimientos.");
        }
    }

    public void refrescarTabla(Tarjeta t) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Movimiento> listaMovimTarjeta = s.listaMovimTarjeta(t);
            modelo.setContenido(listaMovimTarjeta);
            modelo.fireTableDataChanged();
            tablaMovimientos.getColumnModel().getColumn(0).setPreferredWidth(100);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaMovimientos.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de movimientos.");
        }
    }

}
