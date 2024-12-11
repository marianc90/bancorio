package homebaking.ui.Login;

import homebaking.exceptions.ServiceException;
import homebaking.model.User;
import homebaking.service.UserService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;

public class PantallaLoginPanel extends AbstractPantallaAltaPanel {

    public PantallaLoginPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposLoginPanel(panelManager);
        this.botonesPanel = new LoginBotoneraPanel(panelManager);
    }


    @Override
    public void ejecutarAccionOk() {

        CamposLoginPanel campos = (CamposLoginPanel) this.camposPanel;
        String email = campos.getEmailTxt().getText();
        String password = campos.getPasswordTxt().getText();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try {
            UserService s = new UserService();
            User u = s.checkUser(email, password);
            if (u != null) {
                panelManager.setUserLogueado(u);
                if (u.isAdmin()) {
                    panelManager.mostrarAdminPanel();
                } else {
                    panelManager.mostrarUserPanel();
                }
            } else {
                JOptionPane.showMessageDialog(this, "LOGIN ERROR");
            }
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(this, "EL USUARIO NO EXISTE");
        }


    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarInicioPanel();
        vaciarDatos();
    }

    public void vaciarDatos() {
        CamposLoginPanel camposLoginPanel = (CamposLoginPanel) this.camposPanel;
        camposLoginPanel.getEmailTxt().setText("");
        camposLoginPanel.getPasswordTxt().setText("");
    }

}