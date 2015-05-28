/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Administrateur;
import java.util.List;

/**
 *
 * @author seif
 */

public interface IsuperAdminDAO {
    public void insertAdmin(Administrateur ad);
    public void deleteAdmin(int id);
    public List<Administrateur> displayAll();
    public Administrateur findAdminById(int id);
}
