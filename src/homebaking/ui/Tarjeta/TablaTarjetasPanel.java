package homebaking.ui.Tarjeta;

import homebaking.exceptions.ServiceException;
import homebaking.model.Tarjeta;
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

public class TablaTarjetasPanel extends JPanel implements ActionListener {

    private JTable tablaTarjetas;
    private TarjetaTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonActualizarSaldo;
    private JButton botonEditarDisponible;
    private PanelManager panelManager;
    TarjetaService s = new TarjetaService();

    public TablaTarjetasPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new TarjetaTableModel();
        tablaTarjetas = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaTarjetas);
        this.add(scrollPaneParaTabla);

        tablaTarjetas.addMouseListener(new MouseAdapter() {
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

        botonAgregar = new JButton("Crear Tarjeta");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);

        botonActualizarSaldo = new JButton("Saldo +/-");
        botonActualizarSaldo.addActionListener(this);
        this.add(botonActualizarSaldo);

        botonEditarDisponible = new JButton("Límite");
        botonEditarDisponible.addActionListener(this);
        this.add(botonEditarDisponible);

        botonBorrar = new JButton("Eliminar");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonActualizarSaldo) {
            int filaSeleccionada = this.tablaTarjetas.getSelectedRow();

            if (filaSeleccionada != -1) {
                Tarjeta tarjetaSeleccionada = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaEdicionTarjetaPanel(tarjetaSeleccionada, "saldo");

            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta para modificar su saldo.");
            }

        } else if (e.getSource() == botonEditarDisponible) {
            int filaSeleccionada = this.tablaTarjetas.getSelectedRow();

            if (filaSeleccionada != -1) {
                Tarjeta tarjetaSeleccionada = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaEdicionTarjetaPanel(tarjetaSeleccionada, "disponible");

            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta para modificar su límite.");
            }
        } else if (e.getSource() == botonAgregar) {
            this.panelManager.mostrarPantallaAltaTarjetaPanel();

        } else if (e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaTarjetas.getSelectedRow();
            if (filaSeleccionada != -1) {
                Tarjeta tarjeta = this.modelo.getContenido().get(filaSeleccionada);
                try {
                    s.borrarTarjeta(tarjeta);
                    this.modelo.getContenido().remove(filaSeleccionada);
                    modelo.fireTableDataChanged();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(this, "LA TARJETA "+tarjeta.getNumero()+" NO PUDO SER BORRADA");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta para borrar.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarAdminPanel();

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Tarjeta> listaTodasLasTarjetas = s.listaTodasLasTarjetas();
            modelo.setContenido(listaTodasLasTarjetas);
            modelo.fireTableDataChanged();
            tablaTarjetas.getColumnModel().getColumn(0).setPreferredWidth(100);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaTarjetas.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de tarjetas.");
        }
    }

}
