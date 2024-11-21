package homebaking.ui;

public class PantallaAltaCuentaPanel extends AbstractPantallaAltaPanel{

    public PantallaAltaCuentaPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposCuentaPanel(panelManager);
    }

    @Override
    public void ejecutarAccionOk() {
        System.out.println("llamo a bd y grabo cuenta");
    }

    @Override
    public void ejecutarAccionCancel() {
        System.out.println("cancelo accion y muestro lista de cuentas");
    }


}
