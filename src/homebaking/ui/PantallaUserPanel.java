package homebaking.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaUserPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton transferBtn;
    private JButton verTarjetasBtn;
    private JButton verCuentasBtn;
    private JButton emitResumenBtn;
    private JButton logoutBtn;

    public PantallaUserPanel(PanelManager panelManager) {
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

        this.transferBtn = new JButton("Mis Transferencias");
        this.verTarjetasBtn = new JButton("Mis Tarjetas");
        this.verCuentasBtn = new JButton("Mis Cuentas");
        this.emitResumenBtn = new JButton("Emitir Resumen");
        this.logoutBtn = new JButton("Cerrar Sesión");

        this.add(Box.createRigidArea(new Dimension(500, 10)));
        this.add(Box.createRigidArea(new Dimension(340, 0)));
        this.add(logoutBtn);
        this.add(imageLabel);
        this.add(transferBtn);
        this.add(verTarjetasBtn);
        this.add(verCuentasBtn);
        this.add(emitResumenBtn);

        //escuchar evento del ok, mandar a grabar
        this.transferBtn.addActionListener(new ActionListener() {
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

        this.verTarjetasBtn.addActionListener(new ActionListener() {
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

        this.logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Sí", "No"};
                int confirm = JOptionPane.showOptionDialog(
                        null,
                        "¿Está seguro de que desea cerrar sesión?",
                        "Confirmar cierre de sesión",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    panelManager.setUserLogueado(null);
                    panelManager.mostrarInicioPanel();
                }
            }
        });

        this.verCuentasBtn.addActionListener(new ActionListener() {
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

        this.emitResumenBtn.addActionListener(new ActionListener() {
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
