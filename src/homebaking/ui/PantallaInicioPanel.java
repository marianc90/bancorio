package homebaking.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicioPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton loginBtn;

    public PantallaInicioPanel(PanelManager panelManager) {
        this.panelManager = panelManager;
        armarPantallaPanel();
    }

    public void armarPantallaPanel() {
        this.setLayout(new FlowLayout());

        this.setBackground(Color.decode("#204F9F"));
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/images/banco.png"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(380, 380, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        this.loginBtn = new JButton("Iniciar Sesión");

        this.add(Box.createRigidArea(new Dimension(500, 10)));
        this.add(Box.createRigidArea(new Dimension(340, 0)));
        this.add(loginBtn);
        this.add(imageLabel);

        this.loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarLoginPanel();
            }
        });

    }
}
