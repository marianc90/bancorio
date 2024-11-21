package homebaking.ui;

import homebaking.exceptions.ServiceException;
import homebaking.model.User;
import homebaking.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TablaUsuariosPanel extends JPanel implements ActionListener {

    private JTable tablaUsuarios;
    private UsuarioTableModel modelo;

    private JScrollPane scrollPaneParaTabla;
    private JButton botonVolver;
    private JButton botonAgregar;
    private JButton botonBorrar;
    private JButton botonEditar;
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
            panelManager.mostrarInicioPanel();

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
