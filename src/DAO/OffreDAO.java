/*
 * The content is available under the terms of the Creative Commons 
 * Attribution-ShareAlike license (CC-BY-SA), v2.5 or any later version
 */
package DAO;

import Entity.Adresse;
import Entity.BienImmobilier;
import Entity.Offre;
import Entity.OffreX;
import Technique.MyConnection;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FATHALLAH Wael
 */
public class OffreDAO implements Interface.IOffresDAO {

    @Override
    public List<Offre> displayAll() {

        String requete = "select * from offre  ";
        ArrayList<Offre> lst = new ArrayList<Offre>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Offre o = new Offre();

                o.setId(resultat.getInt(1));

                o.setDate_publication(resultat.getDate(10));
                o.setDate_modification(resultat.getDate(11));
                o.setDisponible_date(resultat.getDate(12));
                o.setNature(resultat.getString(6));
                o.setPayement(resultat.getFloat(7));

                o.setNote(resultat.getFloat(23));
                lst.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public List<Offre> displayAllParGerant(int id) {

        String requete = "select * from offre where id_gerant=? ";
        ArrayList<Offre> lst = new ArrayList<Offre>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Offre o = new Offre();

                o.setId(resultat.getInt(1));

                o.setDate_publication(resultat.getDate(10));
                o.setDate_modification(resultat.getDate(11));
                o.setDisponible_date(resultat.getDate(12));
                o.setNature(resultat.getString(6));
                o.setPayement(resultat.getFloat(7));

                o.setNote(resultat.getFloat(23));
                lst.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public boolean deleteOffre(int id) {
        String requete = "delete from offre where id = ?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean deleteOffre1(float prix, float surface, String nature, int nbrPiece) {
        String requete = "delete from offre where payement=? and surface=? and nature=? and nbrPiece=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setFloat(1, prix);
            ps.setFloat(2, surface);
            ps.setString(3, nature);
            ps.setInt(4, nbrPiece);
            ps.executeUpdate();
            System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean insertOffre(Offre o, BienImmobilier b, Adresse a) {
        {
            String req = "insert into offre(id_gerant,id_adresse,etat,typeImmob,nature,payement,surface,nbrPiece,datePublication,dateModification,dispobileAPartir,description,etage,ascenceur,cuisineEquipe,jardin,entreeIndep,gazDeVille,chauffage,meuble,climatisation,note,UrlImage,position) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            try {
                PreparedStatement ps = MyConnection.getInstance().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, o.getGerant().getId());
                ps.setInt(2, a.getId());
                ps.setString(3, b.getEtat());
                ps.setString(4, b.getTypeImmob());
                ps.setString(5, o.getNature());
                ps.setFloat(6, o.getPayement());
                ps.setFloat(7, b.getSurface());
                ps.setInt(8, b.getNbr_piece());

                //Conversion Date_publication
                java.util.Date utilDate = new java.util.Date();
                utilDate = o.getDate_publication();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                ps.setDate(9, sqlDate);

                //Conversion Date_modification
                java.util.Date utilDate1 = new java.util.Date();
                utilDate1 = o.getDate_modification();
                java.sql.Date sqlDate1 = new java.sql.Date(utilDate.getTime());
                ps.setDate(10, sqlDate1);

                //Conversion dispoapartir
                java.util.Date utilDate2 = new java.util.Date();
                utilDate2 = o.getDisponible_date();
                java.sql.Date sqlDate2 = new java.sql.Date(utilDate.getTime());
                ps.setDate(11, sqlDate2);

                ps.setString(12, b.getDescritpion());
                ps.setInt(13, b.getEtage());
                ps.setInt(14, b.getAscenceur());
                ps.setInt(15, b.getCuisineEquipe());
                ps.setInt(16, b.getJardin());
                ps.setInt(17, b.getEntreeIndep());
                ps.setInt(18, b.getGaz_de_ville());
                ps.setInt(19, b.getChauffage());
                ps.setInt(20, b.getMeuble());
                ps.setInt(21, b.getClimatisation());
                ps.setFloat(22, o.getNote());
                ps.setString(23, o.getUrlImage());
                ps.setString(24, o.getPosition());

                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    @Override
    public Date getDate() {
        String requete = "SELECT DATE( NOW() );";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return null;
    }

    public boolean getDateupdate(int id) {
        String requete = "update offre set dateModification=DATE( NOW() ) where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean setNote(int id) {
        String requete = "update offre set note=note+1 where id=? and nom=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updatePrix(int id, float payement) {
        String requete = "update offre set payement=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setFloat(1, payement);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updateSurface(int id, float surface) {
        String requete = "update offre set surface=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setFloat(1, surface);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updateOffre(int id, Offre o) {
        String requete = "update offre set nature=?,payement=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, o.getNature());
            ps.setFloat(2, o.getPayement());

            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updateNature(int id, String nature) {
        String requete = "update offre set nature=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, nature);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updateType(int id, String typeImmob) {
        String requete = "update offre set typeImmob=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, typeImmob);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean updateNbr(int id, int nbrPiece) {
        String requete = "update offre set nbrPiece=? where id=?;";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setFloat(1, nbrPiece);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public List<Adresse> findAdresse(String ville) {

        String requete = "select * from adresse where ville Like ?";
        ArrayList<Adresse> lst = new ArrayList<Adresse>();

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, "%" + ville + "%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Adresse c = new Adresse();
                c.setId(resultat.getInt(1));
                c.setVille(resultat.getString(4));
                c.setGouvernorat(resultat.getString(2));
                c.setCode_pos(resultat.getInt(3));

                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public List<Adresse> displayAllAdresse() {

        String requete = "select * from adresse  ";
        ArrayList<Adresse> lst = new ArrayList<Adresse>();
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                Adresse c = new Adresse();
                c.setId(resultat.getInt(1));
                c.setVille(resultat.getString(4));
                c.setGouvernorat(resultat.getString(2));
                c.setCode_pos(resultat.getInt(3));
                lst.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public String getImg(int id) {
        String requete = "select * from offre where id = ?  ";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                return resultat.getString(24);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getPos(int id) {
        String requete = "select * from offre where id = ?  ";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                return resultat.getString(25);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Wael
    @Override
    public List<OffreX> getAllListOfOffreX() {
        List<OffreX> listOffre = new ArrayList<>();
        String requete = "select * from offre";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                listOffre.add(new OffreX(resultat.getInt(1), resultat.getInt(2),
                        resultat.getInt(3), resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getString(7),
                        resultat.getFloat(8), resultat.getInt(9), resultat.getString(10), resultat.getString(11), resultat.getString(12),
                        resultat.getString(13), resultat.getInt(14), resultat.getBoolean(15), resultat.getBoolean(16), resultat.getBoolean(17), resultat.getBoolean(18),
                        resultat.getBoolean(19), resultat.getBoolean(20), resultat.getBoolean(21), resultat.getBoolean(22), resultat.getInt(23), resultat.getString(24), resultat.getString(25)));
            }
            return listOffre;
        } catch (SQLException ex) {
            System.out.println("erreur");

        }
        return null;
    }

    @Override
    public OffreX getOffreByIDX(int id) {
        OffreX offreByID = new OffreX();
        String requete = "select * from offre where id = ?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                offreByID = new OffreX(resultat.getInt(1), resultat.getInt(2),
                        resultat.getInt(3), resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getString(7),
                        resultat.getFloat(8), resultat.getInt(9), resultat.getString(10), resultat.getString(11), resultat.getString(12),
                        resultat.getString(13), resultat.getInt(14), resultat.getBoolean(15), resultat.getBoolean(16), resultat.getBoolean(17), resultat.getBoolean(18),
                        resultat.getBoolean(19), resultat.getBoolean(20), resultat.getBoolean(21), resultat.getBoolean(22), resultat.getInt(23), resultat.getString(24), resultat.getString(25));
                System.out.println("done");
            }
            return offreByID;
        } catch (SQLException ex) {
            System.out.println("erreur");

        }
        return null;
    }

    //+Search+" or typeImmob = "+Search+" or nature = "+Search
    @Override
    public List<OffreX> getOffreByStringX(String Search) {
        List<OffreX> listOffre = new ArrayList<>();
        String requete = "select * from offre where etat = ? or typeImmob = ? or nature = ? or surface=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, Search);
            ps.setString(2, Search);
            ps.setString(3, Search);
            try {
                ps.setFloat(4, Float.parseFloat(Search));
            } catch (NumberFormatException nfe) {
                ps.setFloat(4, 0.0f);
            }
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {

                listOffre.add(new OffreX(resultat.getInt(1), resultat.getInt(2),
                        resultat.getInt(3), resultat.getString(4), resultat.getString(5), resultat.getString(6), resultat.getString(7),
                        resultat.getFloat(8), resultat.getInt(9), resultat.getString(10), resultat.getString(11), resultat.getString(12),
                        resultat.getString(13), resultat.getInt(14), resultat.getBoolean(15), resultat.getBoolean(16), resultat.getBoolean(17), resultat.getBoolean(18),
                        resultat.getBoolean(19), resultat.getBoolean(20), resultat.getBoolean(21), resultat.getBoolean(22), resultat.getInt(23), resultat.getString(24), resultat.getString(25)));
                System.out.println("done");
            }
            return listOffre;
        } catch (SQLException ex) {
            System.out.println("erreur");

        }
        return null;
    }

    @Override
    public boolean deleteOffreX(int id) {
        String requete = "delete from offre where id = ?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Delete Done");

        } catch (SQLException ex) {
            System.out.println("Erreur");
        }
        return false;
    }

    @Override
    public boolean apdateNoteOffreX(int id, float note, int myID) {
        int numderX =0;
        String requeteTest = "select * from vote where idOffre = ? AND idUtilisateur = ?";
        
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requeteTest);
            ps.setInt(1, id);
            ps.setInt(2, myID);
            ResultSet resultat = ps.executeQuery();
            
            
            while (resultat.next()) {
                numderX=resultat.getInt(1);
                
                
                
            }
        } catch (SQLException ex) {
            System.out.println("erreur");
            return false;
            
        }
        if(numderX == 0){
            String requete = "insert into vote (idOffre, idUtilisateur, note)"
                    + "VALUES (?, ?, ?)";
            try {
                PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
                
                ps.setInt(1, id);
                ps.setInt(2, myID);
                ps.setInt(3, (int) note);
                
                
                ps.executeUpdate();
                System.out.println("rating Done");
                return true;
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
        }else{
            String requete = "UPDATE vote SET note = ? WHERE id = ?";
            
            try {
                PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
                
                ps.setInt(1, (int) note);

                ps.setInt(2, numderX);
                ps.executeUpdate();
                System.out.println("Update Done");
                System.out.println(note);
                System.out.println(numderX);
                return true;
                
            } catch (SQLException ex) {
                System.out.println("Erreur");
            }
            
        }
        return false;
        
        
    }

    @Override
    public float getNoteOffreX(int id) {
        String requete = "select * from vote where idOffre = ?";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            int numderX =0;
            int noteX =0;
            while (resultat.next()) {
                numderX+=1;
                noteX+=resultat.getInt(4);
                

            }if(numderX==0){
                return 1;
            }else{
            return noteX/numderX;}
        } catch (SQLException ex) {
            System.out.println("erreur");

        }
        return 0;

    }

}
