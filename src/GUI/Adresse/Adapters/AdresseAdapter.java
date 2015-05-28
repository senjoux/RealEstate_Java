/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Adresse.Adapters;

import DAO.AdresseDAO;
import DAO.GerantDAO;
import Entity.Adresse;
import Entity.Gerant;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ShujiX
 */
@SuppressWarnings("UnusedDeclaration")
public class AdresseAdapter extends AbstractTableModel{
    
    List<Adresse> adresses; 
    String []header={"ID Adresse","Gouvernorat","CodePostal","Ville"};
        
    public AdresseAdapter(){
         adresses = new AdresseDAO().displayAll();
    }

    public AdresseAdapter(List<Adresse> adresses) {
        this.adresses = adresses;
    }
        
    @Override
    public int getRowCount() {
        return adresses.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return adresses.get(rowIndex).getId();
            case 1: return adresses.get(rowIndex).getGouvernorat();
            case 2: return adresses.get(rowIndex).getCode_pos();
            case 3: return adresses.get(rowIndex).getVille();
                default: return null;
        }
    }
 
    @Override
    public String getColumnName(int column){
        return header[column];
    }
}
