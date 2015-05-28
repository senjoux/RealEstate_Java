/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import DAO.ArchiveDAO;
import Entity.Archive;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author azerty
 */
public class ArchiveAdapterNom extends AbstractTableModel{
    
    public ArrayList<Archive> archives;

    public String[] header = {"Id", "id_gerant", "date", "url"};

    public ArchiveAdapterNom(String d) {

        archives = new ArchiveDAO().findArchiveBydate(d);
    }

    public ArchiveAdapterNom(ArrayList<Archive> archives) {
        this.archives = archives;
    }

    @Override
    public int getRowCount() {
        return archives.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {// parcour par colonne
            case 0:
                return archives.get(rowIndex).getId();
            case 1:
                return archives.get(rowIndex).getId_gerant();
            case 2:
                return archives.get(rowIndex).getDatePublication();
            case 3:
                return archives.get(rowIndex).getUrl();
           

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
