/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Archive;
import Interface.IArchive;
import Technique.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blanchard
 */
public class ArchiveDAO implements IArchive {

    String SQL;
    Connection connection;
    Statement statement;
    String requete;

    @Override
    public void deleteArchine(int id) {
        String req = "delete from Archive where Id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArchiveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Archive findArchiveById(int id) {
        Archive a = new Archive();
        String requete = "select * from Archive where id=?";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                a.setId(resultat.getInt(1));
                a.setId_gerant(resultat.getInt(2));
                a.setDatePublication(resultat.getString(3));
                a.setUrl(resultat.getString(4));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArchiveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ArrayList<Archive> findAllArchive() {
        ArrayList<Archive> lst = new ArrayList<Archive>();

        String requete = "select * from archive ";

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Archive a = new Archive();
                a.setId(resultat.getInt(1));
                a.setId_gerant(resultat.getInt(2));
                a.setDatePublication(resultat.getString(3));
                a.setUrl(resultat.getString(4));
                lst.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArchiveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public ArrayList<Archive> findArchiveBydate(String date) {

        String requete = "select * from Archive where date=?";
        ArrayList<Archive> lst = new ArrayList<Archive>();

        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, date);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Archive a = new Archive();
                a.setId(resultat.getInt(1));
                a.setId_gerant(resultat.getInt(2));
                a.setDatePublication(resultat.getString(3));
                a.setUrl(resultat.getString(4));

                lst.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public void addArchive(int gerantID, Date datePub, String url) {
        String req = "INSERT INTO `archive` (`id_gerant`, `date`, `url`) VALUES (?, ?, ?);";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(datePub);
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, gerantID);
            ps.setString(2, date);
            ps.setString(3, url);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArchiveDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
