package Interface;

import Entity.BoiteMessages;

/**
 *
 * @author Elyes
 */
public interface IBoiteMessageDAO {
    public boolean envoyerMessage(int id_expediteur, int id_destinataire, String contenu);
    public BoiteMessages getBoiteMessages(int id_proprietaire, boolean unreadOnly);
    public void setVu(long id);
    public void supprimerMessage(long id);
}
