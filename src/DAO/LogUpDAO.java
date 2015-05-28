/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Interface.ILogUpDAO;
import Technique.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author FATHLLAH Wael
 */
public class LogUpDAO implements ILogUpDAO{
//    INSERT INTO `agenceimmob`.`utilisateur` (`id`, `mail`, `mot_de_passe`, `nom`, `prenom`, `numMobile`, `numFix`, `privilege`) 
//    VALUES (NULL, 'fathallahwael80@gmail', 'wael', 'FATHALLAH', 'Wael', '', NULL, '');
    @Override
    public boolean logUPG(String email, String password, String nom, String prenom, int numMob, int numFix, String status_matrimonial, int priv, String URLp) {
        String requete = "insert into utilisateur (id, mail, password, nom, prenom, numMobile, numFix, status_matrimonial, role, URLp)"
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            
            ps.setInt(5, numMob);
            ps.setInt(6, numFix);
            ps.setString(7, status_matrimonial);
            ps.setInt(8, priv);
            ps.setString(9, URLp);
            
            ps.executeUpdate();
            System.out.println("LogUp Done");
            return true;
                    
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean logUPC(String email, String password, String nom, String prenom, String status_matrimonial, int priv, String URLp) {
        String requete = "insert into utilisateur (id, mail, password, nom, prenom, numMobile, numFix, status_matrimonial, role, URLp)"
                + "VALUES (NULL, ?, ?, ?, ?, null, null, ?, ?, ?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nom);
            ps.setString(4, prenom);
            ps.setString(5, status_matrimonial);
            ps.setInt(6, priv);
            ps.setString(7, URLp);
            
            ps.executeUpdate();
            System.out.println("LogUp Done");
            return true;
                    
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    
}
