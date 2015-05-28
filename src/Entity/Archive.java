/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author azerty
 */
public class Archive {
    
    private int id;
    private int id_gerant;
    private String url;
    private String datePublication;

    public Archive() {
    }

    public Archive(int id, int id_gerant, String datePublication, String url) {
        this.id = id;
        this.id_gerant = id_gerant;
        this.url = url;
        this.datePublication = datePublication;
    }

    public String getDatePublication() {
        return datePublication;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_gerant() {
        return id_gerant;
    }

    public void setId_gerant(int id_gerant) {
        this.id_gerant = id_gerant;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
    
  
    
    
    
}
