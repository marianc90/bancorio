package homebaking.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPantallaAltaPanel extends JPanel {

    protected PanelManager panelManager;
    protected CamposPanel camposPanel;
    protected BotoneraPanel botonesPanel;


    public AbstractPantallaAltaPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setCamposPanel();
        this.setBotoneraPanel();
        armarPantallaPanel();
    }

    public void armarPantallaPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(camposPanel);
        this.add(botonesPanel);

        this.botonesPanel.getOkBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionOk();
            }
        });

        this.botonesPanel.getCancelBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionCancel();
            }
        });

    }

    private void setBotoneraPanel() {
        this.botonesPanel = new BotoneraPanel(this.panelManager);
    }

    public abstract void setCamposPanel();

    public abstract void ejecutarAccionOk();

    public abstract void ejecutarAccionCancel();

}

