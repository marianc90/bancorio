package homebaking.ui.CuentaUser;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
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

public class TablaCuentasUserPanel extends JPanel implements ActionListener {

    private JTable tablaCuentas;
    private CuentaUserTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonMovimientos;
    private PanelManager panelManager;
    CuentaService s = new CuentaService();

    public TablaCuentasUserPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new CuentaUserTableModel();
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

        botonMovimientos = new JButton("Ver Movimientos");
        botonMovimientos.addActionListener(this);
        this.add(botonMovimientos);

    }

    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == botonMovimientos) {
           int filaSeleccionada = this.tablaCuentas.getSelectedRow();
           if (filaSeleccionada != -1) {
               Cuenta cuenta = this.modelo.getContenido().get(filaSeleccionada);
               this.panelManager.mostrarPantallaUserMovimCuentaPanel(cuenta);
           } else {
               JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta para ver sus movimientos.");
           }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarUserPanel();

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<Cuenta> listaCuentasUser = s.listaCuentasUser(panelManager.getUserLogueado());
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
