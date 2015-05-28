package Entity;

/**
 *
 * @author user
 */
public class Utilisateur {

    private int id;
    private String mail;
    private String password;
    private String nom;
    private String prenom;
    private String numMobile;
    private String numFix;
    private short role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String mail, String password, String nom, String prenom, String numMobile, String numFix, short role) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.numMobile = numMobile;
        this.numFix = numFix;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumMobile() {
        return numMobile;
    }

    public void setNumMobile(String numMobile) {
        this.numMobile = numMobile;
    }

    public String getNumFix() {
        return numFix;
    }

    public void setNumFix(String numFix) {
        this.numFix = numFix;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "Id=" + id + ", Nom=" + nom + ", Prenom=" + prenom + ", Mail=" + mail + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
