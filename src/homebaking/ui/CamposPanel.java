package homebaking.ui;

import homebaking.exceptions.ServiceException;

import javax.swing.*;

public abstract class CamposPanel extends JPanel {

    private PanelManager m;

    public CamposPanel(PanelManager m) throws ServiceException {
        this.m = m;
        armarFormulario();
    }

    public abstract void armarFormulario() throws ServiceException;

}
