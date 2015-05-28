/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import DAO.AdresseDAO;
import DAO.AgenceDAO;
import DAO.GerantDAO;
import Entity.Adresse;
import Entity.Agence;
import Entity.Gerant;
import GUI.Adresse.Adapters.AdresseAdapter;
import GUI.Adresse.Adapters.AdresseAdapterInstant;
import GUI.Agence.Adapters.AgenceAdapter;
import GUI.Gerant.Adapters.GerantAdapter;
import GUI.Gerant.Adapters.GerantAdapterInstant;
import GUI.Gerant.Adapters.GerantAdapterName;
import GUI.Gerant.Adapters.GerantAdapterPrenom;
import com.alee.extended.image.WebImage;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.text.WebTextField;
import com.alee.managers.notification.NotificationManager;
import javax.swing.JOptionPane;
import org.jfree.ui.tabbedui.VerticalLayout;

/**
 *
 * @author ShujiX
 */
public class Agence_Panel extends javax.swing.JPanel {

    /**
     * Creates new form Agence_Panel
     */
    public Agence_Panel() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_agences = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        txt_description = new javax.swing.JTextField();
        bt_exporter = new javax.swing.JButton();
        bt_supprimer = new javax.swing.JButton();
        bt_ajouter = new javax.swing.JButton();
        bt_modifier = new javax.swing.JButton();
        lbl_gerants = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_gerants = new javax.swing.JTable();
        bt_trie = new javax.swing.JButton();
        combo_trie = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_adresses = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        bt_chercherA = new javax.swing.JButton();
        txt_chercherA = new WebTextField();
        jPanel1 = new javax.swing.JPanel();
        txt_chercherG = new WebTextField();
        bt_chercherG = new javax.swing.JButton();

        table_agences.setModel(new AgenceAdapter());
        table_agences.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_agences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_agencesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_agences);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Les agences");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png"))); // NOI18N
        jLabel2.setText("Help");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setText("Nom ");

        jLabel4.setText("Description");

        bt_exporter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        bt_exporter.setText("Exporter");
        bt_exporter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exporterActionPerformed(evt);
            }
        });

        bt_supprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        bt_supprimer.setText("Supprimer");
        bt_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_supprimerActionPerformed(evt);
            }
        });

        bt_ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        bt_ajouter.setText("Ajouter");
        bt_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ajouterActionPerformed(evt);
            }
        });

        bt_modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        bt_modifier.setText("Modifier");
        bt_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modifierActionPerformed(evt);
            }
        });

        lbl_gerants.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_gerants.setText("Les gerants");

        table_gerants.setModel(new GerantAdapter());
        jScrollPane2.setViewportView(table_gerants);

        bt_trie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sort.png"))); // NOI18N
        bt_trie.setText("Trier par");
        bt_trie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_trieActionPerformed(evt);
            }
        });

        combo_trie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nom", "Prenom" }));

        table_adresses.setModel(new AdresseAdapter());
        jScrollPane3.setViewportView(table_adresses);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Les adresses");

        bt_chercherA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go.png"))); // NOI18N
        bt_chercherA.setText("Chercher");
        bt_chercherA.setMargin(new java.awt.Insets(2, 4, 2, 10));
        bt_chercherA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_chercherAActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_chercherG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/go.png"))); // NOI18N
        bt_chercherG.setText("Chercher");
        bt_chercherG.setIconTextGap(3);
        bt_chercherG.setMargin(new java.awt.Insets(2, 4, 2, 10));
        bt_chercherG.setMaximumSize(new java.awt.Dimension(91, 33));
        bt_chercherG.setMinimumSize(new java.awt.Dimension(91, 33));
        bt_chercherG.setPreferredSize(new java.awt.Dimension(91, 33));
        bt_chercherG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_chercherGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_gerants)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_exporter)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_supprimer))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(373, 373, 373))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(bt_modifier)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(bt_ajouter))
                                                .addComponent(txt_description, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 28, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel2)))))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_trie)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_trie, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(txt_chercherG, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(bt_chercherG, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(txt_chercherA, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(bt_chercherA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, txt_chercherA, txt_chercherG);

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_description, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_exporter)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_supprimer)
                        .addComponent(bt_modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_ajouter)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_gerants)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_trie)
                            .addComponent(combo_trie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txt_chercherG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_chercherG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txt_chercherA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_chercherA))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, txt_chercherA, txt_chercherG);

        WebTextField txt_chercherA_aux = (WebTextField) txt_chercherA;
        txt_chercherA_aux.setTrailingComponent(new WebImage(getClass().getResource("/images/search.png")));
        txt_chercherA_aux.setInputPrompt("Chercher par");
        txt_chercherA = txt_chercherA_aux;
        WebTextField txt_chercherG_aux = (WebTextField) txt_chercherG;
        txt_chercherG_aux.setTrailingComponent(new WebImage(getClass().getResource("/images/search.png")));
        txt_chercherG_aux.setInputPrompt("Chercher par");
        txt_chercherG = txt_chercherG_aux;
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        WebPopOver po=new WebPopOver();
        po.setCloseOnFocusLoss(true);
        po.setMargin(10);
        po.setLayout(new VerticalLayout());
        po.add(new WebLabel("**Pour l'ajout :"));
        po.add(new WebLabel("1. Remplir les champs nom & description "));
        po.add(new WebLabel("2. Choisir un gerant & une adresse "));
        po.add(new WebLabel("3. Cliquer sur ajouter "));
        po.add(new WebLabel("**Pour modifier :"));
        po.add(new WebLabel("1 . Choisir une agence de la liste "));
        po.add(new WebLabel("2 . Cliquer sur modifier"));
        po.add(new WebLabel("**Exportation : Génerer un fichier contenant tous les agences "));
        po.show(jLabel2);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void bt_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_supprimerActionPerformed
        if (table_agences.getSelectedRow()==-1) {
            NotificationManager.showNotification ( "Veuillez sélectionner une agence" ).setDisplayTime(2000);
        }
        else
        {   int dialogResult=WebOptionPane.showConfirmDialog ( bt_supprimer, "Voulez vous vraiment supprimer ce gérant ?", "Confirmer", WebOptionPane.YES_NO_OPTION,
                        WebOptionPane.QUESTION_MESSAGE );
            if(dialogResult==0){
            int i = Integer.parseInt(table_agences.getValueAt(table_agences.getSelectedRow(),0)+"");
            AgenceDAO agd = new AgenceDAO();
            agd.deleteAgence(i);
            table_agences.setModel(new AgenceAdapter());
            }
        }
    }//GEN-LAST:event_bt_supprimerActionPerformed

    private void table_agencesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_agencesMousePressed
        int i = Integer.parseInt(table_agences.getValueAt(table_agences.getSelectedRow(),0)+"");
        String nom=(String) table_agences.getValueAt(table_agences.getSelectedRow(),1);
        String description=(String) table_agences.getValueAt(table_agences.getSelectedRow(),2);
        txt_nom.setText(nom);
        txt_description.setText(description);
        this.id_ag=i;
    }//GEN-LAST:event_table_agencesMousePressed

    private void bt_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modifierActionPerformed
        if((!txt_nom.getText().isEmpty())&&(!txt_description.getText().isEmpty())&&id_ag!=0){
            Agence ag=new Agence(id_ag, txt_nom.getText(), txt_description.getText());
            AgenceDAO agd=new AgenceDAO();
            agd.updateAgence(ag);
            table_agences.setModel(new AgenceAdapter());
            JOptionPane.showMessageDialog(bt_modifier, "Agence modifiée avec succés ");
            
        }else{
            NotificationManager.showNotification ( "Veuillez sélectionner une agence à modifier !" ).setDisplayTime(2000);
        }
    }//GEN-LAST:event_bt_modifierActionPerformed

    private void bt_exporterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exporterActionPerformed
         int dialogResult=WebOptionPane.showConfirmDialog ( bt_exporter, "Voulez vous vraiment génerer un rapport ?", "Confirmer", WebOptionPane.YES_NO_OPTION,
                        WebOptionPane.QUESTION_MESSAGE );
            if(dialogResult==0){
                //Jasper_Gen test_Jasper=new Jasper_Gen();
                // JProgressBar;
                //test_Jasper.generate_report();
            }
        
    }//GEN-LAST:event_bt_exporterActionPerformed

    private void bt_trieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_trieActionPerformed
 if (String.valueOf(combo_trie.getSelectedItem()).equalsIgnoreCase("nom"))
       {    
            table_gerants.setModel(new GerantAdapterName());}
       else if (String.valueOf(combo_trie.getSelectedItem()).equalsIgnoreCase("prenom")){
            table_gerants.setModel(new GerantAdapterPrenom());
       }
    }//GEN-LAST:event_bt_trieActionPerformed

    private void bt_chercherGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_chercherGActionPerformed
               table_gerants.setModel(new GerantAdapterInstant(txt_chercherG.getText()));
    }//GEN-LAST:event_bt_chercherGActionPerformed

    private void bt_chercherAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_chercherAActionPerformed
               table_adresses.setModel(new AdresseAdapterInstant(txt_chercherA.getText()));
    }//GEN-LAST:event_bt_chercherAActionPerformed

    private void bt_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ajouterActionPerformed
         if(txt_nom.getText().isEmpty() || txt_description.getText().isEmpty()){
                     NotificationManager.showNotification ("Veuillez remplir le nom & l'adresse").setDisplayTime(2000);
        }else{
            if(table_gerants.getSelectedRow() ==-1 || table_adresses.getSelectedRow()==-1){
                 NotificationManager.showNotification ( "Veuillez sélectionnez un gérant est une adresse" ).setDisplayTime(2000);
            }else{
                
                int ig = Integer.parseInt(table_gerants.getValueAt(table_gerants.getSelectedRow(),0)+"");
                int ia = Integer.parseInt(table_adresses.getValueAt(table_adresses.getSelectedRow(),0)+"");
                AgenceDAO agd=new AgenceDAO();
                GerantDAO grd=new GerantDAO();
                AdresseDAO ado=new AdresseDAO();
                if(agd.findExistGerant(ig)==0){
                        Gerant g=grd.findGerantById(ig);
                        Adresse a=ado.findAdresseById(ia);
                        Agence ag=new Agence();
                        ag.setNom(txt_nom.getText());
                        ag.setDescription(txt_description.getText());
                        ag.setGerant(g);
                        ag.setAdresse(a);
                        agd.insertAgence(ag);
                        table_agences.setModel(new AgenceAdapter());
                        JOptionPane.showMessageDialog(bt_modifier, "Agence ajouter avec succés ");
                        
                }else{
                        JOptionPane.showMessageDialog(this, "Le gerant selectionné posséde déja une agence !", "Error", 1, 
                                new javax.swing.ImageIcon(getClass().getResource("/ressources/error.png")));
                        }
            }
        }
    }//GEN-LAST:event_bt_ajouterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ajouter;
    private javax.swing.JButton bt_chercherA;
    private javax.swing.JButton bt_chercherG;
    private javax.swing.JButton bt_exporter;
    private javax.swing.JButton bt_modifier;
    private javax.swing.JButton bt_supprimer;
    private javax.swing.JButton bt_trie;
    private javax.swing.JComboBox combo_trie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_gerants;
    private javax.swing.JTable table_adresses;
    private javax.swing.JTable table_agences;
    private javax.swing.JTable table_gerants;
    private javax.swing.JTextField txt_chercherA;
    private javax.swing.JTextField txt_chercherG;
    private javax.swing.JTextField txt_description;
    private javax.swing.JTextField txt_nom;
    // End of variables declaration//GEN-END:variables
    private int id_ag=0;
}
