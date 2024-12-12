package homebaking.ui.Cuenta;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.service.CuentaService;
import homebaking.service.MovimientoService;
import homebaking.service.UserService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;

public class PantallaAltaCuentaPanel extends AbstractPantallaAltaPanel {

    public PantallaAltaCuentaPanel(PanelManager panelManager) throws ServiceException {

        super(panelManager);
    }

    @Override
    public void setCamposPanel() throws ServiceException {
        this.camposPanel = new CamposCuentaPanel(panelManager);
        this.botonesPanel = new CuentaBotoneraPanel(panelManager);
    }

    @Override
    public void ejecutarAccionOk() throws ServiceException {

        CamposCuentaPanel campos = (CamposCuentaPanel) this.camposPanel;

        String tipo = campos.getTipoSeleccionado();
        String titularStr = campos.getTitularIDTxt().getText();

        if (tipo == null || titularStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        Integer titular;
        try {
            titular = Integer.valueOf(titularStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El titular debe ser un número válido.");
            return;
        }




        if (campos.isModoEdicion()) {
            double saldo = Double.parseDouble(campos.getSaldoTxt().getText());
            Integer numero = Integer.parseInt(campos.getNumeroTxt().getText());
            try {
                CuentaService s = new CuentaService();
                Cuenta c = s.checkCuenta(numero, tipo);
                String tipomov = "";
                //c.setSaldo(saldo);
                //s.actualizaSaldo(c);
                MovimientoService ms = new MovimientoService();
                if (saldo < 0) {
                    tipomov = "DEBITO";
                    saldo = saldo * -1;
                } else {
                    tipomov = "CREDITO";
                }
                ms.crearMovimiento("Ajuste de saldo", saldo, tipomov,c,null,null);
                panelManager.mostrarPantallaAdminCuentaPanel(c.getTitular());
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "LA CUENTA "+numero+" NO EXISTE");
            }
        }
        else {
            try {
                CuentaService s = new CuentaService();
                UserService us = new UserService();
                s.crearCuenta(tipo, titular);
                panelManager.mostrarPantallaAdminCuentaPanel(us.getUser(titular));
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "LA CUENTA YA EXISTE");
            }
        }

    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarPantallaAnterior();
    }

    public void llenarDatos(Cuenta c) {
        CamposCuentaPanel camposCuentaPanel = (CamposCuentaPanel) this.camposPanel;
        camposCuentaPanel.getTipoComboBox().setSelectedItem(c.getTipo().equals("CA") ? "Caja de Ahorro" : "Cuenta Corriente");
        camposCuentaPanel.getTipoComboBox().setEnabled(false);
        camposCuentaPanel.getTitularIDTxt().setText(String.valueOf(c.getTitular().getId()));
        camposCuentaPanel.getTitularIDTxt().setEnabled(false);
        camposCuentaPanel.getSaldoTxt().setText(String.valueOf(c.getSaldo()));
        camposCuentaPanel.getSaldoTxt().setEnabled(true);
        camposCuentaPanel.getNumeroTxt().setText(String.valueOf(c.getNumero()));
        camposCuentaPanel.getNumeroTxt().setEnabled(false);
        camposCuentaPanel.setModoEdicion(true);
    }

    public void vaciarDatos() {
        CamposCuentaPanel camposCuentaPanel = (CamposCuentaPanel) this.camposPanel;
        camposCuentaPanel.getTipoComboBox().setSelectedItem("Caja de Ahorro");
        camposCuentaPanel.getTipoComboBox().setEnabled(true);
        camposCuentaPanel.getTitularIDTxt().setText("");
        camposCuentaPanel.getTitularIDTxt().setEnabled(true);
        camposCuentaPanel.getSaldoTxt().setEnabled(false);
        camposCuentaPanel.getNumeroTxt().setText("");
        camposCuentaPanel.getNumeroTxt().setEnabled(false);
        camposCuentaPanel.setModoEdicion(false);
    }


}
