package homebaking.ui;

import javax.swing.*;
import java.awt.*;

public class BotoneraPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton okBtn;
    private JButton cancelBtn;

    public BotoneraPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarBotoneraPanel();
    }

    public void armarBotoneraPanel() {
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancelar");


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(okBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(cancelBtn, gbc);

    }



    public JButton getOkBtn() {
        return okBtn;
    }

    public void setOkBtn(JButton okBtn) {
        this.okBtn = okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }


}
