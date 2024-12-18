package homebaking.ui.MovimientoUser;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Tarjeta;
import homebaking.service.CuentaService;
import homebaking.service.MovimientoService;
import homebaking.service.TarjetaService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;
import javax.swing.*;
import java.text.ParseException;
import java.util.List;

public class PantallaAltaMovimUserPanel extends AbstractPantallaAltaPanel {

    private String operacion = "";

    public PantallaAltaMovimUserPanel(PanelManager panelManager) throws ServiceException {

        super(panelManager);
    }

    @Override
    public void setCamposPanel() throws ServiceException {
        this.camposPanel = new CamposMovimUserPanel(panelManager);
        this.botonesPanel = new MovimUserBotoneraPanel(panelManager);
    }

    @Override
    public void ejecutarAccionOk() throws ServiceException, ParseException {

        CamposMovimUserPanel campos = (CamposMovimUserPanel) this.camposPanel;

        String descripcionStr = campos.getDescripcionTxt().getText();
        String montoStr = campos.getMontoTxt().getText();
        String tipo = null;
        Integer cuentaOrigen = null;
        Integer cuentaDestino = null;
        Long tarjeta = null;
        if (this.operacion.equals("TARJETA")) {
            tipo = "PAGO";
            String tarjetaStr = campos.getTarjetaTxt().getText();
            tarjeta = Long.parseLong(tarjetaStr);
            Object cuentaOrigenObj = campos.getCuentaOrigenComboBox().getSelectedItem();
            if (cuentaOrigenObj == null) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                cuentaOrigen = (Integer) cuentaOrigenObj;
            }
        } else if (this.operacion.equals("CUENTA")) {
            tipo = "TRANSFERENCIA";
            Object cuentaOrigenObj = campos.getCuentaOrigenComboBox().getSelectedItem();
            String cuentaDestinoStr = campos.getCuentaDestinoTxt().getText();
            if (cuentaOrigenObj == null || cuentaDestinoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                cuentaOrigen = (Integer) cuentaOrigenObj;
                cuentaDestino = Integer.parseInt(cuentaDestinoStr);
            }
        }

        if (tipo == null || descripcionStr.isEmpty() || montoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        double monto;
        try {
            monto = Double.valueOf(montoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.");
            return;
        }

        if (monto <= 0) {
            JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
            return;
        }

        if (this.operacion.equals("TARJETA")) {
            try {
                MovimientoService s = new MovimientoService();
                Cuenta co = null;
                Tarjeta t = null;
                CuentaService cuentaService = new CuentaService();
                TarjetaService tarjetaService = new TarjetaService();
                if (cuentaOrigen != null) {
                    co = cuentaService.checkCuenta(cuentaOrigen);
                }
                if (tarjeta != null) {
                    t = tarjetaService.checkTarjeta(tarjeta);
                }
                s.crearMovimiento(descripcionStr, monto, tipo, co, null, t);
                panelManager.mostrarPantallaUserTarjetaPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (this.operacion.equals("CUENTA")) {
            try {
                MovimientoService s = new MovimientoService();
                Cuenta co = null;
                Cuenta cd = null;
                CuentaService cuentaService = new CuentaService();
                if (cuentaOrigen != null) {
                    co = cuentaService.checkCuenta(cuentaOrigen);
                }
                if (cuentaDestino != null) {
                    cd = cuentaService.checkCuenta(cuentaDestino);
                }
                s.crearMovimiento(descripcionStr, monto, tipo, co, cd, null);
                panelManager.mostrarPantallaUserMovimCuentaPanel(co);
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }

    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarPantallaAnterior();
    }

    public void vaciarDatos(Tarjeta t) {
        this.operacion = "TARJETA";
        CamposMovimUserPanel camposTarjetaPanel = (CamposMovimUserPanel) this.camposPanel;
        camposTarjetaPanel.getDescripcionTxt().setText("Pago de Tarjeta");
        camposTarjetaPanel.getDescripcionTxt().setEnabled(false);
        camposTarjetaPanel.getMontoTxt().setText("");
        camposTarjetaPanel.getMontoTxt().setEnabled(true);
        CuentaService s = new CuentaService();
        try {
            List<Cuenta> listaCuentasUser = s.listaCuentasUser(panelManager.getUserLogueado());
            Integer[] cuentas = listaCuentasUser.stream().map(Cuenta::getNumero).toArray(Integer[]::new);
            camposTarjetaPanel.getCuentaOrigenComboBox().setModel(new DefaultComboBoxModel<>(cuentas));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        camposTarjetaPanel.getCuentaOrigenComboBox().setSelectedItem(t.getNumero());
        camposTarjetaPanel.getCuentaDestinoTxt().setText("");
        camposTarjetaPanel.getCuentaDestinoTxt().setEnabled(false);
        camposTarjetaPanel.getCuentaDestinoTxt().setVisible(false);
        camposTarjetaPanel.getCuentaDestinoLbl().setVisible(false);
        camposTarjetaPanel.getTarjetaTxt().setText(String.valueOf(t.getNumero()));
        camposTarjetaPanel.getTarjetaTxt().setEnabled(false);
        camposTarjetaPanel.getTarjetaTxt().setVisible(true);
        camposTarjetaPanel.getTarjetaLbl().setVisible(true);
    }

    public void vaciarDatos() {
        this.operacion = "CUENTA";
        CamposMovimUserPanel camposCuentaPanel = (CamposMovimUserPanel) this.camposPanel;
        camposCuentaPanel.getDescripcionTxt().setText("");
        camposCuentaPanel.getDescripcionTxt().setEnabled(true);
        camposCuentaPanel.getMontoTxt().setText("");
        camposCuentaPanel.getMontoTxt().setEnabled(true);
        CuentaService s = new CuentaService();
        try {
            List<Cuenta> listaCuentasUser = s.listaCuentasUser(panelManager.getUserLogueado());
            Integer[] cuentas = listaCuentasUser.stream().map(Cuenta::getNumero).toArray(Integer[]::new);
            camposCuentaPanel.getCuentaOrigenComboBox().setModel(new DefaultComboBoxModel<>(cuentas));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        camposCuentaPanel.getCuentaOrigenComboBox().setSelectedIndex(0);
        camposCuentaPanel.getCuentaDestinoTxt().setText("");
        camposCuentaPanel.getCuentaDestinoTxt().setEnabled(true);
        camposCuentaPanel.getCuentaDestinoLbl().setEnabled(true);
        camposCuentaPanel.getCuentaDestinoTxt().setVisible(true);
        camposCuentaPanel.getCuentaDestinoLbl().setVisible(true);
        camposCuentaPanel.getTarjetaTxt().setText("");
        camposCuentaPanel.getTarjetaTxt().setEnabled(false);
        camposCuentaPanel.getTarjetaLbl().setEnabled(false);
        camposCuentaPanel.getTarjetaTxt().setVisible(false);
        camposCuentaPanel.getTarjetaLbl().setVisible(false);
    }


}
