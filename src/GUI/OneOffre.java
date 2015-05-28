/*
 * The content is available under the terms of the Creative Commons 
 * Attribution-ShareAlike license (CC-BY-SA), v2.5 or any later version
 */
package GUI;

import DAO.OffreDAO;
import Entity.OffreX;
import static GUI.Main.fm;
import static GUI.Main.gf2;
import static GUI.Main.gfl;
import Technique.MapX;
import Technique.StarRater;
import com.alee.extended.image.WebDecoratedImage;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.progress.WebStepProgress;
import com.alee.global.StyleConstants;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.toolbar.WhiteSpace;
import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationListener;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.notification.NotificationOption;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author FATHALLAH Wael
 */
public class OneOffre {

    public static JFrame ListOfOffresFrame;
    final static JFrame prog = new JFrame();
    final static JPanel f = new JPanel();
    final static WebPanel f2 = new WebPanel();
    static int nbrpage =1;
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
//                WebLookAndFeel.install();
//                WebLookAndFeel.setDecorateAllWindows(true);
                openPleaseWait();
//                try {
//                    openListOfOffresFrame();
//                } catch (IOException ex) {
//                }
            }
        });
    }

    // Create Single offres panel
    static WebPanel createListOfOffresPanel(int id, int myId) throws IOException, InterruptedException {
        WebPanel panel = new WebPanel();
        //Geting data from database
        OffreX tt = new OffreDAO().getOffreByIDX(id);
        System.out.println(tt.toString());
        //int Id, int id_gerant, int id_adresse, String etat, String typeImmob, String nature, String payement, 
        //float surface, int nbrPiece, String datePublication, String dateModification, String dispobileAPartir, 
        //String description, int etage, boolean ascenceur, boolean cuisineEquipe, boolean jardin, boolean entreeIndep, 
        //boolean gazDeVille, boolean chauffage, boolean meuble, boolean climatisation, float note, String urlImage
        
        final GroupPanel ListOfOffrePanel = new GroupPanel(GroupingType.fillFirstAndLast, 5, false);
       ListOfOffrePanel.add(new GroupPanel(GroupingType.fillFirstAndLast, 5, new WhiteSpace(), new WhiteSpace(10),
                    createOffrePanel(tt.getUrlImage(),tt.getEtat(), tt.getNature(), tt.getTypeImmob(), tt.getPayement(), tt.getSurface(),
                            tt.getNbrPiece(), tt.getEtage(), tt.isAscenceur(), tt.isChauffage(), tt.isClimatisation(), tt.isCuisineEquipe(), 
                            tt.isEntreeIndep(), tt.isGazDeVille(), tt.isJardin(), tt.isMeuble(), tt.getDescription(), tt.getPosition(),id, tt.getId_gerant(),myId),
                    new WhiteSpace(10)));
       ListOfOffrePanel.setMargin(20, 20, 20, 20);

        // List of offre Panel configuration 
        panel.setRound(StyleConstants.largeRound);
        panel.setAutoscrolls(false);
        panel.setPreferredSize(new Dimension(300, 350));
        panel.setMaximumSize(new Dimension(300, 350));
        WebScrollPane bb = new WebScrollPane(ListOfOffrePanel);
        //bb.setBackground(Color.getHSBColor(237, 237, 237));
        bb.setOpaque(false);
        WebPanel panel1 = new WebPanel();
        panel1.add(bb);
        return panel1;
    }

    // Create Single offres panel
    protected static WebPanel createOffrePanel(String urlImage, String etat, String nature, String typeImmob, 
                                              String payement, float surface, int nbrPiece, int etage, boolean ascenceur, 
                                              boolean chauffage, boolean climatisation, boolean cuisineEquipe, boolean entreeIndep, 
                                              boolean gazDeVille, boolean jardin, boolean meuble, String description, final String position, final int id, final int id_Ger, final int myId) throws IOException {

        final WebPanel offresPanelOne = new WebPanel(true);
        offresPanelOne.setPaintFocus(true);
        System.out.println(nature);
        final WebPanel descPanel = new WebPanel(false);
         
        GroupPanel OffrePanel = new GroupPanel(GroupingType.fillFirst, 5, false);
       
        descPanel.setOpaque(true);
        // Nature de l'offre
        GroupPanel naturePanel = new GroupPanel();
        WebLabel natureLabel = new WebLabel("Nature de l'offre");
        final WebRadioButton radioButtonLoc = new WebRadioButton ( "Location" );
        radioButtonLoc.setEnabled(false);
        if(nature.equals("Location")) {
            radioButtonLoc.setSelected(true);
            radioButtonLoc.setEnabled(true);}
        
        final WebRadioButton radioButtonVe = new WebRadioButton ( "Vente" );   
        radioButtonVe.setEnabled(false);
        if(nature.equals("Vente")) {
            radioButtonVe.setSelected(true);
            radioButtonVe.setEnabled(true);}
        
        ButtonGroup groupNature = new ButtonGroup();
        groupNature.add(radioButtonLoc);
        groupNature.add(radioButtonVe);
        naturePanel=new GroupPanel(GroupingType.fillFirst, 5,false,natureLabel,radioButtonLoc,radioButtonVe);
        naturePanel.setMargin(50, 50, 50, 50);
        
        //Etat OffreX
        GroupPanel etatPanel = new GroupPanel();
        
        WebLabel etatLabel = new WebLabel("Etat de l'offre");
        final WebRadioButton radioButtonNouv = new WebRadioButton ( "Nouveau" );  
        radioButtonNouv.setEnabled(false);
        if(etat.equals("Nouveau")) {
            radioButtonNouv.setSelected(true);
            radioButtonNouv.setEnabled(true);  }
        
        final WebRadioButton radioButtonBon = new WebRadioButton ( "Bon" );  
        radioButtonBon.setEnabled(false);
        if(etat.equals("Bon")) {
            radioButtonBon.setSelected(true);
            radioButtonBon.setEnabled(true);   }
        
        final WebRadioButton radioButtonMauv = new WebRadioButton ( "Mauvais" );
        radioButtonMauv.setEnabled(false);
        if(etat.equals("Mauvais"))  { 
            radioButtonMauv.setSelected(true);
            radioButtonMauv.setEnabled(true);  }
        
        final ButtonGroup groupEtat = new ButtonGroup();
        groupEtat.add(radioButtonNouv);
        groupEtat.add(radioButtonBon);
        groupEtat.add(radioButtonMauv);
        
        etatPanel=new GroupPanel(GroupingType.fillFirst, 5,false,etatLabel,radioButtonNouv,radioButtonBon,radioButtonMauv);
        etatPanel.setMargin(50, 50, 50, 50);

        descPanel.setOpaque(false);
        descPanel.setMargin(50, 50, 50, 50);
        offresPanelOne.setPaintFocus(true);
        offresPanelOne.setMargin(10);
        // Type immod
        WebStepProgress wsp5 = null;
        wsp5 = new WebStepProgress ( "Villa", "Studio", "Appartement", "Maison", "Entrepot", "Terrain" );
        
        wsp5.setLabelsPosition ( WebStepProgress.LEADING );
        wsp5.setOrientation ( WebStepProgress.HORIZONTAL );
        wsp5.setStepControlRound ( 6);
        wsp5.setStepControlFillRound ( 0 );
        System.out.println("type est "+ typeImmob);
        if(typeImmob.equals("Villa")) 
            wsp5.setSelectedStep ( 0 );
        if(typeImmob.equals("Studio")) 
            wsp5.setSelectedStep ( 1 );
        if(typeImmob.equals("Appartement")) 
            wsp5.setSelectedStep ( 2 );
        if(typeImmob.equals("Maison")) 
            wsp5.setSelectedStep ( 3 );
        if(typeImmob.equals("Entrepot")) 
            wsp5.setSelectedStep ( 4 );
        if(typeImmob.equals("Terrain")) 
            wsp5.setSelectedStep ( 5 );
        wsp5.setEnabled(false);
        wsp5.setMargin(20, 20, 20, 20);
        //wsp5.getSelectedStep();
        
        // surface 
        WebLabel surfaceLabel1 = new WebLabel("Surface", WebLabel.CENTER);
        WebLabel surfaceLabel2 = new WebLabel("<html><body><h1><font color=green><center>"+surface+" Metre</center></font></h1><br></body></html>", WebLabel.CENTER);
        GroupPanel surfaceGroupPanel = new GroupPanel(false, surfaceLabel1, surfaceLabel2); 
        // nbr de Piece 
        WebLabel nbrPieceLabel1 = new WebLabel("Numbre de Piece", WebLabel.CENTER);
        WebLabel nbrPieceLabel2 = new WebLabel("<html><body><h1><font color=green><center>"+nbrPiece+"</center></font></h1><br></body></html>", WebLabel.CENTER);
        GroupPanel nbrPieceGroupPanel = new GroupPanel(false, nbrPieceLabel1, nbrPieceLabel2); 
        //Etage
         WebLabel etageLabel1 = new WebLabel("L'etage", WebLabel.CENTER);
         WebLabel etageLabel2 = new WebLabel("<html><body><h1><font color=green><center>"+etage+"</center></font></h1><br></body></html>", WebLabel.CENTER);
        
         GroupPanel etageGroupPanel = new GroupPanel(false, etageLabel1, etageLabel2); 
        // payement
         WebLabel payementLabel1 = new WebLabel("Prix", WebLabel.CENTER);
         WebLabel payementLabel2 = new WebLabel("<html><body><h1><font color=green><center>"+payement+" TD</center></font></h1><br></body></html>", WebLabel.CENTER);
        GroupPanel payementGroupPanel = new GroupPanel(false, payementLabel1, payementLabel2);
        // 4 line
        GroupPanel SNGroupPanel = new GroupPanel(GroupingType.fillFirstAndLast, 5,true, surfaceGroupPanel, nbrPieceGroupPanel); 
        GroupPanel EPGroupPanel = new GroupPanel(GroupingType.fillFirstAndLast, 5,true, etageGroupPanel,payementGroupPanel); 
        GroupPanel SNEPGroupPanel = new GroupPanel(GroupingType.fillFirstAndLast,0, SNGroupPanel,EPGroupPanel);
        SNEPGroupPanel.setMargin(40, 100, 40, 100);
        
        // Type immod
        WebStepProgress wsp10 = null;
        wsp10 = new WebStepProgress ( "Sans ascenseur", "Avec ascenceur" );
        wsp10.setSelectedStep ( 0 );
        wsp10.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp10.setOrientation ( WebStepProgress.LEFT );
        wsp10.setStepControlRound ( 2);
        wsp10.setStepControlFillRound ( 0 );
        System.out.println("type est "+ typeImmob);
        if(ascenceur==false) 
            wsp10.setSelectedStep ( 0 );
        if(ascenceur==true) 
            wsp10.setSelectedStep ( 1 );
        
        wsp10.setEnabled(false);
        wsp10.setMargin(20, 20, 20, 20);
        
        // Type immod
        WebStepProgress wsp11 = null;
        wsp11 = new WebStepProgress ( "Sans chauffage", "Avec chauffage" );
        wsp11.setSelectedStep ( 0 );
        wsp11.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp11.setOrientation ( WebStepProgress.LEFT );
        wsp11.setStepControlRound ( 2);
        wsp11.setStepControlFillRound ( 0 );
        System.out.println("type est "+ typeImmob);
        if(chauffage==false) 
            wsp11.setSelectedStep ( 0 );
        if(chauffage==true) 
            wsp11.setSelectedStep ( 1 );
        
        wsp11.setEnabled(false);
        wsp11.setMargin(20, 20, 20, 20);
        
        // Type immod
        WebStepProgress wsp12 = null;
        wsp12 = new WebStepProgress ( "Sans climatisation", "Avec climatisation" );
        wsp12.setSelectedStep ( 0 );
        wsp12.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp12.setOrientation ( WebStepProgress.LEFT );
        wsp12.setStepControlRound ( 2);
        wsp12.setStepControlFillRound ( 0 );
        System.out.println("type est "+ climatisation);
        if(climatisation==false) 
            wsp12.setSelectedStep ( 0 );
        if(climatisation==true) 
            wsp12.setSelectedStep ( 1 );
        
        wsp12.setEnabled(false);
        wsp12.setMargin(20, 20, 20, 20);
        
        // Type immod
        WebStepProgress wsp13 = null;
        wsp13 = new WebStepProgress ( "Sans cuisine equipe", "Avec cuisine equipe" );
        wsp13.setSelectedStep ( 0 );
        wsp13.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp13.setOrientation ( WebStepProgress.LEFT );
        wsp13.setStepControlRound ( 2);
        wsp13.setStepControlFillRound ( 0 );
        System.out.println("type est "+ typeImmob);
        if(cuisineEquipe==false) 
            wsp13.setSelectedStep ( 0 );
        if(cuisineEquipe==true) 
            wsp13.setSelectedStep ( 1 );
        
        wsp13.setEnabled(false);
        wsp13.setMargin(20, 20, 20, 20);
        
        // Type immod
        WebStepProgress wsp14 = null;
        wsp14 = new WebStepProgress ( "Sans entree indep", "Avec entree indep" );
        wsp14.setSelectedStep ( 0 );
        wsp14.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp14.setOrientation ( WebStepProgress.LEFT );
        wsp14.setStepControlRound ( 2);
        wsp14.setStepControlFillRound ( 0 );
        System.out.println("type est "+ entreeIndep);
        if(entreeIndep==false) 
            wsp14.setSelectedStep ( 0 );
        if(entreeIndep==true) 
            wsp14.setSelectedStep ( 1 );
        
        wsp14.setEnabled(false);
        wsp14.setMargin(20, 20, 20, 20);
        
        // gazDeVille
        WebStepProgress wsp15 = null;
        wsp15 = new WebStepProgress ( "Sans gaz de ville", "Avec gaz de ville" );
        wsp15.setSelectedStep ( 0 );
        wsp15.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp15.setOrientation ( WebStepProgress.LEFT );
        wsp15.setStepControlRound ( 2);
        wsp15.setStepControlFillRound ( 0 );
        System.out.println("type est "+ typeImmob);
        if(gazDeVille==false) 
            wsp15.setSelectedStep ( 0 );
        if(gazDeVille==true) 
            wsp15.setSelectedStep ( 1 );
        
        wsp15.setEnabled(false);
        wsp15.setMargin(20, 20, 20, 20);
        
        // jardin
        WebStepProgress wsp16 = null;
        wsp16 = new WebStepProgress ( "Sans jardin", "Avec jardin" );
        wsp16.setSelectedStep ( 0 );
        wsp16.setLabelsPosition ( WebStepProgress.STEP_SELECTION );
        wsp16.setOrientation ( WebStepProgress.LEFT );
        wsp16.setStepControlRound ( 2);
        wsp16.setStepControlFillRound ( 0 );
        System.out.println("type est "+ jardin);
        if(jardin==false) 
            wsp16.setSelectedStep ( 0 );
        if(jardin==true) 
            wsp16.setSelectedStep ( 1 );
        
        wsp16.setEnabled(false);
        wsp16.setMargin(20, 20, 20, 20);
        
        GroupPanel group1014Panel = new GroupPanel(GroupingType.fillFirstAndLast, 5,true, wsp10, wsp11,wsp12,wsp13,wsp14,wsp15,wsp16); 
        GroupPanel group1516Panel = new GroupPanel(GroupingType.fillFirstAndLast, 5,true, wsp15,wsp16); 
        
        int ddNote =(int) new OffreDAO().getNoteOffreX(id);
        System.out.println(ddNote);
        final StarRater starRater = new StarRater(5, 0, ddNote);
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(final int selection) {
                    System.out.println(selection);
                    
                    starRater.setRating(selection);
                   //new OffreDAO().apdateNoteOffre(id, (float)selection);
                    
                   NotificationManager.showNotification ( "Voulez vous noter l'offre", NotificationIcon.database.getIcon (),
                            NotificationOption.apply, NotificationOption.discard ).addNotificationListener(new NotificationListener() {

                        @Override
                        public void optionSelected(NotificationOption no) {
                            if(no.toString().equals("apply")){
                                new OffreDAO().apdateNoteOffreX(id, (float)selection, myId);
                                int selection =(int) new OffreDAO().getNoteOffreX(id);
                                starRater.setRating(selection);
                               
                            }
                            
                        }

                        @Override
                        public void accepted() {
                                    //System.out.println("test dfg Ok");
                        }

                        @Override
                        public void closed() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                   
                    
                   
                              
                }

                       });
        final JPanel starRaterpanel = new JPanel();
        starRaterpanel.add(starRater);
        starRaterpanel.setOpaque(false);
        //Map
        JPanel MapXV = loadImageX("http://maps.googleapis.com/maps/api/staticmap?center="+position+"&zoom=15&scale=1&size=600x300&maptype=roadmap&format=png&visual_refresh=true&markers=icon:d%7Cshadow:false%7C"+position);
        MapXV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MapX(position);
            }
            });
       
        //Contacter gerant
        JButton contatct =  new JButton("Contacter gerant");
        contatct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(id_Ger);
                new EnvoyerMessage(myId, id_Ger).setLocationRelativeTo(null);
            }
        });
        

        //GroupPanel group1011Panel = new GroupPanel(GroupingType.fillFirstAndLast, 5,true, etageGroupPanel,payementGroupPanel); 
        //GroupPanel SNEPGroupPanel = new GroupPanel(GroupingType.fillFirstAndLast,0, SNGroupPanel,EPGroupPanel);
        //wsp5.getSelectedStep();
        //***************************************************************/
        offresPanelOne.setPreferredSize(new Dimension(850, 1420)); 
        OffrePanel.add(new GroupPanel(GroupingType.fillFirst, 5,false, new WhiteSpace(), new WhiteSpace(10),
                    
                    loadImageX(urlImage).setPreferredHeight(340),
                    new GroupPanel(GroupingType.fillFirstAndLast,5, etatPanel,naturePanel),
                    wsp5,
                    SNEPGroupPanel,
                    group1014Panel,
                    group1516Panel,starRaterpanel,
                    new WhiteSpace(10),
                    MapXV,
                    new WhiteSpace(10),
                    new WhiteSpace()
                    ,contatct,
                    new WhiteSpace(10)));
        offresPanelOne.add(OffrePanel);
        return offresPanelOne;
    }

    // Open list of offres frame 
//    static void openListOfOffresFrame() throws IOException {
//
//        new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//
//                // do some processing here while the progress bar is running
//                f.setSize(500, 500);
//                f.setLocation(300, 200);
//                f.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//                f.setLayout(new GridLayout(1, 1, 5, 5));
//                try {
//                    f2.add(createListOfOffresPanel(0, 0));
//                    f.add(f2);
//                } catch (IOException ex) {
//                }
//
//                return null;
//            }
//
//            ;
//
//        // this is called when doInBackground finished
//        @Override
//            protected void done() {
//            //Background processing done
//                //Crate new Frale and confagurated
//                ListOfOffresFrame = new JFrame();
//                ListOfOffresFrame.setMaximizedBounds(null);
//                ListOfOffresFrame.setMinimumSize(new Dimension(300, 400));
//                ListOfOffresFrame.setTitle("Offre");
//                ListOfOffresFrame.setLayout(new GridLayout(1, 1, 20, 20));
//                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//                ListOfOffresFrame.setLocation((dim.height / 2) + 150, 150);
//                ListOfOffresFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                ListOfOffresFrame.setContentPane(f);
//                ListOfOffresFrame.setPreferredSize(dim);
//                ListOfOffresFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
//                ListOfOffresFrame.setVisible(true);
//                prog.setVisible(false);
//            }
//        ;
//    }
//    .execute();       
//    }
    //
    // Open list of offres frame 
    // Open list of offres frame 
     void openListOfOffresFrameagn(final int id, final int myId) throws IOException {
       openPleaseWait();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

           // do some processing here while the progress bar is running
                try {
                   
                    gf2.removeAll();
                    gfl.removeAll();
                    gfl.add(createListOfOffresPanel(id, myId));
                    
                } catch (IOException ex) {
                    Logger.getLogger(ListOfOffres1.class.getName()).log(Level.SEVERE, null, ex);
                }

                return null;
            }

            // this is called when doInBackground finished
            @Override
                protected void done() {
                    //Background processing done
                    gf2.add(new GroupPanel(GroupingType.fillLast, 0, true,fm,new WhiteSpace(), gfl));
                    gf2.revalidate();
                    gf2.repaint();
                    prog.setVisible(false);

                }
        }

    .execute();
        
    }
    //Wait for loading Content
    static void openPleaseWait() {
        WebProgressBar progressBar3 = new WebProgressBar();
        progressBar3.setIndeterminate(true);
        progressBar3.setStringPainted(true);
        progressBar3.setString("Please wait...");
        prog.setSize(500, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        prog.setLocation((dim.width / 2) - 250, 250);
        prog.add(progressBar3);
        //WebLookAndFeel.setDecorateAllWindows(true);
        prog.setVisible(true);
        
    }
    //Wait for loading Content
    static WebPanel loadImageX(String urlImage) throws IOException {
        WebPanel imagePanel = new WebPanel();
                
        try {
            BufferedImage bi = ImageIO.read(new URL(urlImage));
            ImageIcon img = new ImageIcon(bi);
            WebDecoratedImage img2 = new WebDecoratedImage(img);
            img2.setShadeWidth(0);
            imagePanel.add(img2);
            imagePanel.setOpaque(false);
            
        } catch (IOException e) {
            //or open no image found image
            BufferedImage image = ImageIO.read(new File("resources/no-image-found.jpg"));
            ImageIcon i1 = new ImageIcon(image);
            WebDecoratedImage img2 = new WebDecoratedImage(i1);
            img2.setShadeWidth(0);   
            imagePanel.add(img2);
            imagePanel.setOpaque(false);
            
         
        }
        return imagePanel;
        
    }
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
}
