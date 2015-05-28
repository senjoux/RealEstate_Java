/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Archive;
import java.util.Date;
import java.util.List;

/**
 *
 * @author azerty
 */
public interface IArchive {

    public void deleteArchine(int id);

    public Archive findArchiveById(int id);

    public List<Archive> findArchiveBydate(String date);

    public void addArchive(int gerantID, Date datePub, String url);

}
