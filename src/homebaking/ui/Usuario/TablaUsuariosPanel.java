package homebaking.ui.Usuario;

import homebaking.exceptions.ServiceException;
import homebaking.model.User;
import homebaking.service.UserService;
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

public class TablaUsuariosPanel extends JPanel implements ActionListener {

    private JTable tablaUsuarios;
    private UsuarioTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonEditar;
    private JButton botonVerCuentas;
    private JButton botonVerTarjetas;
    private PanelManager panelManager;
    UserService s = new UserService();

    public TablaUsuariosPanel(PanelManager panelManager) {
        super();
        this.panelManager = panelManager;
        armarPanel();
    }

    private void armarPanel() {
        this.setLayout(new FlowLayout());

        modelo = new UsuarioTableModel();
        tablaUsuarios = new JTable(modelo);
        scrollPaneParaTabla = new JScrollPane(tablaUsuarios);
        this.add(scrollPaneParaTabla);

        tablaUsuarios.addMouseListener(new MouseAdapter() {
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

        botonAgregar = new JButton("Crear Usuario");
        botonAgregar.addActionListener(this);
        this.add(botonAgregar);

        botonEditar = new JButton("Editar Usuario");
        botonEditar.addActionListener(this);
        this.add(botonEditar);

        botonBorrar = new JButton("Borrar");
        botonBorrar.addActionListener(this);
        this.add(botonBorrar);

        botonVerCuentas = new JButton("Ver Cuentas");
        botonVerCuentas.addActionListener(this);
        this.add(botonVerCuentas);

        botonVerTarjetas = new JButton("Ver Tarjetas");
        botonVerTarjetas.addActionListener(this);
        this.add(botonVerTarjetas);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEditar) {
            int filaSeleccionada = this.tablaUsuarios.getSelectedRow();

            if (filaSeleccionada != -1) {
                User usuarioSeleccionado = this.modelo.getContenido().get(filaSeleccionada);

                this.panelManager.mostrarPantallaEDicionUsuarioPanel(usuarioSeleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para editar.");
            }

        } else if (e.getSource() == botonAgregar) {
            this.panelManager.mostrarPantallaAltaUsuarioPanel();

        } else if (e.getSource() == botonBorrar) {
            int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
            if (filaSeleccionada != -1) {
                User usuario = this.modelo.getContenido().get(filaSeleccionada);
                try {
                    s.borrarUser(usuario);
                    this.modelo.getContenido().remove(filaSeleccionada);
                    modelo.fireTableDataChanged();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(this, "EL USUARIO "+usuario.getUsername()+" NO PUDO SER BORRADO");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para borrar.");
            }

        } else if (e.getSource() == botonVolver){
            panelManager.mostrarPantallaAnterior();

        } else if (e.getSource() == botonVerCuentas){
            int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
            if (filaSeleccionada != -1) {
                User usuario = this.modelo.getContenido().get(filaSeleccionada);
                panelManager.mostrarPantallaAdminCuentaPanel(usuario);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para ver sus cuentas.");
            }

        } else if (e.getSource() == botonVerTarjetas){
            int filaSeleccionada = this.tablaUsuarios.getSelectedRow();
            if (filaSeleccionada != -1) {
                User usuario = this.modelo.getContenido().get(filaSeleccionada);
                panelManager.mostrarPantallaAdminTarjetaPanel(usuario);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para ver sus tarjetas.");
            }

        }
    }
    public void refrescarTabla() {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        try {
            List<User> listaTodosLosUsuarios = s.listaTodosLosUsers();
            modelo.setContenido(listaTodosLosUsuarios);
            modelo.fireTableDataChanged();
            tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(15);
            leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            tablaUsuarios.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener lista de usuarios.");
        }
    }

}
