package DAO;

import Entity.BoiteMessages;
import Entity.Message;
import Technique.MyConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elyes
 */
public class BoiteMessageDAO implements Interface.IBoiteMessageDAO {

    public BoiteMessageDAO() {
    }

    @Override
    public boolean envoyerMessage(int id_expediteur, int id_destinataire, String contenu) {
        String sql = "INSERT INTO `boitemessages` (`id`, `id_expediteur`, `id_destinataire`, `contenu`, `vu`, `temps_envoi`) VALUES (NULL, ?, ?, ?, '0', CURRENT_TIMESTAMP());";
        try {
            PreparedStatement stmt = MyConnection.getInstance().prepareStatement(sql);
            stmt.setInt(1, id_expediteur);
            stmt.setInt(2, id_destinataire);
            stmt.setString(3, contenu);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BoiteMessageDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public BoiteMessages getBoiteMessages(int id_proprietaire,boolean unreadOnly) {
        String condition = "";
        if(unreadOnly == true) condition = "AND `vu` = 0";
        String sql = "SELECT * FROM `boitemessages` WHERE `id_destinataire` = ? " + condition + " ORDER BY `temps_envoi` DESC";
        BoiteMessages B = new BoiteMessages();
        try {
            PreparedStatement stmt = MyConnection.getInstance().prepareStatement(sql);
            stmt.setInt(1, id_proprietaire);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                int id_expediteur = rs.getInt("id_expediteur");
                int id_destinataire = rs.getInt("id_destinataire");
                short vu = rs.getShort("vu");
                String contenu = rs.getString("contenu");
                Timestamp time = rs.getTimestamp("temps_envoi");
                B.ajouterMessage(new Message(id, id_expediteur, id_destinataire, contenu, vu, time));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoiteMessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return B;
    }

    @Override
    public void setVu(long id) {
        String sql = "UPDATE `boitemessages` SET `vu` = '1' WHERE `boitemessages`.`id` = ?";
        try {
            PreparedStatement stmt = MyConnection.getInstance().prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BoiteMessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerMessage(long id) {
        String sql = "DELETE FROM `boitemessages` WHERE `id` = ?;";
        try {
            PreparedStatement stmt = MyConnection.getInstance().prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BoiteMessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
