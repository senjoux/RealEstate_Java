/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.util.Objects;

/**
 *
 * @author ulrich blanchard
 */
public class Media {
    
    //Attribus
    private int Id;
    private String Nom;
    private String Type;
    private String Url;

    //Constructeur
    public Media(int Id, String Nom, String Type, String Url) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Url = Url;
    }
    
    //Getters & Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
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
        final Media other = (Media) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.Url, other.Url)) {
            return false;
        }
        return true;
    }

//Affichage
    @Override
    public String toString() {
        return "Media{" + "Id=" + Id + ", Nom=" + Nom + ", Type=" + Type + ", Url=" + Url + '}';
    }
    

}
