/*
 * The content is available under the terms of the Creative Commons 
 * Attribution-ShareAlike license (CC-BY-SA), v2.5 or any later version
 */

package Interface;

import Entity.Adresse;
import Entity.BienImmobilier;
import Entity.Offre;
import Entity.OffreX;
import java.util.Date;
import java.util.List;

/**
 *
 * @author FATHALLAH Wael
 */
public interface IOffresDAO {
            public  List<Offre> displayAll();
            public  List<Offre> displayAllParGerant(int id); 
            public boolean deleteOffre(int id);
            public boolean deleteOffre1(float prix,float surface,String nature,int nbrPiece);
            public boolean insertOffre(Offre o,BienImmobilier b,Adresse a);
            public  Date getDate();
            public boolean setNote(int id);
            public boolean updatePrix(int id ,float payement);
            public boolean updateSurface(int id ,float surface);
            public boolean updateOffre(int id ,Offre o);
            public boolean updateNature(int id ,String nature);
            public boolean updateType(int id ,String typeImmob);
            public boolean updateNbr(int id ,int nbrPiece);
            public List<Adresse> findAdresse(String ville);
            public  List<Adresse> displayAllAdresse();
            public String getImg(int id);
            public String getPos(int id);
                 public List<OffreX> getAllListOfOffreX();
        public List<OffreX> getOffreByStringX(String Search);
        public OffreX getOffreByIDX(int id);
        public boolean deleteOffreX(int id);        
        public boolean apdateNoteOffreX(int id, float note, int myID);
        public float getNoteOffreX(int id);
}

