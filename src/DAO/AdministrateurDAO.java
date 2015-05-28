/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Administrateur;
import Interface.IAdministrateurDAO;
import Technique.MyConnection;
import java.sql.Connection;
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
public class AdministrateurDAO implements Interface.IAdministrateurDAO {

    @SuppressWarnings("UnusedDeclaration")
    private Connection connection;

    @Override
    public Administrateur signIn(String mail, String pass) {

        String requete = "select * from administrateur where mot_de_passe=? AND mail=?";
        Administrateur c = new Administrateur();

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, pass);
            ps.setString(2, mail);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(5));
                c.setPassword(resultat.getString(4));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setPrivilege(resultat.getInt(6));
                return c;
            }
        } catch (SQLException ex) {

        }
        return null;
    }

    @Override
    public void insertAdmin(Administrateur ad) {
        String req = "insert into Administrateur (nom,prenom,mot_de_passe,mail,privilege) values(?,?,?,?,?) ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, ad.getNom());
            ps.setString(2, ad.getPrenom());
            ps.setString(3, ad.getPassword());
            ps.setString(4, ad.getMail());
            ps.setInt(5, ad.getPrivilege());
            ps.execute();
        } catch (SQLException ex) {

        }
    }

    public List<Administrateur> findAdminByNom(String Nom) {

        String requete = "select * from administrateur where nom Like ?";
        ArrayList<Administrateur> lst = new ArrayList<Administrateur>();

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, "%" + Nom + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Administrateur c = new Administrateur();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(5));
                c.setPassword(resultat.getString(4));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setPrivilege(resultat.getInt(6));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public void deleteAdmin(int id) {
        String req = "delete from administrateur where Id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    @Override
    public List<Administrateur> displayAll() {
        String requete = "select * from administrateur";
        ArrayList<Administrateur> lst = new ArrayList<Administrateur>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Administrateur c = new Administrateur();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(5));
                c.setPassword(resultat.getString(4));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setPrivilege(resultat.getInt(6));
                lst.add(c);
            }
        } catch (SQLException ex) {

        }
        return lst;
    }

    @Override
    public Administrateur findAdminById(int id) {
        Administrateur c = new Administrateur();
        String requete = "select * from utilisateur where id=?";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(5));
                c.setPassword(resultat.getString(4));
                c.setNom(resultat.getString(2));
                c.setPrenom(resultat.getString(3));
                c.setPrivilege(resultat.getInt(6));
            }
        } catch (SQLException ex) {

        }
        return c;
    }

        public void updateAdministrateur(Administrateur admin) {
        String req = "update administrateur set nom=?,prenom=?,mot_de_passe=?,mail=?,privilege=? where id=? ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, admin.getNom());
            ps.setString(2, admin.getPrenom());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getMail());
            ps.setInt(5,admin.getPrivilege());
            ps.setInt(6,admin.getId());
            ps.executeUpdate();
            System.out.println(ps);
        } catch (SQLException ex) {
            System.out.println("erreur de mise a jour");
        }
    }
}
