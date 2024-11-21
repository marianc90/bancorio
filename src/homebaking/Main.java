package homebaking;

import homebaking.ui.PanelManager;

public class Main {

    private PanelManager manager;

    public static void main(String[] args) {

        Main ppal = new Main();
        ppal.iniciarManager();
        ppal.showFrame();
    }

    public void iniciarManager() {
        manager = new PanelManager();
        manager.armarManager();

        manager.mostrarInicioPanel();
    }

    public void showFrame() {
        manager.showFrame();
    }

}
