package DAO;

import Entity.Utilisateur;
import Interface.IUtilisateurDAO;
import Technique.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UtilisateurDAO implements IUtilisateurDAO {

    public UtilisateurDAO() {
    }

    @Override
    public void insertUtilisateur(Utilisateur u) {
        String req = "insert into utilisateur values(?,?,?,?,?) ";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.setString(2, u.getNom());
            ps.setString(3, u.getPrenom());
            ps.setString(4, u.getMail());
            ps.setString(5, u.getPassword());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUtilisateur(Utilisateur u) {
        String req = "delete from utilisateur where Id=?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Utilisateur getUtilisateur(int id) {
        String sql = "SELECT * FROM `utilisateur` WHERE `id` = ?";
        try {
            PreparedStatement stmt = MyConnection.getInstance().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int uid = rs.getInt("id");
                String mail = rs.getString("mail");
                String password = rs.getString("password");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String numMobile = rs.getString("numMobile");
                String numFix = rs.getString("numFix");
                short role = rs.getShort("role");
                return new Utilisateur(uid, mail, password, nom, prenom, numMobile, numFix, role);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoiteMessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
