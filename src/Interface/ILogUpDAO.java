/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

/**
 *
 * @author FATHLLAH Wael
 */
public interface ILogUpDAO {
    
    public boolean logUPG(String email, String password, String nom, String prenom, int numMob, int numFix, String status_matrimonial, int priv, String URLp);
    public boolean logUPC(String email, String password, String nom, String prenom, String status_matrimonial, int priv, String URLp);
    
}
