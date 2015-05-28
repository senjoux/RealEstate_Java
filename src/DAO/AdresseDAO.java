/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Adresse;
import Entity.Gerant;
import Technique.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ShujiX
 */
public class AdresseDAO implements Interface.IAdresseDAO{

  
    @Override
    public List<Adresse> displayAll() {
        
        String requete="select * from adresse ";
        ArrayList<Adresse> lst=new ArrayList<Adresse>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Adresse addr=new Adresse();
                addr.setId(resultat.getInt(1));
                addr.setGouvernorat(resultat.getString(2));
                addr.setCode_pos(resultat.getInt(3));
                addr.setVille(resultat.getString(4));
                lst.add(addr);
            }
        } catch (SQLException ex) {
           Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lst;    }

    @Override
    public Adresse findAdresseById(int id) {
        Adresse addr=new Adresse();        
        String requete="select * from adresse where id=?";
 
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                addr.setId(resultat.getInt(1));
                addr.setGouvernorat(resultat.getString(2));
                addr.setCode_pos(resultat.getInt(3));
                addr.setVille(resultat.getString(4));                   
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                return addr;    
    }
    

    @Override
    public List<Adresse> findInstant(String ch) {
          String requete="select * from adresse where (ville like ? or gouvernorat like ? or codePostal like ?)";
        ArrayList<Adresse> lst=new ArrayList<Adresse>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, "%"+ch+"%");
            ps.setString(2, "%"+ch+"%");
            ps.setString(3, "%"+ch+"%");
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Adresse addr=new Adresse();
                addr.setId(resultat.getInt(1));
                addr.setGouvernorat(resultat.getString(2));
                addr.setCode_pos(resultat.getInt(3));
                addr.setVille(resultat.getString(4));
                lst.add(addr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return lst;
    }
}
