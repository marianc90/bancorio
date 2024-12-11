package homebaking.ui.Tarjeta;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Tarjeta;
import homebaking.service.CuentaService;
import homebaking.service.MovimientoService;
import homebaking.service.TarjetaService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;

public class PantallaAltaTarjetaPanel extends AbstractPantallaAltaPanel {

    public PantallaAltaTarjetaPanel(PanelManager panelManager) throws ServiceException {

        super(panelManager);
    }

    @Override
    public void setCamposPanel() throws ServiceException {
        this.camposPanel = new CamposTarjetaPanel(panelManager);
        this.botonesPanel = new TarjetaBotoneraPanel(panelManager);
    }

    @Override
    public void ejecutarAccionOk() throws ServiceException {

        CamposTarjetaPanel campos = (CamposTarjetaPanel) this.camposPanel;

        String tipo = campos.getTipoSeleccionado();
        String titularStr = campos.getTitularIDTxt().getText();
        String disponibleStr = campos.getDisponibleTxt().getText();

        if (tipo == null || titularStr.isEmpty() || disponibleStr == null) {
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

        double disponible;
        try {
            disponible = Double.parseDouble(disponibleStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El límite debe ser un número válido.");
            return;
        }

        if (campos.isModoEdicion().equals("saldo")) {
            double saldo = Double.parseDouble(campos.getSaldoTxt().getText());
            Long numero = Long.parseLong(campos.getNumeroTxt().getText());
            try {
                TarjetaService s = new TarjetaService();
                Tarjeta t = s.checkTarjeta(numero, tipo);
                //t.setSaldo(saldo);
                //s.actualizaSaldo(t);
                MovimientoService ms = new MovimientoService();
                ms.crearMovimiento("Ajuste de saldo", saldo, "AJUSTE",null,null,t);
                panelManager.mostrarPantallaAdminTarjetaPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "LA TARJETA "+numero+" NO EXISTE");
            }
        } else if (campos.isModoEdicion().equals("disponible")) {
            Long numero = Long.parseLong(campos.getNumeroTxt().getText());
            try {
                TarjetaService s = new TarjetaService();
                Tarjeta t = s.checkTarjeta(numero, tipo);
                t.setDisponible(disponible);
                s.actualizaDisponible(t);
                panelManager.mostrarPantallaAdminTarjetaPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, "LA TARJETA "+numero+" NO EXISTE");
            }

        } else {
            try {
                TarjetaService s = new TarjetaService();
                s.crearTarjeta(titular, tipo, disponible);
                panelManager.mostrarPantallaAdminTarjetaPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarPantallaAdminTarjetaPanel();
    }

    public void llenarDatos(Tarjeta t, String modo) {
        CamposTarjetaPanel camposTarjetaPanel = (CamposTarjetaPanel) this.camposPanel;
        camposTarjetaPanel.getTipoComboBox().setSelectedItem(t.getTipo().equals("Visa") ? "Visa" : "Mastercard");
        camposTarjetaPanel.getTipoComboBox().setEnabled(false);
        camposTarjetaPanel.getNumeroTxt().setText(String.valueOf(t.getNumero()));
        camposTarjetaPanel.getNumeroTxt().setEnabled(false);
        camposTarjetaPanel.getTitularIDTxt().setText(String.valueOf(t.getTitular().getId()));
        camposTarjetaPanel.getTitularIDTxt().setEnabled(false);
        if (modo.equals("saldo")) {
            camposTarjetaPanel.getSaldoTxt().setText(String.valueOf(t.getSaldo()));
            camposTarjetaPanel.getSaldoTxt().setEnabled(true);
            camposTarjetaPanel.setModoEdicion("saldo");
            camposTarjetaPanel.getDisponibleTxt().setText(String.valueOf(t.getDisponible()));
            camposTarjetaPanel.getDisponibleTxt().setEnabled(false);

        } else if (modo.equals("disponible")) {
            camposTarjetaPanel.getDisponibleTxt().setText(String.valueOf(t.getDisponible()));
            camposTarjetaPanel.getDisponibleTxt().setEnabled(true);
            camposTarjetaPanel.setModoEdicion("disponible");
            camposTarjetaPanel.getSaldoTxt().setText(String.valueOf(t.getSaldo()));
            camposTarjetaPanel.getSaldoTxt().setEnabled(false);
        }
    }

    public void vaciarDatos() {
        CamposTarjetaPanel camposTarjetaPanel = (CamposTarjetaPanel) this.camposPanel;
        camposTarjetaPanel.getTipoComboBox().setSelectedItem("Visa");
        camposTarjetaPanel.getTipoComboBox().setEnabled(true);
        camposTarjetaPanel.getNumeroTxt().setText("");
        camposTarjetaPanel.getNumeroTxt().setEnabled(false);
        camposTarjetaPanel.getTitularIDTxt().setText("");
        camposTarjetaPanel.getTitularIDTxt().setEnabled(true);
        camposTarjetaPanel.getSaldoTxt().setText("");
        camposTarjetaPanel.getSaldoTxt().setEnabled(false);
        camposTarjetaPanel.getDisponibleTxt().setText("");
        camposTarjetaPanel.getDisponibleTxt().setEnabled(true);
        camposTarjetaPanel.setModoEdicion("no");
    }


}
