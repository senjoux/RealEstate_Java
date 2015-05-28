/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author seif
 */
public class SuperAdmin extends Administrateur {

    public SuperAdmin(int Id, String Nom, String Prenom, String Password,String mail) {
        super(Id, Nom, Prenom, Password,mail,77);     
    }
    
    
    
}
