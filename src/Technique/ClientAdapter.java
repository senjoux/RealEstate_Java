/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Technique;

import DAO.ClientDAO;
import Entity.Client;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */


public class ClientAdapter extends AbstractTableModel 
{
  List<Client> clients ;
    
    String []header = {"Id","Nom","Prenom","Mail","Password","statusMatrimonial"};
    
    public ClientAdapter() {

        clients = new ClientDAO().displayAll();
    }

    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }
  @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {// parcour par colonne
        case 0:
             return clients.get(rowIndex).getId();
        case 1:
            return  clients.get(rowIndex).getNom();
        case 2:
            return clients.get(rowIndex).getPrenom();
        case 3:
            return clients.get(rowIndex).getMail();
             case 4:
            return clients.get(rowIndex).getPassword();
            case 5:
            return clients.get(rowIndex).getStatusMatrimonial();
                
          default:
                return null;
        }
    }
     @Override
    public String getColumnName(int column) { // nom des colonnes
        return header[column]; 
    }
  
     
     boolean[] canEdit= new boolean[]
     {
         false,true,true
     };

    
}
    
    
    
    
    
