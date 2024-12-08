package homebaking.ui;


import javax.swing.*;
import java.awt.*;

public class CamposLoginPanel extends CamposPanel {

    private JTextField emailTxt;
    private JTextField passwordTxt;

    public CamposLoginPanel(PanelManager panelManager) {
        super(panelManager);
    }

    public void armarFormulario() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLbl = new JLabel("Email:");
        JLabel passwordLbl = new JLabel("Contraseña:");

        emailTxt = new JTextField(20);
        emailTxt.setBorder(BorderFactory.createCompoundBorder(
                emailTxt.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        emailTxt.setBackground(Color.WHITE);
        emailTxt.setOpaque(true);

        passwordTxt = new JPasswordField(20);
        passwordTxt.setBorder(BorderFactory.createCompoundBorder(
                passwordTxt.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        passwordTxt.setBackground(Color.WHITE);
        passwordTxt.setOpaque(true);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(emailLbl, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(emailTxt, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(passwordLbl, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(passwordTxt, gbc);

    }

    public JTextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(JTextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public JTextField getPasswordTxt() { return passwordTxt;}

    public void setPasswordTxt(JTextField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }
}
