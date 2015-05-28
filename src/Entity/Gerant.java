package Entity;

import java.util.Objects;

/**
 *
 * @author user
 */
//Il manque un attribut Agence, c'est l'agence dans laquelle est affecté ce gerant
public class Gerant extends Utilisateur {

    //Attributs
    private String numFix;
    private String numTel;

//Constructeur
    public Gerant() {
        super();
    }

    public Gerant(String num_Fix, String numTel, int id, String mail, String password, String nom, String prenom, String numMobile, String numFix, short role) {
        super(id, mail, password, nom, prenom, numMobile, numFix, role);
        this.numFix = num_Fix;
        this.numTel = numTel;
    }

    //Getters & Setters
    public String getNumFix() {
        return numFix;
    }

    public void setNumFix(String numFix) {
        this.numFix = numFix;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
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
        final Gerant other = (Gerant) obj;
        if (!Objects.equals(this.numFix, other.numFix)) {
            return false;
        }
        if (!Objects.equals(this.numTel, other.numTel)) {
            return false;
        }
        return true;
    }
//Affichage

    @Override
    public String toString() {
        return "Gerant{" + "numFix=" + numFix + ", numTel=" + numTel + '}';
    }

}
