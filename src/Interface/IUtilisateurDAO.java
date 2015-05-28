package Interface;

import Entity.Utilisateur;

/**
 *
 * @author user
 */
public interface IUtilisateurDAO {
    public void insertUtilisateur(Utilisateur u);
    public void deleteUtilisateur(Utilisateur u);
    public Utilisateur getUtilisateur(int id);
}
