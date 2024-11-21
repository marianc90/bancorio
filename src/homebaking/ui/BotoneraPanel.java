package homebaking.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoneraPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton okBtn;
    private JButton cancelBtn;
    private JButton volverBtn;

    public BotoneraPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarBotoneraPanel();
    }

    public void armarBotoneraPanel() {
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancelar");
        volverBtn = new JButton("Menú Principal");

        this.setLayout(new FlowLayout());

        this.add(okBtn);
        this.add(cancelBtn);
        this.add(volverBtn);

        this.volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarInicioPanel();
            }
        });

//		this.okBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ejecutarAccionAceptar();
//			}
//		});
    }
//	public abstract void ejecutarAccionAceptar();


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

    public JButton getVolverBtn() {
        return volverBtn;
    }

    public void setVolverBtn(JButton volverBtn) {
        this.volverBtn = volverBtn;
    }
}
