package homebaking.ui.Usuario;

import homebaking.exceptions.ServiceException;
import homebaking.model.User;
import homebaking.service.UserService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;

public class PantallaAltaUsuarioPanel extends AbstractPantallaAltaPanel {

    public PantallaAltaUsuarioPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposUsuarioPanel(panelManager);
        this.botonesPanel = new UsuarioBotoneraPanel(panelManager);
    }


    @Override
    public void ejecutarAccionOk() {

        CamposUsuarioPanel campos = (CamposUsuarioPanel) this.camposPanel;
        String user = campos.getUserTxt().getText();
        String email = campos.getEmailTxt().getText();
        String password = campos.getPasswordTxt().getText();

        if (user.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        User u = new User();
        u.setUsername(user);
        u.setEmail(email);
        u.setPassword(password);

        if (campos.isModoEdicion()) {
            try {
                UserService s = new UserService();
                s.actualizaUser(u);
                panelManager.mostrarPantallaAdminUsuarioPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "EL USUARIO "+user+" NO EXISTE");
            }
        }
        else {
            try {
                UserService s = new UserService();
                s.crearUser(u);
                panelManager.mostrarPantallaAdminUsuarioPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "EL USUARIO "+user+" YA EXISTE");
            }
        }


    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarPantallaAdminUsuarioPanel();
    }

    public void llenarDatos(User u) {
        CamposUsuarioPanel camposUsuarioPanel = (CamposUsuarioPanel) this.camposPanel;
        camposUsuarioPanel.getUserTxt().setText(u.getUsername());
        camposUsuarioPanel.getUserTxt().setEnabled(false);
        camposUsuarioPanel.getEmailTxt().setText(String.valueOf(u.getEmail()));
        camposUsuarioPanel.getPasswordTxt().setText("");
        camposUsuarioPanel.setModoEdicion(true);
    }

    public void vaciarDatos() {
        CamposUsuarioPanel camposUsuarioPanel = (CamposUsuarioPanel) this.camposPanel;
        camposUsuarioPanel.getUserTxt().setText("");
        camposUsuarioPanel.getUserTxt().setEnabled(true);
        camposUsuarioPanel.getEmailTxt().setText("");
        camposUsuarioPanel.getPasswordTxt().setText("");
        camposUsuarioPanel.setModoEdicion(false);
    }

}