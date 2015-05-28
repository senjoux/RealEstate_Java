/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.LoginE;
import com.alee.extended.image.WebDecoratedImage;
import com.alee.extended.image.WebImage;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.text.WebTextField;
import com.alee.laf.toolbar.WhiteSpace;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.json.JSONException;

/**
 *
 * @author FATHLLAH Wael
 */
public class Main {

    public static JFrame ListOfOffresFrame;
    final static WebPanel mainPanel = new WebPanel(true);
    final static JFrame prog = new JFrame();
    public final static JPanel fm = new JPanel();
    public static JPanel gfl = new JPanel();
    public static final WebPanel gf2 = new WebPanel();
    final static WebPanel fm1 = new WebPanel();
    static int nbrpage = 1;
    public static WebButton exitButton;
    static WebTextField textSearchField;

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                WebLookAndFeel.install();
                WebLookAndFeel.setDecorateAllWindows(true);

                openPleaseWait();
                try {
                    openListOfOffresFrame(null);
                } catch (IOException ex) {

                }
            }
        });
    }

    // Create Single offres panel
    // Create Single offres panel
    static private WebPanel createAdminPanel(String url, String firstName, String lastName, final int myId) throws IOException {

        mainPanel.setPaintFocus(true);

        // Text field input Search
        textSearchField = new WebTextField(15);
        textSearchField.setInputPrompt("Search");
        textSearchField.setInputPromptFont(textSearchField.getFont().deriveFont(Font.ROMAN_BASELINE));
        textSearchField.setTrailingComponent(new WebImage("resources/search.png"));
        System.out.println("My id"+ myId);
        // Boite Message
        WebButton boiteMessageButton = new WebButton("Ouvrir ma Boite Message");
        boiteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame boiteFrame = new JFrame("Boite de messages");
                boiteFrame.setLocationRelativeTo(null);
                JPanel boite = new JPanel(new BorderLayout());
                boite.add(new BoiteReception(myId), BorderLayout.CENTER);
                boiteFrame.add(boite);
                boiteFrame.setIconImage(new ImageIcon(getClass().getResource("/images/message.png")).getImage());
                boiteFrame.pack();
                boiteFrame.setLocationRelativeTo(boite);
                boiteFrame.setVisible(true);
            }
        });
        // Text Button Search
        WebButton SearchButton = new WebButton("Search");
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    new ListOfOffres1().openListOfOffresFrameagn(1, textSearchField.getText(), myId);
                } catch (IOException ex) {
                    Logger.getLogger(ListOfOffres1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Exit Button 
        WebButton logOutButton = new WebButton("Exit");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListOfOffresFrame.setVisible(false); //you can't see me!
                ListOfOffresFrame.dispose(); //Destroy the JFrame object
                ListOfOffresFrame = null;
                System.exit(0);
            }
        });
        // Text Button Retour
        exitButton = new WebButton("Retour");

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retour clicked");
                ListOfOffres1.openPleaseWait();
                try {
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+myId);
                    new ListOfOffres1().openListOfOffresFrameagn(0, "", myId);
                } catch (IOException ex) {
                    Logger.getLogger(ListOfOffres1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        final WebPanel descPanel = new WebPanel(false);

        GroupPanel OffrePanel = new GroupPanel(GroupingType.fillFirst, 5, false);

        descPanel.setOpaque(false);
        descPanel.setMargin(50, 50, 50, 50);
        mainPanel.setPaintFocus(true);
        mainPanel.setMargin(10);
        System.out.print(url);
        // load the image once

        mainPanel.setPreferredSize(new Dimension(300, 100));
        OffrePanel.add(new GroupPanel(GroupingType.fillFirst, 5, false, loadImageX(url), new WebLabel("<html><body><h1><font color=#555555>" + firstName + " " + lastName + "</font></h1></body></html>", WebLabel.CENTER),
                textSearchField, SearchButton,
                new WhiteSpace(),
                new GroupPanel(GroupingType.fillFirst, false, new WhiteSpace(), boiteMessageButton, new GroupPanel(GroupingType.fillFirstAndLast, 5, true, exitButton, logOutButton))
        ));
        mainPanel.add(OffrePanel);
        return mainPanel;
    }

    // Open list of offres frame 
    static void openListOfOffresFrame(final LoginE client) throws IOException {
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("My ID :"+client.getId());
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////");
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                // do some processing here while the progress bar is running
                fm.setSize(500, 500);
                fm.setLocation(300, 200);
                fm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                fm.setLayout(new GridLayout(1, 1, 5, 5));
                try {
                    fm1.add(createAdminPanel(client.getURLp(), client.getNom(), client.getPrenom(), client.getId()));

                } catch (IOException ex) {
                }

                return null;
            }

            // this is called when doInBackground finished
        @Override
            protected void done() {
                //Background processing done
                //Crate new Frale and confagurated
                fm.add(fm1);
                ListOfOffresFrame = new JFrame();
                ListOfOffresFrame.setMaximizedBounds(null);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                ListOfOffresFrame.setMinimumSize(dim);
                ListOfOffresFrame.setTitle("T_IMMOB");
                ListOfOffresFrame.setLayout(new GridLayout(1, 0));

                ListOfOffresFrame.setLocation(0, 0);
                ListOfOffresFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
                    gfl = new ListOfOffres1().createListOfOffresPanel(0, "", client.getId());
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                gf2.add(new GroupPanel(GroupingType.fillLast, 0, true, fm, new WhiteSpace(), gfl));
                ListOfOffresFrame.setContentPane(gf2);
                ListOfOffresFrame.setPreferredSize(dim);
                ListOfOffresFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
                ListOfOffresFrame.setVisible(true);
                prog.setVisible(false);
                prog.dispose();
                final WebPopOver popOver = new WebPopOver(textSearchField);
                popOver.setCloseOnFocusLoss(true);
                popOver.setMargin(10);

                popOver.setLayout(new VerticalFlowLayout());
                popOver.add(new WebLabel("<html><center><font size=4>Vous pouvez faire des recherche sure</font></center></html>"));
                popOver.add(new WebLabel("<html><center><font size=3>- Etat de l'offre ( Nouveau , Bon , Mauvais)"));
                popOver.add(new WebLabel("<html><center><font size=3>- Nature de l'offre ( Location , Vente )</font></center></html>"));
                popOver.add(new WebLabel("<html><center><font size=3>- Type de l'offre ( Vila , Studio , Apartemen ,</font></center></html>"));
                popOver.add(new WebLabel("<html><center><font size=3>Maison , Entrepot)</font></center></html>"));
                popOver.show(textSearchField);

            }
        }

    .execute();       
    }
    //
    // Open list of offres frame 
//     void openListOfOffresFrameagn() throws IOException {
//
//        new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//
//                // do some processing here while the progress bar is running
//                try {
//                    fm.remove(fm1);
//                    fm1.removeAll();
//                    fm1.add(createAdminPanel("https://lh3.googleusercontent.com/-GrC0KWkHnC0/AAAAAAAAAAI/AAAAAAAACjo/CDMVCXdNkYI/photo.jpg", "FATHALLAH", "Wael", 0));
//                    fm.add(fm1);
//                    fm.revalidate();
//                    fm.repaint();
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
//                //Background processing done            
//                prog.setVisible(false);
//                prog.dispose();
//
//            }
//        ;
//    }
//
//    .execute();
//        
//    }
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
            ImageIcon img = new ImageIcon(resize(bi, 150, 150));
            WebDecoratedImage img2 = new WebDecoratedImage(img);
            img2.setShadeWidth(5);
            img2.setRound(500);
            imagePanel.add(img2);

            imagePanel.setOpaque(false);

        } catch (IOException e) {
            //or open no image found image
            BufferedImage image = ImageIO.read(new File("resources/no-image-found.jpg"));
            ImageIcon img = new ImageIcon(resize(image, 150, 150));

            WebDecoratedImage img2 = new WebDecoratedImage(img);
            img2.setShadeWidth(5);
            img2.setRound(500);
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
