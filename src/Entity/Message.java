package Entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Elyes
 */
public class Message {

    private long id;
    private String contenu;
    private short vu;
    private int id_expediteur;
    private int id_destinataire;
    private String temps_envoi;
    private long date;

    public Message() {
    }

    public Message(long id, int id_expediteur, int id_destinataire, String contenu, short vu, Timestamp time) {
        this.id = id;
        this.contenu = contenu;
        this.vu = vu;
        this.id_expediteur = id_expediteur;
        this.id_destinataire = id_destinataire;
        this.temps_envoi = time.toString();
        this.date = time.getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public short getVu() {
        return vu;
    }

    public void setVu(short vu) {
        this.vu = vu;
    }

    public int getId_expediteur() {
        return id_expediteur;
    }

    public void setId_expediteur(int id_expediteur) {
        this.id_expediteur = id_expediteur;
    }

    public int getId_destinataire() {
        return id_destinataire;
    }

    public void setId_destinataire(int id_destinataire) {
        this.id_destinataire = id_destinataire;
    }

    public String getTemps_envoi() {
        return temps_envoi;
    }

    public void setTemps_envoi(String temps_envoi) {
        this.temps_envoi = temps_envoi;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
