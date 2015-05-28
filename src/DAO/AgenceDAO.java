package DAO;

import Entity.Adresse;
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
public class AgenceDAO implements Interface.IAgenceDAO {

    //pour inserer une agence, il faut choisir une adresse de la liste des adresse et un gerant de la liste des gerants  
    public void insertAgence(Agence ag) {
        String req = "insert into agence (id_gerant,id_adresse,nom,description)values(?,?,?,?)";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, ag.getGerant().getId());
            ps.setInt(2, ag.getAdresse().getId());
            ps.setString(3, ag.getNom());
            ps.setString(4, ag.getDescription());
            ps.execute();
            System.out.println("Insertion Agence effectuée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAgence(int id) {
        String req = "delete from agence where id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression Agence avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(Agence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Agence> displayAll() {

        String requete = "select * from agence";
        ArrayList<Agence> lst = new ArrayList<Agence>();
        Adresse addr = new Adresse();
        Gerant gr = new Gerant();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            AdresseDAO adresseDAO = new AdresseDAO();
            GerantDAO gerantDAO = new GerantDAO();
            while (resultat.next()) {
                Agence agence = new Agence();
                agence.setId(resultat.getInt(1));
                agence.setGerant(gerantDAO.findGerantById(resultat.getInt(2)));
                agence.setAdresse(adresseDAO.findAdresseById(resultat.getInt(3)));
                agence.setNom(resultat.getString(4));
                agence.setDescription(resultat.getString(5));
                lst.add(agence);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<Agence> displayAllNoAD() {

        String requete = "select * from agence";
        ArrayList<Agence> lst = new ArrayList<Agence>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Agence agence = new Agence();
                agence.setId(resultat.getInt(1));
                agence.setNom(resultat.getString(4));
                agence.setDescription(resultat.getString(5));
                lst.add(agence);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public void updateAgence(Agence ag) {
        String req = "update agence set nom=?,description=? where id=? ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, ag.getNom());
            ps.setString(2, ag.getDescription());
            ps.setInt(3, ag.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Agence findAgenceById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int findExistGerant(int id_g) {
        Gerant gr = new Gerant();
        String requete = "select * from agence where id_gerant=? ";
        int x = 0;
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id_g);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                x++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
