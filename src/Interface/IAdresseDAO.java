/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Entity.Adresse;
import Entity.Agence;
import java.util.List;

/**
 *
 * @author ShujiX
 */
public interface IAdresseDAO {
    public List<Adresse> displayAll();
    public Adresse findAdresseById(int id);
    public List<Adresse> findInstant(String ch);
}
