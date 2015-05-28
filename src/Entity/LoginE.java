/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

/**
 *
 * @author FATHLLAH Wael
 */
public class LoginE {
    int id;
    String email; 
    String password;
    String nom;
    String prenom; 
    int numMob;
    int numFix; 
    int priv; 
    String URLp;
    public LoginE(){}
    public LoginE(int id,String email, String password, String nom, String prenom, int numMob, int numFix, int priv, String URLp) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.numMob = numMob;
        this.numFix = numFix;
        this.priv = priv;
        this.URLp = URLp;
    }

    @Override
    public String toString() {
        return "LoginE{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", numMob=" + numMob + ", numFix=" + numFix + ", priv=" + priv + ", URLp=" + URLp + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getNumMob() {
        return numMob;
    }

    public void setNumMob(int numMob) {
        this.numMob = numMob;
    }

    public int getNumFix() {
        return numFix;
    }

    public void setNumFix(int numFix) {
        this.numFix = numFix;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public String getURLp() {
        return URLp;
    }

    public void setURLp(String URLp) {
        this.URLp = URLp;
    }
    
}
