package homebaking.ui;


import homebaking.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicioPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton tarjetasBtn;
    private JButton adminUsuarioBtn;
    private JButton cuentaBtn;
    private JButton transacBtn;

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

        this.adminUsuarioBtn = new JButton("Administración de Usuarios");
        this.tarjetasBtn = new JButton("Gestión de Tarjetas");
        this.cuentaBtn = new JButton("Control de Cuentas");
        this.transacBtn = new JButton("Transacciones");

        this.add(imageLabel);
        this.add(adminUsuarioBtn);
        this.add(tarjetasBtn);
        this.add(cuentaBtn);
        this.add(transacBtn);

        //escuchar evento del ok, mandar a grabar
        this.tarjetasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Esta opción estará disponible próximamente.",
                        "Funcionalidad en desarrollo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        this.adminUsuarioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarPantallaAdminUsuarioPanel();
            }
        });

        this.cuentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //panelManager.mostrarPantallaAltaCuentaPanel();
                JOptionPane.showMessageDialog(
                    null,
                    "Esta opción estará disponible próximamente.",
                    "Funcionalidad en desarrollo",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        this.transacBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //panelManager.mostrarPantallaAltaCuentaPanel();
                JOptionPane.showMessageDialog(
                        null,
                        "Esta opción estará disponible próximamente.",
                        "Funcionalidad en desarrollo",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

    }
}
