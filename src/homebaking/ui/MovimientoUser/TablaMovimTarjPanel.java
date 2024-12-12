package homebaking.ui.MovimientoUser;

import homebaking.exceptions.ServiceException;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;
import homebaking.service.MovimientoService;
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

public class TablaMovimTarjPanel extends JPanel implements ActionListener {

    private JTable tablaMovimientos;
    private MovimTarjTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private PanelManager panelManager;
    MovimientoService s = new MovimientoService();

    public TablaMovimTarjPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new MovimTarjTableModel();
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

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonVolver){
            panelManager.mostrarPantallaAnterior();

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
