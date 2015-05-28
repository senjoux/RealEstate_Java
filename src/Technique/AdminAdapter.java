/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import DAO.AdministrateurDAO;
import Entity.Administrateur;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class AdminAdapter extends AbstractTableModel {

    public List<Administrateur> admins;

    public String[] header = {"Id", "Nom", "Prenom", "Mail", "password", "privilege"};

    public AdminAdapter() {

        admins = new AdministrateurDAO().displayAll();
    }

    public AdminAdapter(List<Administrateur> admins) {
        this.admins = admins;
    }

    @Override
    public int getRowCount() {
        return admins.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {// parcour par colonne
            case 0:
                return admins.get(rowIndex).getId();
            case 1:
                return admins.get(rowIndex).getNom();
            case 2:
                return admins.get(rowIndex).getPrenom();
            case 3:
                return admins.get(rowIndex).getMail();
            case 4:
                return admins.get(rowIndex).getPassword();
            case 5:
                return admins.get(rowIndex).getPrivilege();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) { // nom des colonnes
        return header[column];
    }

    boolean[] canEdit = new boolean[]{
        false, true, true
    };

}
