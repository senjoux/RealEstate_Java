/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entity.LoginE;
import Technique.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author FATHALLAH Wael
 */
public class LoginDAO implements Interface.ILoginDAO{
    private Connection connection;
    
    

    @Override
    public int Login(String mail, String pass) {
       String requete = "select * from administrateur where password=? AND mail=?"; 
       try{
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setString(1, pass);
            ps.setString(2, mail);
            System.out.println(mail + " " + pass);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()){
                
                return resultat.getInt(6);        
            }
            } catch (SQLException ex) {
            System.out.println(ex);
            return 404;
        }
        System.out.println("Admin not found");
        return 500;
        
    }

    @Override
    public LoginE LoginU(String mail, String pass) {
        String requete = "select * from utilisateur where mail=? AND password=?"; 
        LoginE logfind = new LoginE();
       try{
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            
            ps.setString(1, mail);
            ps.setString(2, pass);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()){
                
                logfind.setId(resultat.getInt(1));  
                logfind.setEmail(resultat.getString(2));      
                logfind.setPassword(resultat.getString(3));    
                logfind.setNom(resultat.getString(4));    
                logfind.setPrenom(resultat.getString(5)); 
                logfind.setNumMob(resultat.getInt(6));  
                logfind.setNumFix(resultat.getInt(7));  
                  
                logfind.setPriv(resultat.getInt(9));  
                logfind.setURLp(resultat.getString(10));
                  
            }
            return logfind;
            } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
 
    }

  
    
  
}
