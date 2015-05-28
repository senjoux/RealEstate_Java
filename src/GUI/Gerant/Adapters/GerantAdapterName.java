/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Gerant.Adapters;

import DAO.GerantDAO;
import Entity.Gerant;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ShujiX
 */
public class GerantAdapterName extends AbstractTableModel{
    List<Gerant> gerants; 
        String []header={"ID Gerant","Mail","Password","Nom","Prenom","NumMobile","NumFix"}; 

    public GerantAdapterName() {
        gerants = new GerantDAO().displayAllTrieName();
    }
    
    
    public GerantAdapterName(List<Gerant> gerants) {
        this.gerants = gerants;
    }
           
    @Override
    public int getRowCount() {
        return gerants.size();
    }

    @Override
    public int getColumnCount() {      
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return gerants.get(rowIndex).getId();
            case 1: return gerants.get(rowIndex).getMail();
            case 2: return gerants.get(rowIndex).getPassword();
            case 3: return gerants.get(rowIndex).getNom();
            case 4: return gerants.get(rowIndex).getPrenom();
            case 5: return gerants.get(rowIndex).getNumTel();
            case 6: return gerants.get(rowIndex).getNumFix();    
                default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        return header[column];
    }
    
}
