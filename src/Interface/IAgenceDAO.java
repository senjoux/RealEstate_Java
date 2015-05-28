/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Entity.Adresse;
import Entity.Agence;
import Entity.Gerant;
import java.util.List;

/**
 *
 * @author ShujiX
 */
public interface IAgenceDAO {
    public void insertAgence(Agence ag);
    public void deleteAgence(int id);
    public void updateAgence(Agence ag);
    public List<Agence> displayAll();
    public Agence findAgenceById(int id);
    
}
