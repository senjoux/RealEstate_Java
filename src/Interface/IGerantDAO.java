/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Entity.Gerant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ShujiX
 */
public interface IGerantDAO {
    
    public void insertGerant(Gerant g);
    public void deleteGerant(int id);
    public void updateGerant(Gerant g);
    public List<Gerant> displayAll();
    public Gerant findGerantById(int id);
    public List<Gerant> displayAllTrieName();
    public List<Gerant> displayAllTriePrenom();
    public List<Gerant> displayAllTrieMail();
    public ArrayList<Gerant> findInstant(String ch);
    
    
}
