/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

/**
 *
 * @author seif
 */
import Entity.Administrateur;
import DAO.AdministrateurDAO;
import java.util.*;
import javax.swing.table.AbstractTableModel;

public class SearchAdminAdapter extends AbstractTableModel {

    public List<Administrateur> admins;

    public String[] header = {"Id", "Nom", "Prenom", "Mail","password" ,"privilege"};

    public SearchAdminAdapter(String ch) {

        admins = new AdministrateurDAO().findAdminByNom(ch);
    }

    public SearchAdminAdapter(List<Administrateur> admins) {
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
