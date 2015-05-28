/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Agence.Adapters;

import DAO.AdresseDAO;
import DAO.AgenceDAO;
import Entity.Adresse;
import Entity.Agence;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ShujiX
 */
public class AgenceAdapter extends AbstractTableModel{
    List<Agence> agences; 
    String []header={"ID Agence","Nom","Description"};
    
    public AgenceAdapter(){
         agences = new AgenceDAO().displayAllNoAD();
    }
    
    public AgenceAdapter(List<Agence> agences) {
        this.agences = agences;
    }
    @Override
    public int getRowCount() {
        return agences.size(); 
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return agences.get(rowIndex).getId();
            case 1: return agences.get(rowIndex).getNom();
            case 2: return agences.get(rowIndex).getDescription();
                default: return null;
        }    
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
    
}
