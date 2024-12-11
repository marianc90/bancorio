package homebaking.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAdminPanel extends JPanel {

    protected PanelManager panelManager;
    private JButton tarjetasBtn;
    private JButton adminUsuarioBtn;
    private JButton cuentaBtn;
    private JButton transacBtn;
    private JButton logoutBtn;

    public PantallaAdminPanel(PanelManager panelManager) {
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
        this.tarjetasBtn = new JButton("Gestionar Tarjetas");
        this.cuentaBtn = new JButton("Control de Cuentas");
        this.transacBtn = new JButton("Movimientos");
        this.logoutBtn = new JButton("Cerrar Sesión");

        this.add(Box.createRigidArea(new Dimension(500, 10)));
        this.add(Box.createRigidArea(new Dimension(340, 0)));
        this.add(logoutBtn);
        this.add(imageLabel);
        this.add(adminUsuarioBtn);
        this.add(tarjetasBtn);
        this.add(cuentaBtn);
        this.add(transacBtn);

        //escuchar evento del ok, mandar a grabar
        this.tarjetasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    panelManager.mostrarPantallaAdminTarjetaPanel();
            }
        });

        this.adminUsuarioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarPantallaAdminUsuarioPanel();
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

        this.cuentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarPantallaAdminCuentaPanel();
            }
        });

        this.transacBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelManager.mostrarPantallaAdminMovimientoPanel();

            }
        });

    }
}
