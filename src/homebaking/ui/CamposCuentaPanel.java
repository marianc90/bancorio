package homebaking.ui;

import javax.swing.*;
import java.awt.*;

public class CamposCuentaPanel extends CamposPanel {

    private JComboBox<String> tipoComboBox;
    private JTextField titularIDTxt;
    private JTextField saldoTxt;
    private JTextField numeroTxt;
    private boolean esEdicion = false;

    public CamposCuentaPanel(PanelManager panelManager) {
        super(panelManager);
    }

    public void armarFormulario() {
        this.setLayout(new GridLayout(2,2,5,5));

        JLabel tipoLbl = new JLabel("Tipo:");
        JLabel titularIDLbl = new JLabel("ID Titular:");
        JLabel saldoLbl = new JLabel("Saldo:");
        JLabel numeroLbl = new JLabel("Numero:");

        String[] tipos = {"Caja de Ahorro", "Cuenta Corriente"};
        tipoComboBox = new JComboBox<>(tipos);
        titularIDTxt = new JTextField("");
        saldoTxt = new JTextField("");
        numeroTxt = new JTextField("");

        this.add(tipoLbl);
        this.add(tipoComboBox);
        this.add(titularIDLbl);
        this.add(titularIDTxt);
        this.add(saldoLbl);
        this.add(saldoTxt);
        this.add(numeroLbl);
        this.add(numeroTxt);

    }

    public String getTipoSeleccionado() {
        String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
        if (tipoSeleccionado.equals("Caja de Ahorro")) {
            return "CA";
        } else if (tipoSeleccionado.equals("Cuenta Corriente")) {
            return "CC";
        }
        return null;
    }

    public JComboBox<String> getTipoComboBox() {
        return tipoComboBox;
    }

    public void setTipoComboBox(JComboBox<String> tipoComboBox) {
        this.tipoComboBox = tipoComboBox;
    }

    public JTextField getTitularIDTxt() {

        return titularIDTxt;
    }

    public void setTitularIDTxt(JTextField titularIDTxt) {

        this.titularIDTxt = titularIDTxt;
    }

    public JTextField getSaldoTxt() {
        return saldoTxt;
    }

    public void setSaldoTxt(JTextField saldoTxt) {
        this.saldoTxt = saldoTxt;
    }

    public JTextField getNumeroTxt() {
        return numeroTxt;
    }

    public void setNumeroTxt(JTextField numeroTxt) {
        this.numeroTxt = numeroTxt;
    }

    public void setModoEdicion(boolean edicion) {
        this.esEdicion = edicion;
    }

    public boolean isModoEdicion() {
        return esEdicion;
    }
}
