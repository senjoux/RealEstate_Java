/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Agence {
    
  //Attribus
    private int id;
    private String nom;
    private String description;
    private Adresse adresse;
    private Gerant gerant;
    //Constructeur
    public Agence() {
    }

    public Agence(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Agence(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse.toString() + ", gerant=" + gerant.toString() + '}';
    }
    
    
}
