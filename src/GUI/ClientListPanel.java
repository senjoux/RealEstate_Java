package GUI;

/**
 *
 * @author seif
 */
import DAO.ClientDAO;
import Technique.ClientAdapter;
import Technique.ClientAdapter2;
import Technique.ClientAdapter3;
import Technique.ClientAdapter4;
import Technique.ClientAdapter5;
import Technique.ClientAdapter6;
import Technique.LoadIcon;
import com.alee.extended.image.WebImage;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.tooltip.TooltipManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClientListPanel extends WebPanel {

    // <editor-fold defaultstate="collapsed" desc="Attributs">                   
    private JButton jButton;
    private JButton jButton1;
    private JButton jButton2;
    private JButton contactButton;
    private JLabel jLabel1;
    private JComboBox jList;
    private WebPanel mainPanel;
    private JScrollPane jScrollPane1;
    private JTable jTable2;
    private WebTextField jTextField1;
    // </editor-fold >

    public ClientListPanel() {
        initComponents();
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        
        mainPanel = new WebPanel();
        jList = new JComboBox();
        jScrollPane1 = new JScrollPane();
        jTable2 = new JTable();
        jTextField1 = new WebTextField();
        jLabel1 = new JLabel();
        jButton = new JButton();
        jButton1 = new JButton();
        jButton2 = new JButton();
        contactButton = new JButton();
        
        jTextField1.setTrailingComponent(new WebImage(LoadIcon.loadIcon(getClass().getResource("/images/search.png"))));
        jTextField1.setInputPrompt("Chercher par nom");
        
        jList.setFont(new java.awt.Font("Papyrus", 1, 14));
        jList.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Nom", "Prénom", "Mail", "Password"}));
        jList.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 51, 51)));
        TooltipManager.setTooltip(jList, "Critère de trie", TooltipWay.up, 0);
        
        jScrollPane1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        jTable2.setModel(new ClientAdapter());
        jScrollPane1.setViewportView(jTable2);
        
        jButton1.setFont(new java.awt.Font("Georgia", 1, 12));
        jButton1.setText("Trier Liste");
        jButton1.setIcon(LoadIcon.loadIcon(getClass().getResource("/images/sort.png")));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jLabel1.setFont(new java.awt.Font("Perpetua", 1, 18));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Liste Des Clients");
        
        jButton.setText("Supprimer");
        jButton.setIcon(LoadIcon.loadIcon(getClass().getResource("/images/cancel.png")));
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });
        
        jButton2.setForeground(new java.awt.Color(102, 0, 153));
        jButton2.setIcon(LoadIcon.loadIcon(getClass().getResource("/images/go.png")));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        contactButton.setText("Contacter");
        contactButton.setIcon(LoadIcon.loadIcon(getClass().getResource("/images/send.png")));
        contactButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_dest = 0;
                if (jTable2.getSelectedRow() != -1) {
                    id_dest = (int) jTable2.getValueAt(jTable2.getSelectedRow(), 0);
                    new EnvoyerMessage(0, id_dest).setLocationRelativeTo(null);
                }
                else{
                    NotificationManager.showNotification ( "Veuillez sélectionner un utilisateur" ).setDisplayTime(2000);
                }
            }
        });

        // South Panel
        WebPanel southPanel = new WebPanel();
        southPanel.setOpaque(false);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        southPanel.setMargin(15, 15, 15, 15);
        southPanel.add(jTextField1);
        southPanel.add(jButton2);
        southPanel.add(Box.createRigidArea(new Dimension(300, 0)));
        southPanel.add(contactButton);
        southPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        southPanel.add(jButton);
        southPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        southPanel.add(jList);
        southPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        southPanel.add(jButton1);
        // Center Panel
        WebPanel centerPanel = new WebPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(jScrollPane1);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Liste des Clients"));
        // Add panels
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }// </editor-fold>                        

    // <editor-fold defaultstate="collapsed" desc="Action hangle">
    // Search button
    private void jButton2ActionPerformed(ActionEvent evt) {
        ClientDAO c = new ClientDAO();
        String ch = jTextField1.getText();
        jTable2.setModel(new ClientAdapter6(ch));
    }

    // Sort button
    private void jButton1ActionPerformed(ActionEvent evt) {
        ClientDAO clientdao = new ClientDAO();
        if (jList.getSelectedIndex() == 0) {
            clientdao.TrierParNom();
            jTable2.setModel(new ClientAdapter2());
        } else if (jList.getSelectedIndex() == 1) {
            clientdao.TrierParPrenom();
            jTable2.setModel(new ClientAdapter3());
        } else if (jList.getSelectedIndex() == 2) {
            clientdao.TrierParMail();
            jTable2.setModel(new ClientAdapter4());
        } else if (jList.getSelectedIndex() == 3) {
            clientdao.TrierParPassword();
            jTable2.setModel(new ClientAdapter5());
        }
        
    }

    // Delete button
    private void jButtonActionPerformed(ActionEvent evt) {
        
        if (jTable2.getSelectedRow() == -1) {
            NotificationManager.showNotification ( "Veuillez sélectionnez une ligne" ).setDisplayTime(2000);
        } else {
            int i = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0) + "");
            
            ClientDAO clientdao = new ClientDAO();
            clientdao.deleteClient(i);
            JOptionPane.showMessageDialog(this, "Suppression effectuée avec succés");
            jTable2.setModel(new ClientAdapter());
        }
        
    }
    //</editor-fold>
}
