/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.Agence;
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
 * @author user
 */
public class GerantDAO implements Interface.IGerantDAO{

    @Override
    public void insertGerant(Gerant g) {
        
        // ajout de l'attribut role qui prend 1 pour gerant et 0 pour les autres utilisateurs
         String req="insert into utilisateur (mail,password,nom,prenom,numMobile,numFix,role) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, g.getMail());
            ps.setString(2, g.getPassword());
            ps.setString(3, g.getNom());
            ps.setString(4, g.getPrenom());
            ps.setString(5, g.getNumTel());
            ps.setString(6, g.getNumFix());
            ps.setInt(7, 1);
            
            // le gearant doit avoir une table a lui parceque la table 
            // gerant admet une clé etrangére de la table agence
            // Il faut ajouter l 'id de l'agence dans laquelle appartient ce gerant
            ps.execute();
            System.out.println("Insertion gérant effectuée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteGerant(int id) {
        String req="delete from utilisateur where id=? and role=?";
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, id);
            ps.setInt(2, 1);
            ps.executeUpdate();
            System.out.println("Suppression Gerant avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Gerant> displayAll() {
        
        String requete="select * from utilisateur where role=?";
        ArrayList<Gerant> lst=new ArrayList<Gerant>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Gerant gr=new Gerant();
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
                lst.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lst;
    }

    @Override
    public Gerant findGerantById(int id) {
        Gerant gr=new Gerant();        
        String requete="select * from utilisateur where id=? and role=?";
 
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.setInt(2, 1);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                return gr;
    }

    @Override
    public void updateGerant(Gerant g) {

        String req="update utilisateur set mail=?,password=?,nom=?,prenom=?,numMobile=?,numFix=? "
                + "where id=? and role=?";
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(req);
            ps.setString(1,g.getMail());
            ps.setString(2,g.getPassword());
            ps.setString(3,g.getNom());
            ps.setString(4,g.getPrenom());
            ps.setString(5,g.getNumTel());
            ps.setString(6,g.getNumFix());
            ps.setInt(7,g.getId());
            ps.setInt(8, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Gerant> displayAllTrieName() {
        
        String requete="select * from utilisateur where role=? order by nom ";
        ArrayList<Gerant> lst=new ArrayList<Gerant>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Gerant gr=new Gerant();
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
                lst.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lst;
    }
    public List<Gerant> displayAllTriePrenom() {
        
        String requete="select * from utilisateur where role=? order by prenom";
        ArrayList<Gerant> lst=new ArrayList<Gerant>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Gerant gr=new Gerant();
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
                lst.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lst;
    }
    
    public List<Gerant> displayAllTrieMail() {
        
        String requete="select * from utilisateur where role=? order by mail";
        ArrayList<Gerant> lst=new ArrayList<Gerant>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, 1);
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Gerant gr=new Gerant();
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
                lst.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lst;
    }
    
    public ArrayList<Gerant> findInstant(String ch){
        String requete="select * from utilisateur where (nom like ? or prenom like ?) and role=?";
        ArrayList<Gerant> lst=new ArrayList<Gerant>();
        try {
            PreparedStatement ps=MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, "%"+ch+"%");
            ps.setString(2, "%"+ch+"%");
            ps.setString(3, "1");
            ResultSet resultat=ps.executeQuery();
            while(resultat.next()){
                Gerant gr=new Gerant();
                gr.setId(resultat.getInt(1));
                gr.setMail(resultat.getString(2));
                gr.setPassword(resultat.getString(3));
                gr.setNom(resultat.getString(4));
                gr.setPrenom(resultat.getString(5));
                gr.setNumTel(resultat.getString(6));
                gr.setNumFix(resultat.getString(7));
                lst.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return lst;
    }
    
}
