package homebaking.ui;

import javax.swing.*;
import java.awt.*;

public class CamposCuentaPanel extends CamposPanel {

    private JTextField cuitTxt;
    private JTextField cbuTxt;

    public CamposCuentaPanel(PanelManager panelManager) {
        super(panelManager);
    }

    public void armarFormulario() {
        this.setLayout(new GridLayout(2,2));

        JLabel cuitLbl = new JLabel("CUIT:");
        JLabel cbuLbl = new JLabel("CBU:");

        cuitTxt = new JTextField("");
        cbuTxt = new JTextField("");

        this.add(cuitLbl);
        this.add(cuitTxt);
        this.add(cbuLbl);
        this.add(cbuTxt);

    }

    public JTextField getCuitTxt() {
        return cuitTxt;
    }

    public void setCuitTxt(JTextField cuitTxt) {
        this.cuitTxt = cuitTxt;
    }

    public JTextField getCbuTxt() {
        return cbuTxt;
    }

    public void setCbuTxt(JTextField cbuTxt) {
        this.cbuTxt = cbuTxt;
    }
}
