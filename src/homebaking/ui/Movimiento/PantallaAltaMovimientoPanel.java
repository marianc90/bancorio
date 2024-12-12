package homebaking.ui.Movimiento;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.model.Movimiento;
import homebaking.model.Tarjeta;
import homebaking.service.CuentaService;
import homebaking.service.MovimientoService;
import homebaking.service.TarjetaService;
import homebaking.ui.AbstractPantallaAltaPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PantallaAltaMovimientoPanel extends AbstractPantallaAltaPanel {

    public PantallaAltaMovimientoPanel(PanelManager panelManager) throws ServiceException {

        super(panelManager);
    }

    @Override
    public void setCamposPanel() throws ServiceException {
        this.camposPanel = new CamposMovimientoPanel(panelManager);
        this.botonesPanel = new MovimientoBotoneraPanel(panelManager);
    }

    @Override
    public void ejecutarAccionOk() throws ServiceException, ParseException {

        CamposMovimientoPanel campos = (CamposMovimientoPanel) this.camposPanel;

        String descripcionStr = campos.getDescripcionTxt().getText();
        String montoStr = campos.getMontoTxt().getText();
        String tipo = campos.getTipoSeleccionado();
        Integer cuentaOrigen = null;
        Integer cuentaDestino = null;
        Long tarjeta = null;
        if (tipo.equals("CONSUMO") || tipo.equals("AJUSTE")) {
            String tarjetaStr = campos.getTarjetaTxt().getText();
            if (tarjetaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                tarjeta = Long.parseLong(tarjetaStr);
            }
        } else if (tipo.equals("TRANSFERENCIA")) {
            String cuentaOrigenStr = campos.getCuentaOrigenTxt().getText();
            String cuentaDestinoStr = campos.getCuentaDestinoTxt().getText();
            if (cuentaOrigenStr.isEmpty() || cuentaDestinoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                cuentaOrigen = Integer.parseInt(cuentaOrigenStr);
                cuentaDestino = Integer.parseInt(cuentaDestinoStr);
            }
        } else if (tipo.equals("CREDITO") || tipo.equals("DEBITO")) {
            String cuentaOrigenStr = campos.getCuentaOrigenTxt().getText();
            if (cuentaOrigenStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                cuentaOrigen = Integer.parseInt(cuentaOrigenStr);
            }

        } else if (tipo.equals("PAGO")) {
            String tarjetaStr = campos.getTarjetaTxt().getText();
            String cuentaOrigenStr = campos.getCuentaOrigenTxt().getText();
            if (cuentaOrigenStr.isEmpty() || tarjetaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            } else {
                cuentaOrigen = Integer.parseInt(cuentaOrigenStr);
                tarjeta = Long.parseLong(tarjetaStr);
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

        if (campos.isModoEdicion().equals("si")) {
            Long id = Long.parseLong(campos.getIdTxt().getText());
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(campos.getFechaTxt().getText());

            try {
                MovimientoService s = new MovimientoService();
                Movimiento m = s.checkMovimiento(id);
                m.setFecha(fecha);
                m.setDescripcion(descripcionStr);
                s.editarMovimiento(m);
                panelManager.mostrarPantallaAdminMovimientoPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else {

            try {
                MovimientoService s = new MovimientoService();
                Cuenta co = null;
                Cuenta cd = null;
                Tarjeta t = null;
                CuentaService cuentaService = new CuentaService();
                TarjetaService tarjetaService = new TarjetaService();
                if (cuentaOrigen != null) {
                    co = cuentaService.checkCuenta(cuentaOrigen);;
                    if (cuentaDestino != null) {
                        cd = cuentaService.checkCuenta(cuentaDestino);
                    }        }
                if (tarjeta != null) {
                    t = tarjetaService.checkTarjeta(tarjeta);
                }
                s.crearMovimiento(descripcionStr, monto, tipo, co, cd, t);
                panelManager.mostrarPantallaAdminMovimientoPanel();
            } catch (ServiceException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }

    @Override
    public void ejecutarAccionCancel() {
        panelManager.mostrarPantallaAnterior();
    }

    public void llenarDatos(Movimiento m) {
        CamposMovimientoPanel camposMovimientoPanel = (CamposMovimientoPanel) this.camposPanel;
        camposMovimientoPanel.getIdTxt().setText(String.valueOf(m.getId()));
        camposMovimientoPanel.getIdTxt().setEnabled(false);
        camposMovimientoPanel.getFechaTxt().setText(new SimpleDateFormat("dd/MM/yyyy").format(m.getFecha()));
        camposMovimientoPanel.getFechaTxt().setEnabled(true);
        camposMovimientoPanel.getDescripcionTxt().setText(m.getDescripcion());
        camposMovimientoPanel.getDescripcionTxt().setEnabled(true);
        //if (camposMovimientoPanel.getTiposModel().getIndexOf("Pago") == -1) {
       //     camposMovimientoPanel.getTiposModel().addElement("Pago");
      //  }
        camposMovimientoPanel.getTipoComboBox().setSelectedItem(m.getTipo().equals("TRANSFERENCIA") ? "Transferencia" : m.getTipo().equals("DEBITO") ? "Débito" : m.getTipo().equals("CREDITO") ? "Crédito" : m.getTipo().equals("CONSUMO") ? "Consumo TC" : m.getTipo().equals("AJUSTE") ? "Ajuste TC" : m.getTipo().equals("PAGO") ? "Pago" : "Otra");
        camposMovimientoPanel.getTipoComboBox().setEnabled(false);
        camposMovimientoPanel.getMontoTxt().setText(String.valueOf(m.getMonto()));
        camposMovimientoPanel.getMontoTxt().setEnabled(false);
        camposMovimientoPanel.getCuentaOrigenTxt().setText(m.getCuentaOrigen() != null ? String.valueOf(m.getCuentaOrigen().getNumero()) : "");
        camposMovimientoPanel.getCuentaOrigenTxt().setEnabled(false);
        camposMovimientoPanel.getCuentaDestinoTxt().setText(m.getCuentaDestino() != null ? String.valueOf(m.getCuentaDestino().getNumero()) : "");
        camposMovimientoPanel.getCuentaDestinoTxt().setEnabled(false);
        camposMovimientoPanel.getTarjetaTxt().setText(m.getTarjeta() != null ? String.valueOf(m.getTarjeta().getNumero()) : "");
        camposMovimientoPanel.getTarjetaTxt().setEnabled(false);
        camposMovimientoPanel.setModoEdicion("si");
    }

    public void vaciarDatos() {
        CamposMovimientoPanel camposMovimientoPanel = (CamposMovimientoPanel) this.camposPanel;
        camposMovimientoPanel.getIdTxt().setText("");
        camposMovimientoPanel.getIdTxt().setEnabled(false);
        camposMovimientoPanel.getFechaTxt().setText("");
        camposMovimientoPanel.getFechaTxt().setEnabled(false);
        camposMovimientoPanel.getDescripcionTxt().setText("");
        camposMovimientoPanel.getDescripcionTxt().setEnabled(true);
        camposMovimientoPanel.getMontoTxt().setText("");
        camposMovimientoPanel.getMontoTxt().setEnabled(true);
        //camposMovimientoPanel.getTiposModel().removeElement("Pago");
        camposMovimientoPanel.getTipoComboBox().setSelectedItem("Transferencia");
        camposMovimientoPanel.getTipoComboBox().setEnabled(true);
        camposMovimientoPanel.getCuentaOrigenTxt().setText("");
        camposMovimientoPanel.getCuentaOrigenTxt().setEnabled(true);
        camposMovimientoPanel.getCuentaDestinoTxt().setText("");
        camposMovimientoPanel.getCuentaDestinoTxt().setEnabled(true);
        camposMovimientoPanel.getTarjetaTxt().setText("");
        camposMovimientoPanel.getTarjetaTxt().setEnabled(true);
        camposMovimientoPanel.setModoEdicion("no");
        camposMovimientoPanel.actualizarCampos();
    }


}
