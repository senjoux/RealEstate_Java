package Entity;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Client extends Utilisateur {

    //Attribut
    private String statusMatrimonial;

    //Constructeur
    public Client() {

    }

    public Client(String statusMatrimonial, int id, String mail, String password, String nom, String prenom, String numMobile, String numFix, short role) {
        super(id, mail, password, nom, prenom, numMobile, numFix, role);
        this.statusMatrimonial = statusMatrimonial;
    }

    //Getters & Setters
    public String getStatusMatrimonial() {
        return statusMatrimonial;
    }

    public void setStatusMatrimonial(String statusMatrimonial) {
        this.statusMatrimonial = statusMatrimonial;
    }

    //Affichag
    @Override
    public String toString() {
        return "Client{" + "statusMatrimonial=" + statusMatrimonial + '}';
    }

//Equals
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.statusMatrimonial, other.statusMatrimonial)) {
            return false;
        }
        return true;
    }

}
