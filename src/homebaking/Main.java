package homebaking;

import homebaking.exceptions.ServiceException;
import homebaking.ui.PanelManager;

public class Main {

    private PanelManager manager;

    public static void main(String[] args) throws ServiceException {

        Main ppal = new Main();
        ppal.iniciarManager();
        ppal.showFrame();
    }

    public void iniciarManager() throws ServiceException {
        manager = new PanelManager();
        manager.armarManager();
        manager.mostrarInicioPanel();
    }

    public void showFrame() {
        manager.showFrame();
    }

}
