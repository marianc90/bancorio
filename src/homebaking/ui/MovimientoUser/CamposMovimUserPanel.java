package homebaking.ui.MovimientoUser;

import homebaking.exceptions.ServiceException;
import homebaking.model.Cuenta;
import homebaking.service.CuentaService;
import homebaking.ui.CamposPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CamposMovimUserPanel extends CamposPanel {

    private JTextField descripcionTxt;
    private JTextField montoTxt;
    private JComboBox<Integer> cuentaOrigenComboBox;
    private JTextField cuentaDestinoTxt;
    private JTextField tarjetaTxt;
    private JLabel descripcionLbl;
    private JLabel montoLbl;
    private JLabel cuentaOrigenLbl;
    private JLabel cuentaDestinoLbl;
    private JLabel tarjetaLbl;

    public CamposMovimUserPanel(PanelManager panelManager) throws ServiceException {
        super(panelManager);
    }

    public void armarFormulario() throws ServiceException {
        this.setLayout(new GridLayout(5,2,10,10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        descripcionTxt = new JTextField("");
        montoTxt = new JTextField("");
        cuentaOrigenComboBox = new JComboBox<>();
        cuentaDestinoTxt = new JTextField("");
        tarjetaTxt = new JTextField("");

        descripcionLbl = new JLabel("Referencia:");
        montoLbl = new JLabel("Monto:");
        cuentaOrigenLbl = new JLabel("Cuenta Origen:");
        cuentaDestinoLbl = new JLabel("Cuenta Destino:");
        tarjetaLbl = new JLabel("Tarjeta:");


        this.add(descripcionLbl);
        this.add(descripcionTxt);
        this.add(montoLbl);
        this.add(montoTxt);
        this.add(cuentaOrigenLbl);
        this.add(cuentaOrigenComboBox);
        this.add(cuentaDestinoLbl);
        this.add(cuentaDestinoTxt);
        this.add(tarjetaLbl);
        this.add(tarjetaTxt);

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

    public JComboBox<Integer> getCuentaOrigenComboBox() {
        return cuentaOrigenComboBox;
    }

    public void setCuentaOrigenComboBox(JComboBox<Integer> cuentaOrigenComboBox) {
        this.cuentaOrigenComboBox = cuentaOrigenComboBox;
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

    public JLabel getDescripcionLbl() {
        return descripcionLbl;
    }

    public JLabel getMontoLbl() {
        return montoLbl;
    }

    public JLabel getCuentaOrigenLbl() {
        return cuentaOrigenLbl;
    }

    public JLabel getCuentaDestinoLbl() {
        return cuentaDestinoLbl;
    }

    public JLabel getTarjetaLbl() {
        return tarjetaLbl;
    }
}
