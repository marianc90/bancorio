package homebaking.ui.Tarjeta;

import homebaking.ui.CamposPanel;
import homebaking.ui.PanelManager;

import javax.swing.*;
import java.awt.*;

public class CamposTarjetaPanel extends CamposPanel {

    private JComboBox<String> tipoComboBox;
    private JTextField numeroTxt;
    private JTextField titularIDTxt;
    private JTextField saldoTxt;
    private JTextField disponibleTxt;
    private String modoEdicion = "no"; //no, saldo, disponible

    public CamposTarjetaPanel(PanelManager panelManager) {
        super(panelManager);
    }

    public void armarFormulario() {
        this.setLayout(new GridLayout(3,2,10,10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tipoLbl = new JLabel("Tipo:");
        JLabel numeroLbl = new JLabel("Numero:");
        JLabel titularIDLbl = new JLabel("ID Titular:");
        JLabel disponibleLbl = new JLabel("Disponible:");
        JLabel saldoLbl = new JLabel("Saldo:");

        String[] tipos = {"Visa", "Mastercard"};
        tipoComboBox = new JComboBox<>(tipos);
        numeroTxt = new JTextField("");
        titularIDTxt = new JTextField("");
        disponibleTxt = new JTextField("");
        saldoTxt = new JTextField("");

        this.add(tipoLbl);
        this.add(tipoComboBox);
        this.add(numeroLbl);
        this.add(numeroTxt);
        this.add(titularIDLbl);
        this.add(titularIDTxt);
        this.add(saldoLbl);
        this.add(saldoTxt);
        this.add(disponibleLbl);
        this.add(disponibleTxt);

    }

    public String getTipoSeleccionado() {
        String tipoSeleccionado = (String) tipoComboBox.getSelectedItem();
        if (tipoSeleccionado.equals("Visa")) {
            return "Visa";
        } else if (tipoSeleccionado.equals("Mastercard")) {
            return "Mastercard";
        }
        return null;
    }

    public JComboBox<String> getTipoComboBox() {
        return tipoComboBox;
    }

    public void setTipoComboBox(JComboBox<String> tipoComboBox) {
        this.tipoComboBox = tipoComboBox;
    }

    public JTextField getNumeroTxt() {
        return numeroTxt;
    }

    public void setNumeroTxt(JTextField numeroTxt) {
        this.numeroTxt = numeroTxt;
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

    public JTextField getDisponibleTxt() {
        return disponibleTxt;
    }

    public void setDisponibleTxt(JTextField disponibleTxt) {
        this.disponibleTxt = disponibleTxt;
    }

    public void setModoEdicion(String edicion) {
        this.modoEdicion = edicion;
    }

    public String isModoEdicion() {
        return modoEdicion;
    }
}
