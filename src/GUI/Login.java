/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.AdministrateurDAO;
import DAO.LoginDAO;
import Entity.Administrateur;
import Entity.Gerant;
import Entity.LoginE;

import com.alee.extended.image.WebDecoratedImage;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.WebPopOver;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.toolbar.WhiteSpace;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author FATHALLAH Wael
 */
public class Login {

    public static JFrame loginFrame;
    public static JFrame adminFrame;
    private Administrateur connectedAdmin;

    public static void main(final String[] args) {
        new Login();
    }

    public Login() {
        // Look and Feel 
        WebLookAndFeel.install();
        //Open Login Frame
        WebLookAndFeel.setDecorateAllWindows(true);
        openLoginFrame();
    }

    private WebPanel createLoginPanel() {
        final WebPanel panel = new WebPanel();
        panel.setUndecorated(false);
        panel.setWebColoredBackground(false);

        //Logo
        ImageIcon i1 = new ImageIcon(getClass().getResource("/images/logo.png"));

        WebDecoratedImage img2 = new WebDecoratedImage(i1);
        img2.setShadeWidth(0);
        img2.setBlur(false);
        img2.setGrayscale(true);
        //Login
        //final JLabel textLabelLogin = new WebLabel("<html>   <head></head>    <body> <p><font size=\"4\" color=\"#000000\">Login</font></p>   </body>  </html>  ");
        final JLabel textLabelLogin = new WebLabel("Mail");
        final JTextField textFieldLogin = new JTextField();
        TooltipManager.setTooltip(textFieldLogin, "Votre mail va Ici", TooltipWay.up, 0);
        //PassWord
        final JLabel textLabelPassWrod = new WebLabel("Mot De Passe");
        final JPasswordField textFieldPassword = new JPasswordField();
        TooltipManager.setTooltip(textFieldPassword, "Votre Mot De Passe va Ici", TooltipWay.up, 0);
        //Boutton
        final JButton login = new JButton("Login");
        final JButton cancel = new JButton("Cancel");
        final WebCheckBox checkBoxA = new WebCheckBox("Administrateur");
        WebLinkLabel LogUp = new WebLinkLabel();
        LogUp.setText("Inscription");
        LogUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LogUp().openLogupFrame();
            }
        });

        //ActionListener
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new MyConnexion();
                LoginDAO ldao = new LoginDAO();
                char[] pass = null;
                pass = textFieldPassword.getPassword();
                String passString = new String(pass);
                String mailString = textFieldLogin.getText();
                MessageDigest digest = null;
                try {
                    digest = java.security.MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                digest.update(passString.getBytes());
                byte[] passwordBytes = digest.digest();
                if (!fieldIsEmpty(mailString)) {
                    //Mail fiels not empty
                    if (!fieldIsEmpty(passString)) {

                        //Password fiels not empty
                        if (checkBoxA.isSelected()) {
                            connectedAdmin = new AdministrateurDAO().signIn(textFieldLogin.getText(), passString);
                            if (connectedAdmin == null) {
                                final WebPopOver popOver = new WebPopOver(loginFrame);
                                popOver.add(new WebLabel("Mail ou mot de passe ne sont pas correctes"));
                                popOver.setCloseOnFocusLoss(true);
                                popOver.setMargin(10);
                                popOver.setLayout(new VerticalFlowLayout());
                                popOver.show(loginFrame);
                            } else {

                                adminFrame = new AdminGUI(connectedAdmin);
                                adminFrame.setVisible(true);
                                loginFrame.dispose();
                            }
                        } else {
                            //do the work for client or ger
                            LoginE loginE = new LoginDAO().LoginU(textFieldLogin.getText().substring(0).toLowerCase(), passString);
                            System.out.print(loginE);
                            if (loginE.getEmail() != null) {
                                if (loginE.getPriv() != 1) {
                                    //do the work for client
                                    try {

                                        Main vv = new Main();
                                        Main.openPleaseWait();
                                        Main.openListOfOffresFrame(loginE);
                                    } catch (IOException ex) {

                                    } finally {

                                        loginFrame.setVisible(false); //you can't see me!
                                        loginFrame.dispose(); //Destroy the JFrame object

                                    }
                                } else {
                                    //do the work for ger
                                    Gerant gg = new Gerant();
                                    gg.setId(loginE.getId());
                                    gg.setNom(loginE.getNom());
                                    gg.setPrenom(loginE.getPrenom());
                                    gg.setNumFix(String.valueOf(loginE.getNumFix()));
                                    gg.setNumMobile(String.valueOf(loginE.getNumMob()));
                                    gg.setMail(loginE.getEmail());
                                    new GerantGUI(gg);
                                    loginFrame.setVisible(false); //you can't see me!
                                    loginFrame.dispose(); //Destroy the JFrame object
                                }

                            } else {
                                final WebPopOver popOver = new WebPopOver(loginFrame);
                                popOver.setCloseOnFocusLoss(true);
                                popOver.setMargin(10);
                                popOver.setLayout(new VerticalFlowLayout());
                                popOver.add(new WebLabel("Mail ou mot de passe ne sont pas correctes"));

                                popOver.show(loginFrame);
                            }

                        }

                    } else {
                        System.out.println("Password field is empty");
                        final WebPopOver popOver = new WebPopOver(textFieldPassword);
                        popOver.setCloseOnFocusLoss(true);
                        popOver.setMargin(10);
                        popOver.setLayout(new VerticalFlowLayout());
                        popOver.add(new WebLabel("Champ mot de passe est vide"));

                        popOver.show(textFieldPassword);
                    }
                } else {
                    System.out.println("Login field is empty");
                    final WebPopOver popOver = new WebPopOver(textFieldLogin);
                    popOver.setCloseOnFocusLoss(true);
                    popOver.setMargin(10);
                    popOver.setLayout(new VerticalFlowLayout());
                    popOver.add(new WebLabel("Champ mail est vide"));

                    popOver.show(textFieldLogin);
                }
            }

            private boolean fieldIsEmpty(String mailString) {
                return mailString.equals("");
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //Login Group Panel
        final GroupPanel LoginPanel = new GroupPanel(GroupingType.fillFirst, 5, false, new JLabel(i1), textLabelLogin, textFieldLogin, textLabelPassWrod, textFieldPassword, checkBoxA,
                new GroupPanel(GroupingType.fillFirst, 5, new WhiteSpace(), login, cancel),
                new GroupPanel(GroupingType.fillFirst, 5, new WhiteSpace(), LogUp)
        );

        LoginPanel.setMargin(20, 20, 20, 20);

        // Login Panel Confeguration 
        panel.setRound(StyleConstants.largeRound);
        panel.setAutoscrolls(false);
        panel.setPreferredSize(new Dimension(300, 350));
        panel.setMaximumSize(new Dimension(300, 350));

        panel.add(LoginPanel);

        return panel;
    }

    public void openLoginFrame() {
        loginFrame = new JFrame();
        loginFrame.setSize(300, 420);
        loginFrame.setMaximumSize(new Dimension(300, 400));

        loginFrame.setTitle("Login");
        loginFrame.setLayout(new GridLayout(1, 1, 20, 20));
        JPanel f = new JPanel();
        f.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        f.setLayout(new GridLayout(1, 1, 5, 5));
        f.add(createLoginPanel());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loginFrame.setLocation((dim.height / 2) + 150, 150);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.add(f);
        loginFrame.setVisible(true);
    }
}
