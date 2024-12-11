package homebaking.ui.TarjetaUser;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
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

public class TablaTarjetasUserPanel extends JPanel implements ActionListener {

    private JTable tablaTarjetas;
    private TarjetaUserTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonActualizarSaldo;
    private JButton botonMovimientos;
    private PanelManager panelManager;
    TarjetaService s = new TarjetaService();

    public TablaTarjetasUserPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new TarjetaUserTableModel();
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

        botonActualizarSaldo = new JButton("Pagar Tarjeta");
        botonActualizarSaldo.addActionListener(this);
        this.add(botonActualizarSaldo);

        botonMovimientos = new JButton("Ver Movimientos");
        botonMovimientos.addActionListener(this);
        this.add(botonMovimientos);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonActualizarSaldo) {
            int filaSeleccionada = this.tablaTarjetas.getSelectedRow();

            if (filaSeleccionada != -1) {
                Tarjeta tarjetaSeleccionada = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaNuevoMovimUserPanel(tarjetaSeleccionada);

            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta para pagar su saldo.");
            }

        } else if (e.getSource() == botonMovimientos) {
            int filaSeleccionada = this.tablaTarjetas.getSelectedRow();
            if (filaSeleccionada != -1) {
                Tarjeta tarjeta = this.modelo.getContenido().get(filaSeleccionada);
                this.panelManager.mostrarPantallaUserMovimTarjetaPanel(tarjeta);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una tarjeta para ver sus movimientos.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarUserPanel();

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Tarjeta> listaTarjetasUser = s.listaTarjetasUser(panelManager.getUserLogueado());
            modelo.setContenido(listaTarjetasUser);
            modelo.fireTableDataChanged();
            tablaTarjetas.getColumnModel().getColumn(0).setPreferredWidth(100);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaTarjetas.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de tarjetas.");
        }
    }

}
