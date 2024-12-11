package homebaking.ui.Movimiento;

import homebaking.exceptions.ServiceException;
import homebaking.ui.CamposPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CamposMovimientoPanel extends CamposPanel {

    private JTextField idTxt;
    private JTextField fechaTxt;
    private JTextField descripcionTxt;
    private JTextField montoTxt;
    private DefaultComboBoxModel<String> tiposModel;
    private JComboBox<String> tipoComboBox;
    private JTextField cuentaOrigenTxt;
    private JTextField cuentaDestinoTxt;
    private JTextField tarjetaTxt;
    private String modoEdicion = "no"; //no, saldo, disponible

    public CamposMovimientoPanel(PanelManager panelManager) throws ServiceException {
        super(panelManager);
    }

    public void armarFormulario() {
        this.setLayout(new GridLayout(4,2,10,10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLbl = new JLabel("ID:");
        JLabel fechaLbl = new JLabel("Fecha:");
        JLabel descripcionLbl = new JLabel("Descripcion:");
        JLabel montoLbl = new JLabel("Monto:");
        JLabel tipoLbl = new JLabel("Tipo:");
        JLabel cuentaOrigenLbl = new JLabel("Cuenta Origen:");
        JLabel cuentaDestinoLbl = new JLabel("Cuenta Destino:");
        JLabel tarjetaLbl = new JLabel("Tarjeta:");

        idTxt = new JTextField("");
        fechaTxt = new JTextField("");
        descripcionTxt = new JTextField("");
        montoTxt = new JTextField("");
        tiposModel = new DefaultComboBoxModel<>(new String[]{"Transferencia", "Débito", "Crédito", "Consumo TC", "Ajuste TC", "Pago"});
        tipoComboBox = new JComboBox<>(tiposModel);
        cuentaOrigenTxt = new JTextField("");
        cuentaDestinoTxt = new JTextField("");
        tarjetaTxt = new JTextField("");

        tipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCampos();
            }
        });

        this.add(idLbl);
        this.add(idTxt);
        this.add(fechaLbl);
        this.add(fechaTxt);
        this.add(descripcionLbl);
        this.add(descripcionTxt);
        this.add(montoLbl);
        this.add(montoTxt);
        this.add(tipoLbl);
        this.add(tipoComboBox);
        this.add(cuentaOrigenLbl);
        this.add(cuentaOrigenTxt);
        this.add(cuentaDestinoLbl);
        this.add(cuentaDestinoTxt);
        this.add(tarjetaLbl);
        this.add(tarjetaTxt);

        actualizarCampos();

    }

    public String getTipoSeleccionado() {
        String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
        if (tipoSeleccionado.equals("Transferencia")) {
            return "TRANSFERENCIA";
        } else if (tipoSeleccionado.equals("Débito")) {
            return "DEBITO";
        } else if (tipoSeleccionado.equals("Crédito")) {
            return "CREDITO";
        } else if (tipoSeleccionado.equals("Consumo TC")) {
            return "CONSUMO";
        } else if (tipoSeleccionado.equals("Ajuste TC")) {
            return "AJUSTE";
        } else if (tipoSeleccionado.equals("Pago")) {
            return "PAGO";
        }
        return null;
    }

    public void actualizarCampos() {
        String tipo = (String) tipoComboBox.getSelectedItem();
        if (tipo.equals("Transferencia")) {
            cuentaOrigenTxt.setEnabled(true);
            cuentaDestinoTxt.setEnabled(true);
            tarjetaTxt.setEnabled(false);
        } else if (tipo.equals("Crédito") || tipo.equals("Débito")) {
            cuentaOrigenTxt.setEnabled(true);
            cuentaDestinoTxt.setEnabled(false);
            tarjetaTxt.setEnabled(false);
        } else if (tipo.equals("Consumo TC") || tipo.equals("Ajuste TC")) {
            cuentaOrigenTxt.setEnabled(false);
            cuentaDestinoTxt.setEnabled(false);
            tarjetaTxt.setEnabled(true);
        } else if (tipo.equals("Pago")) {
            cuentaOrigenTxt.setEnabled(true);
            cuentaDestinoTxt.setEnabled(false);
            tarjetaTxt.setEnabled(true);
        }
    }

    public JTextField getIdTxt() {
        return idTxt;
    }

    public void setIdTxt(JTextField idTxt) {
        this.idTxt = idTxt;
    }

    public JTextField getFechaTxt() {
        return fechaTxt;
    }

    public void setFechaTxt(JTextField fechaTxt) {
        this.fechaTxt = fechaTxt;
    }

    public JTextField getDescripcionTxt() {
        return descripcionTxt;
    }

    public void setDescripcionTxt(JTextField descripcionTxt) {
        this.descripcionTxt = descripcionTxt;
    }

    public JTextField getMontoTxt() {
        return montoTxt;
    }

    public void setMontoTxt(JTextField montoTxt) {
        this.montoTxt = montoTxt;
    }

    public JComboBox<String> getTipoComboBox() {
        return tipoComboBox;
    }

    public void setTipoComboBox(JComboBox<String> tipoComboBox) {
        this.tipoComboBox = tipoComboBox;
    }

    public JTextField getCuentaOrigenTxt() {
        return cuentaOrigenTxt;
    }

    public void setCuentaOrigenTxt(JTextField cuentaOrigenTxt) {
        this.cuentaOrigenTxt = cuentaOrigenTxt;
    }

    public JTextField getCuentaDestinoTxt() {
        return cuentaDestinoTxt;
    }

    public void setCuentaDestinoTxt(JTextField cuentaDestinoTxt) {
        this.cuentaDestinoTxt = cuentaDestinoTxt;
    }

    public JTextField getTarjetaTxt() {
        return tarjetaTxt;
    }

    public void setTarjetaTxt(JTextField tarjetaTxt) {
        this.tarjetaTxt = tarjetaTxt;
    }

    public DefaultComboBoxModel<String> getTiposModel() {
        return tiposModel;
    }

    public void setTiposModel(DefaultComboBoxModel<String> tiposModel) {
        this.tiposModel = tiposModel;
    }

    public void setModoEdicion(String edicion) {
        this.modoEdicion = edicion;
    }

    public String isModoEdicion() {
        return modoEdicion;
    }
}
