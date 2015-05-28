package DAO;

import Entity.Client;
import Interface.IClientDAO;
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
public class ClientDAO implements IClientDAO {

    public ClientDAO() {

    }

    @Override
    public void insertClient(Client c) {
        String req = "insert into utilisateur (nom,prenom,mail,password,status_matrimonial,role) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getMail());
            ps.setString(4, c.getPassword());
            ps.setString(5, c.getStatusMatrimonial());
            ps.setInt(6, 0);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteClient(int id) {
        String req = "delete from utilisateur where Id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Client> displayAll() {

        String requete = "select * from utilisateur  where role=2";
        ArrayList<Client> lst = new ArrayList<Client>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public Client findClientById(int id) {
        Client c = new Client();
        String requete = "select * from utilisateur where id=?";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<Client> findClientByNom(String Nom) {

        String requete = "select * from utilisateur where role = 2 AND nom Like ?";
        ArrayList<Client> lst = new ArrayList<Client>();

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, "%" + Nom + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<Client> TrierParNom() {

        String requete = "select * from utilisateur where role = 2 order by nom";
        ArrayList<Client> lst = new ArrayList<Client>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;

    }

    public List<Client> TrierParPrenom() {

        String requete = "select * from utilisateur where role = 2 order by prenom";
        ArrayList<Client> lst = new ArrayList<Client>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;

    }

    public List<Client> TrierParMail() {

        String requete = "select * from utilisateur where role = 2 order by mail";
        ArrayList<Client> lst = new ArrayList<Client>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;

    }

    public List<Client> TrierParPassword() {

        String requete = "select * from utilisateur where role = 2 order by password";
        ArrayList<Client> lst = new ArrayList<Client>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Client c = new Client();
                c.setId(resultat.getInt(1));
                c.setMail(resultat.getString(2));
                c.setPassword(resultat.getString(3));
                c.setNom(resultat.getString(4));
                c.setPrenom(resultat.getString(5));
                c.setStatusMatrimonial(resultat.getString(8));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;

    }
}
