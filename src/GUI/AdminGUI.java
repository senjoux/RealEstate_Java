package GUI;

/**
 *
 * @author seif
 */
import Entity.Administrateur;
import Technique.LoadIcon;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.TabStretchType;
import com.alee.laf.tabbedpane.WebTabbedPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import statistique.PieChart_AWT;

public class AdminGUI extends JFrame {
////////////////////////////////////////////////////////////////////////////////

    private WebTabbedPane tabbedPane;
    private JPanel superPanel;
    private JPanel clientPanel;
    private JPanel statPanel;
    private JPanel gerantPanel;
    private JPanel agencePanel;
    private JPanel connectedPane;
    public Administrateur connectedAdmin;
////////////////////////////////////////////////////////////////////////////////

    private AdminGUI() {
    }
////////////////////////////////////////////////////////////////////////////////

    private WebPanel surroundPanel(JPanel panel) {
        WebScrollPane scrollPane = new WebScrollPane(panel);
        scrollPane.setDrawBackground(false);
        scrollPane.setDrawBorder(false);
        WebPanel backgroundPanel = new WebPanel(new BorderLayout());
        WebPanel frontPanel = new WebPanel(new BorderLayout());
        frontPanel.setUndecorated(false);
        frontPanel.setRound(5);
        frontPanel.add(scrollPane, BorderLayout.CENTER);
        frontPanel.setMargin(3);
        backgroundPanel.add(frontPanel, BorderLayout.CENTER);
        backgroundPanel.add(new WebPanel().setMargin(5), BorderLayout.NORTH);
        backgroundPanel.add(new WebPanel().setMargin(5), BorderLayout.SOUTH);
        backgroundPanel.add(new WebPanel().setMargin(5), BorderLayout.EAST);
        backgroundPanel.add(new WebPanel().setMargin(5), BorderLayout.WEST);
        return backgroundPanel;
    }
////////////////////////////////////////////////////////////////////////////////

    private void menuPanel() {
        WebMenuBar menuBar = new WebMenuBar();
        WebMenu sessionM = new WebMenu("Session", new ImageIcon(getClass().getResource("/images/session.png")));
        WebMenu archiveM = new WebMenu("Archive", new ImageIcon(getClass().getResource("/images/archive.png")));
        WebMenuItem boiteI = new WebMenuItem("Boite de messages", new ImageIcon(getClass().getResource("/images/inbox.png")));
        WebMenuItem deconnectI = new WebMenuItem("Deconnecter", new ImageIcon(getClass().getResource("/images/logout.png")));
        WebMenuItem quitterI = new WebMenuItem("Quitter", new ImageIcon(getClass().getResource("/images/exit.png")));
        WebMenuItem afficherI = new WebMenuItem("Consulter", new ImageIcon(getClass().getResource("/images/eye.png")));
        // Actions
        final JFrame thisFrame = this;
        quitterI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        boiteI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame boiteFrame = new JFrame("Boite de messages");
                boiteFrame.setLocationRelativeTo(null);
                JPanel boite = new JPanel(new BorderLayout());
                boite.add(new BoiteReception(0), BorderLayout.CENTER);
                boiteFrame.add(boite);
                boiteFrame.setIconImage(new ImageIcon(getClass().getResource("/images/message.png")).getImage());
                boiteFrame.pack();
                boiteFrame.setLocationRelativeTo(boite);
                boiteFrame.setVisible(true);
            }
        });
        deconnectI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                new Login();
                thisFrame.dispose();
            }
        });
        afficherI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ArchiveGUI();
            }
        });
        // Add Menu Items
        sessionM.add(boiteI);
        sessionM.add(deconnectI);
        sessionM.add(quitterI);
        archiveM.add(afficherI);
        menuBar.add(sessionM);
        menuBar.add(archiveM);
        this.setJMenuBar(menuBar);
    }
////////////////////////////////////////////////////////////////////////////////

    public AdminGUI(Administrateur connectedAdmin) {
        //recuperation de l'admin connecté
        this.connectedAdmin = connectedAdmin;
        // JFrame init
        setTitle("Dashboard");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(LoadIcon.loadIcon(getClass().getResource("/images/gear.png")).getImage());
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            // Create the tab pages
            initialisation();
        } catch (IOException ex) {
            Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectedPane = new WebPanel(new FlowLayout(FlowLayout.LEFT));
        connectedPane.add( new WebLabel(connectedAdmin.getPrenom() + " " + connectedAdmin.getNom(),new ImageIcon(getClass().getResource("/images/connected.png"))).setFontSize(17) );
        connectedPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        // Add
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(connectedPane,BorderLayout.NORTH);
        menuPanel();
        // Show JFrame
        setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////

    public void initialisation() throws IOException {
        superPanel = new SuperGUI(this.connectedAdmin);
        agencePanel = new Agence_Panel();
        gerantPanel = new Gerant_Panel();
        clientPanel = new ClientListPanel();
        statPanel = new PieChart_AWT("nombre des offres par ville").createDemoPanel();
        // Create a tabbed pane
        tabbedPane = new WebTabbedPane();
        tabbedPane.setTabPlacement(WebTabbedPane.LEFT);
        tabbedPane.setFontSize(20);
        tabbedPane.setRound(5);
        tabbedPane.setTabStretchType(TabStretchType.always);
        // Add tabs
        tabbedPane.addTab("Administrateurs", LoadIcon.loadIcon(getClass().getResource("/images/admin.png")), surroundPanel(superPanel));
        tabbedPane.addTab("Liste des agences", LoadIcon.loadIcon(getClass().getResource("/images/agences.png")), surroundPanel(agencePanel));
        tabbedPane.addTab("Liste des gérants", LoadIcon.loadIcon(getClass().getResource("/images/gerants.png")), surroundPanel(gerantPanel));
        tabbedPane.addTab("Liste des clients", LoadIcon.loadIcon(getClass().getResource("/images/clients.png")), surroundPanel(clientPanel));
        tabbedPane.addTab("Statistiques", LoadIcon.loadIcon(getClass().getResource("/images/stats.png")), surroundPanel(statPanel));
    }
////////////////////////////////////////////////////////////////////////////////

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc="Web Look And Feel install">
        WebLookAndFeel.install();
        WebLookAndFeel.setDecorateAllWindows(true);
        //</editor-fold>

        // Create and display the form
        new AdminGUI(new Administrateur(0, "name", "aze", "aze", "aze@", 77));
    }
}
