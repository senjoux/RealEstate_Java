/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;


/**
 *
 * @author user
 */
public class BienImmobilier {
    
    //Attribus
    protected int id;
    protected String descritpion;
    protected float surface;
    protected String typeImmob;
    protected int nbr_piece;
    protected String etat;
    protected int meuble;
    protected int jardin;
    protected int etage;
    protected int gaz_de_ville;
    protected int entreeIndep;
    protected int cuisineEquipe;
    protected int climatisation;
    protected int chauffage;
    protected int ascenceur;
    protected Adresse adresse;
      
    //constructeur vide
    public BienImmobilier() {
    }
    
    
    public BienImmobilier(int id, float surface, String etat, Adresse adresse) {
        this.id = id;
        this.surface = surface;
        this.etat = etat;
        this.adresse = adresse;
    }
    
   
    public BienImmobilier(float surface, String etat, Adresse adresse) {
        this.surface = surface;
        this.etat = etat;
        this.adresse = adresse;
    }

    public BienImmobilier(int id, String descritpion, float surface, String etat, Adresse adresse) {
        this.id = id;
        this.descritpion = descritpion;
        this.surface = surface;
        this.etat = etat;
        this.adresse = adresse;
    }

    public BienImmobilier(String descritpion, float surface, String etat, Adresse adresse) {
        this.descritpion = descritpion;
        this.surface = surface;
        this.etat = etat;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public String getTypeImmob() {
        return typeImmob;
    }

    public void setTypeImmob(String typeImmob) {
        this.typeImmob = typeImmob;
    }

    public int getNbr_piece() {
        return nbr_piece;
    }

    public void setNbr_piece(int nbr_piece) {
        this.nbr_piece = nbr_piece;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getMeuble() {
        return meuble;
    }

    public void setMeuble(int meuble) {
        this.meuble = meuble;
    }

    public int getJardin() {
        return jardin;
    }

    public void setJardin(int jardin) {
        this.jardin = jardin;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getGaz_de_ville() {
        return gaz_de_ville;
    }

    public void setGaz_de_ville(int gaz_de_ville) {
        this.gaz_de_ville = gaz_de_ville;
    }

    public int getEntreeIndep() {
        return entreeIndep;
    }

    public void setEntreeIndep(int entreeIndep) {
        this.entreeIndep = entreeIndep;
    }

    public int getCuisineEquipe() {
        return cuisineEquipe;
    }

    public void setCuisineEquipe(int cuisineEquipe) {
        this.cuisineEquipe = cuisineEquipe;
    }

    public int getClimatisation() {
        return climatisation;
    }

    public void setClimatisation(int climatisation) {
        this.climatisation = climatisation;
    }

    public int getChauffage() {
        return chauffage;
    }

    public void setChauffage(int chauffage) {
        this.chauffage = chauffage;
    }

    public int getAscenceur() {
        return ascenceur;
    }

    public void setAscenceur(int ascenceur) {
        this.ascenceur = ascenceur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    
    @Override
    public String toString() {
        return "BienImmobilier{" + "id=" + id + ", descritpion=" + descritpion + ", surface=" + surface + ", typeImmob=" + typeImmob + ", nbr_piece=" + nbr_piece + ", etat=" + etat + ", meuble=" + meuble + ", jardin=" + jardin + ", etage=" + etage + ", gaz_de_ville=" + gaz_de_ville + ", entreeIndep=" + entreeIndep + ", cuisineEquipe=" + cuisineEquipe + ", climatisation=" + climatisation + ", chauffage=" + chauffage + ", ascenceur=" + ascenceur +  '}';
    }
    
    
    

   

    
}
