/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.util.Date;



/**
 *
 * @author user
 */
public class Offre {
    
    //Attribus
    private int id;
    private float payement;
    private String nature;        
    private float note;
    private Date disponible_date;
    private Date date_publication;
    private Date date_modification;
    private Gerant gerant;
    private BienImmobilier bienImmob;
    
   /*     private int     Id;
    private int     id_gerant;
    private int     id_adresse;
    private String  etat;
    private String  typeImmob;
    private String  payement;
    private float    surface;
    private int     nbrPiece;
    private String  datePublication;
    private String  dateModification;
    private String  dispobileAPartir;
    private String  description;
    private int     etage;
    private boolean ascenceur;
    private boolean cuisineEquipe;
    private boolean jardin;
    private boolean entreeIndep;
    private boolean gazDeVille;
    private boolean chauffage;
    private boolean meuble;
    private boolean climatisation;
    */
    private String  urlImage;
    private String  position;


    public Offre() {
    }

    public Offre(int id, float payement, String nature, float note, Date disponible_date, Date date_publication, Date date_modification, Gerant gerant, BienImmobilier bienImmob ,String urlImage,String position) {
        this.id = id;
        this.payement = payement;
        this.nature = nature;
        this.note = note;
        this.disponible_date = disponible_date;
        this.date_publication = date_publication;
        this.date_modification = date_modification;
        this.gerant = gerant;
        this.bienImmob = bienImmob;
        this.urlImage=urlImage;
        this.position=position;
    }

    public Offre(float payement, String nature, float note, Date disponible_date, Date date_publication, Date date_modification, Gerant gerant, BienImmobilier bienImmob,String urlImage,String position) {
        this.payement = payement;
        this.nature = nature;
        this.note = note;
        this.disponible_date = disponible_date;
        this.date_publication = date_publication;
        this.date_modification = date_modification;
        this.gerant = gerant;
        this.bienImmob = bienImmob;
         this.urlImage=urlImage;
        this.position=position;
    }



 

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    
    
    public BienImmobilier getBienImmob() {
        return bienImmob;
    }

    public void setBienImmob(BienImmobilier bienImmob) {
        this.bienImmob = bienImmob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPayement() {
        return payement;
    }

    public void setPayement(float payement) {
        this.payement = payement;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Date getDisponible_date() {
        return disponible_date;
    }

    public void setDisponible_date(Date disponible_date) {
        this.disponible_date = disponible_date;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public Date getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(Date date_modification) {
        this.date_modification = date_modification;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", prix=" + payement + ", nature=" + nature + ", note=" + note + ", disponible_date=" + disponible_date + ", date_publication=" + date_publication + ", date_modification=" + date_modification + '}';
    }

    
    
}
