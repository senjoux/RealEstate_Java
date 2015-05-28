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
public class Administrateur {
    
    //Declcaration des attribus
    protected int Id;
    protected String Nom;
    protected String Prenom;
    protected String Password;
    protected int  privilege;
    protected String mail;

    //Constructeur
    public Administrateur(){
        
    }
    public Administrateur(int Id, String Nom, String Prenom, String Password,String mail,int privilege) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Password = Password;
        this.privilege=privilege;
        this.mail=mail;
    }

  //Getters and Setters
    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getPassword() {
        return Password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public String getMail() {
        return mail;
    }
    

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }



    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
//Methode equals 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrateur other = (Administrateur) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        if (!Objects.equals(this.Prenom, other.Prenom)) {
            return false;
        }
  
        if (!Objects.equals(this.Password, other.Password)) {
            return false;
        }
        return true;
    }

    //Affichage
    @Override
    public String toString() {
        return "Administrateur{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Password=" + Password + '}';
    }
    
    
}
