/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;



import DAO.LogUpDAO;
import Technique.PostFile;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import com.alee.extended.image.WebImageDrop;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.panel.GroupingType;
import com.alee.extended.window.WebPopOver;
import com.alee.global.GlobalConstants;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.filechooser.WebFileChooser;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.toolbar.WhiteSpace;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 *
 * @author ulrich blanchard
 */
public class LogUp {
    protected static JFrame logupFramr;
    protected static String fileName= "";
    /* faceBook API */
    ///////////////////////////////////
    public static String API_KEY = "433756370121014";
    public static String SECRET = "0274ae3496a60be4d7d21bf1c7d46b59";

    public static String firstRequest = "https://graph.facebook.com/oauth/authorize?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "scope=email,user_birthday";
    public static String secondRequest = "https://graph.facebook.com/oauth/access_token?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "client_secret=" + SECRET + "&code=";
    public static String access_token = "CAAV9J4xeedQBAIZBpKge2yAtKAItjyDOzj1JBDT3I51J2odHedwynZBfZCQcv3RXoXX9ko1qmtUcpU3XUCRtMdRtKsh7ZBZAZAxslFx6oOP67Fz2W18SIqpEZA8qikyu5wNgAEnrlwrmfvjZCwfEGerXFYZAZCRMPWo2HP5O2YBI9Jdi76CLlxZCAVXAKcjvExpkgU5YgGELd3uKcoT7G2leGyDB6UvCe02hue7P7HwUNTNWgZDZD";
    public static boolean firstRequestDone = false;
    public static boolean secondRequestDone = false;
    final JFrame loginFrame = new JFrame();
    public static FacebookClient facebookClient = null;
    
    
    public static boolean fbCnxEnd = false;
    
    public String registredUserName;
    //End of Facebook conf
    public static void main ( final String[] args )
    {
        SwingUtilities.invokeLater ( new Runnable ()
        {
            
            @Override
            public void run ()
            {
               // Look and Feel 
               WebLookAndFeel.install ();  
               //Open Logup Frame
               WebLookAndFeel.setDecorateAllWindows(true );
               openLogupFrame();             
               }
        });
    }
    
    private static WebPanel createLogupPanel () throws NumberFormatException{
        final WebPanel panel = new WebPanel ();
        panel.setUndecorated ( false );
        //panel.setLayout(null);
        
        panel.setWebColoredBackground ( false );
        
        //Photo Pers
        final JLabel textLabelphoto = new WebLabel("Choisire une photo pesonalle");
        final WebImageDrop photoPers = new WebImageDrop (100 , 100);
        photoPers.addMouseListener(new MouseAdapter() {
            private WebFileChooser imageChooser = null;
            private File file = null;
                @Override
                public void mouseClicked(MouseEvent e) {
                    if ( imageChooser == null )
                {
                    imageChooser = new WebFileChooser ();
                    imageChooser.setMultiSelectionEnabled ( false );
                    imageChooser.setAcceptAllFileFilterUsed ( true );
                    imageChooser.addChoosableFileFilter ( GlobalConstants.IMAGES_FILTER );
                }
                if ( file != null )
                {
                    imageChooser.setSelectedFile ( file );
                }
                if ( imageChooser.showOpenDialog ( photoPers ) == WebFileChooser.APPROVE_OPTION )
                {
                    file = imageChooser.getSelectedFile ();
                    photoPers.getImage();
                        try {
                            photoPers.setImage(ImageIO.read(file) );
                        } catch (IOException ex) {
                            Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        fileName = UUID.randomUUID().toString();
                        File outputfile = new File(fileName+".jpg");
                        try {
                            ImageIO.write(photoPers.getImage(), "jpg", outputfile);
                        } catch (IOException ex) {
                            Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    System.out.println(outputfile);
                        try {
                            new PostFile().upload(outputfile.toString());
                        } catch (Exception ex) {
                            Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                   
                }
                }
            });
        
      
        //Nom
        final JLabel textLabelNom= new WebLabel("Nom");
        final JTextField textFieldNom = new JTextField();
        TooltipManager.setTooltip ( textFieldNom, "Votre Nom",TooltipWay.up, 0 );
        //Prenom
        final JLabel textLabelPrenom = new WebLabel("Prenom");
        final JTextField textFieldPrenom = new JTextField();
        TooltipManager.setTooltip ( textFieldPrenom, "Votre Prenom",TooltipWay.up, 0 );
        
        //Group panel Photo Per + group Panel Nom Prenom
        GroupPanel GroupPNP = new GroupPanel(GroupingType.fillLast, 5, true, new GroupPanel(GroupingType.none, 20, false,photoPers), new GroupPanel(GroupingType.none, 5, false, textLabelNom,textFieldNom, textLabelPrenom, textFieldPrenom));
        
        //Email
        final JLabel textLabelEmail = new WebLabel ("Email                      ");
        final JTextField textFieldEmail = new JTextField();
        TooltipManager.setTooltip ( textFieldEmail, "Votre Email",TooltipWay.up, 0 );
        //Email verif
        final JLabel textLabelEmail2 = new WebLabel("Verifier Email            ");
        final JTextField textFieldEmail2 = new JTextField();
        TooltipManager.setTooltip ( textFieldEmail2, "Répétez votre Email",TooltipWay.up, 0 );
        //Group panel Email
        GroupPanel GroupEmVm = new GroupPanel(GroupingType.none, 5, false, new GroupPanel(GroupingType.fillLast, 20, true,textLabelEmail,textFieldEmail), new GroupPanel(GroupingType.fillLast, 20, true,textLabelEmail2,textFieldEmail2));
        //PassWord
        final JLabel textLabelPassWrod = new WebLabel("Mot De Passe           ");
        final JPasswordField textFieldPassword = new JPasswordField();
        TooltipManager.setTooltip ( textFieldPassword, "Votre Mot De Passe",TooltipWay.up, 0 );
        //PassWord verif
        final JLabel textLabelPassWrod2 = new WebLabel("Verifier Mot De Passe");
        final JPasswordField textFieldPassword2 = new JPasswordField();
        TooltipManager.setTooltip ( textFieldPassword2, "Répéter votre Mot De Passe",TooltipWay.up, 0 );
        //Group panel PassWord
        GroupPanel GroupPaVp = new GroupPanel(GroupingType.none, 5, false, new GroupPanel(GroupingType.fillLast, 20, true,textLabelPassWrod,textFieldPassword), new GroupPanel(GroupingType.fillLast, 20, true,textLabelPassWrod2,textFieldPassword2));
        //Male Female
        final JLabel textLabelmale = new WebLabel("Status matrimonial");
        final JTextField textFieldMaleFemale = new JTextField();
        //TooltipManager.setTooltip ( textFieldPassword2, "Répéter votre Mot De Passe",TooltipWay.up, 0 );
        //Group panel Male Female
        GroupPanel GroupMaFe = new GroupPanel(GroupingType.none, 5, false, new GroupPanel(GroupingType.fillLast, 20, true,textLabelmale,textFieldMaleFemale));
        //Je Suis un Gérant
        final WebCheckBox checkBoxG = new WebCheckBox ( "Je Suis un Gérant" );
        //Tell Fixe
        final JLabel textLabelFixe = new WebLabel ("Numéro fixe    ");
        final JTextField TextFieldNF = new JTextField ();
        TextFieldNF.setEnabled(false);
        TooltipManager.setTooltip ( TextFieldNF, "Votre numéro fixe",TooltipWay.up, 0 );
        //Tell Modile
        final JLabel textLabelMob = new WebLabel("Numéro mobile");
        final JTextField TextFieldNM = new JTextField ();

        TextFieldNM.setEnabled(false);
        TooltipManager.setTooltip ( TextFieldNM, "Votre numéro fixe",TooltipWay.up, 0 );
        //Group panel Tell
        GroupPanel GroupTFM = new GroupPanel(GroupingType.none, 5, false, new GroupPanel(GroupingType.fillLast, 20, true,textLabelFixe,TextFieldNF), new GroupPanel(GroupingType.fillLast, 20, true,textLabelMob,TextFieldNM));
        checkBoxG.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxG.isSelected()){
                    TextFieldNM.setEnabled(true);
                    TextFieldNF.setEnabled(true);
                System.out.println("selected");
                }else{
                    TextFieldNM.setEnabled(false);
                    TextFieldNF.setEnabled(false);
                }
                
            }
        });

        
        //Boutton
        final JButton logup  =  new JButton ( "Sing Up" );
        final JButton cancel =  new JButton ( "Cancel" );
        ImageIcon fbIcon =new ImageIcon("resources/fb.png");
        JLabel fbLabel = new JLabel(fbIcon);
        TooltipManager.setTooltip ( fbLabel, "Inscription avec Facebook",TooltipWay.right, 0 );
                
        //ActionListener
        logup.addActionListener ( new ActionListener ()
        {
            @Override
            public void actionPerformed ( ActionEvent e )
            {
                
                //verif pass
                char[] pass = textFieldPassword.getPassword();
                String passString = new String(pass);
                char[] pass2 = textFieldPassword2.getPassword();
                String passString2 = new String(pass2);
                
                //verif email
                String mailString = textFieldEmail.getText().substring(0).toLowerCase();
                String mailString2 = textFieldEmail2.getText().substring(0).toLowerCase();
                    
                        if((!fieldIsEmpty(passString)&&!fieldIsEmpty(passString2)&&!fieldIsEmpty(mailString)&&
                                !fieldIsEmpty(mailString2)&&!fieldIsEmpty(textFieldNom.getText())
                                &&!fieldIsEmpty(textFieldPrenom.getText()))){
                            //Client field are not empty
                            if(passString.equals(passString2)){
                                    if(mailString.equals(mailString2)){
                                        if(checkBoxG.isSelected()){
                                            if(!fieldIsEmpty(TextFieldNF.getText())&&!fieldIsEmpty(TextFieldNM.getText())){
                                                //LogUp Ger 
                                                    
                                                    try {
                                                        int intNM =  Integer.parseInt(TextFieldNM.getText());
                                                        int intNF = Integer.parseInt(TextFieldNF.getText());
                                                        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                                                        digest.update(passString.getBytes());
                                                        byte[] passwordBytes = digest.digest();
                                                        if(new LogUpDAO().logUPG(mailString, passString2 , textFieldNom.getText(), textFieldPrenom.getText(), intNM, intNF,textFieldMaleFemale.getText(), 1, "http://localhost/image/"+fileName+".jpg")){
                                                        //Mobile Fix are not empty               
                                                        logupFramr.setVisible(false); //you can't see me!
                                                        logupFramr.dispose(); //Destroy the JFrame object

                                                        }else{
                                                            WebPopOver popOver = new WebPopOver ( logupFramr );
                                                            popOver.setCloseOnFocusLoss ( true );
                                                            popOver.setMargin ( 10 );
                                                            popOver.setLayout ( new VerticalFlowLayout () );
                                                            popOver.add ( new WebLabel ( "Vous êtes déjà inscrit" ) );

                                                            popOver.show ( logupFramr );
                                                        }
                                                    } catch (NumberFormatException nfe) {
                                                             WebPopOver popOver = new WebPopOver ( logupFramr );
                                                            popOver.setCloseOnFocusLoss ( true );
                                                            popOver.setMargin ( 10 );
                                                            popOver.setLayout ( new VerticalFlowLayout () );
                                                            popOver.add ( new WebLabel ( "Verifez les champs Numero fixe et mobile if faut saisir des numeros" ) );

                                                            popOver.show ( logupFramr );
                                                       // JOptionPane.showMessageDialog(null,"Input must be a number.");
                                                    } catch (NoSuchAlgorithmException ex) {    
                                                    Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                                                }    

                                            }else{
                                                //Mobile Fix are empty
                                                WebPopOver popOver = new WebPopOver ( logupFramr );
                                                popOver.setCloseOnFocusLoss ( true );
                                                popOver.setMargin ( 10 );
                                                popOver.setLayout ( new VerticalFlowLayout () );
                                                popOver.add ( new WebLabel ( "Il faut remplir les champs Numero fixe et Mobile" ) );

                                                popOver.show ( logupFramr );
                                            }
                                        }else{
                                            //Do Logup Client
                                            MessageDigest digest = null;
                                            try {
                                                digest = java.security.MessageDigest.getInstance("MD5");
                                            } catch (NoSuchAlgorithmException ex) {
                                                Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            digest.update(passString.getBytes());
                                            byte[] passwordBytes = digest.digest();
                                            if(new LogUpDAO().logUPC(mailString, passString2, textFieldNom.getText(), textFieldPrenom.getText(),textFieldMaleFemale.getText(), 2, "http://localhost/image/"+fileName+".jpg")){
                                                
                                                                    logupFramr.setVisible(false); //you can't see me!
                                                                    logupFramr.dispose(); //Destroy the JFrame object
                                            }else{
                                                WebPopOver popOver = new WebPopOver ( logupFramr );
                                                            popOver.setCloseOnFocusLoss ( true );
                                                            popOver.setMargin ( 10 );
                                                            popOver.setLayout ( new VerticalFlowLayout () );
                                                            popOver.add ( new WebLabel ( "Vous êtes déjà inscrit" ) );

                                                            popOver.show ( logupFramr );
                                            }
                                        }
                                    }else{
                                        // Mail field not equals
                                        WebPopOver popOver = new WebPopOver ( logupFramr );
                                                popOver.setCloseOnFocusLoss ( true );
                                                popOver.setMargin ( 10 );
                                                popOver.setLayout ( new VerticalFlowLayout () );
                                                popOver.add ( new WebLabel ( "Verifier que les champs mail sont les memes" ) );

                                                popOver.show ( logupFramr );
                                    }
                            }else{
                                // Password field not equals
                                WebPopOver popOver = new WebPopOver ( logupFramr );
                                                popOver.setCloseOnFocusLoss ( true );
                                                popOver.setMargin ( 10 );
                                                popOver.setLayout ( new VerticalFlowLayout () );
                                                popOver.add ( new WebLabel ( "Verifier que les champs mot de passe sont les memes" ) );

                                                popOver.show ( logupFramr );
                            }
                        }else{
                            // field are empty 
                            WebPopOver popOver = new WebPopOver ( logupFramr );
                                                popOver.setCloseOnFocusLoss ( true );
                                                popOver.setMargin ( 10 );
                                                popOver.setLayout ( new VerticalFlowLayout () );
                                                popOver.add ( new WebLabel ( "Les champs sont vide" ) );

                                                popOver.show ( logupFramr );
                        }
                    
            }
            private boolean fieldIsEmpty(String xString) {
                return xString.equals("");
           }
                
            
            
        } );
                
        cancel.addActionListener ( new ActionListener ()
        {
            @Override
            public void actionPerformed ( ActionEvent e )
            {
                logupFramr.setVisible(false); //you can't see me!
                logupFramr.dispose(); //Destroy the JFrame object
            }
        } );
        fbLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //On click on Fb button
                //getting the browser
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(LogUp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        }
                NativeInterface.open();
                NativeInterface.initialize();
                final JFrame authFrame = new JFrame();
                authFrame.setSize(80, 80);
                JPanel webBrowserPanel = new JPanel(new BorderLayout());
                webBrowserPanel.setPreferredSize(new Dimension(80, 80));
                 final JWebBrowser webBrowser = new JWebBrowser();

                webBrowser.navigate(firstRequest);

                webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
                    public void locationChanged(WebBrowserNavigationEvent e) {
                        super.locationChanged(e);
                        // Check if first request was not done
                        if (!firstRequestDone) {
                            // Check if you left the location and were redirected to the next
                            // location

                            if (e.getNewResourceLocation().contains("m.facebook.com/connect/login_success.html?code=")) {
                                // If it successfully redirects you, get the verification code
                                // and go for a second request
                                String[] splits = e.getNewResourceLocation().split("=");
                                String stage2temp = secondRequest + splits[1];
                                System.out.println("First =" + splits[1]);
                                webBrowser.navigate(stage2temp);
                                firstRequestDone = true;
                            }
                        } else {
                            // If secondRequest is not done yet, you perform this and get the
                            // access_token
                            if (!secondRequestDone) {
                                System.out.println(webBrowser.getHTMLContent());
                                // Create reader with the html content
                                StringReader readerSTR = new StringReader(webBrowser.getHTMLContent());
                                // Create a callback for html parser
                                HTMLEditorKit.ParserCallback callback;
                                callback = new HTMLEditorKit.ParserCallback() {
                                    @Override
                                    public void handleText(char[] data, int pos) {
                                        try {
                                            System.out.println(data);
                                               // because there is only one line with the access_token
                                            // in the html content you can parse it.
                                            String string = new String(data);
                                            String[] temp1 = string.split("&");
                                            String[] temp2 = temp1[0].split("=");
                                            System.out.println("access tocken=" + temp2);
                                            access_token = temp2[1];
                                            facebookClient = new DefaultFacebookClient(access_token);
                                            User user = facebookClient.fetchObject("me", User.class);
                                            
                                            textFieldNom.setText( user.getFirstName());
                                            textFieldPrenom.setText( user.getLastName());
                                            textFieldEmail.setText( user.getEmail());
                                            textFieldEmail2.setText( user.getEmail());
                                            //Close fbFrame
                                            // Look and Feel 
                                            WebLookAndFeel.install ();  
                                            //Open Logup Frame
                                            WebLookAndFeel.setDecorateAllWindows(true );
                                            authFrame.dispose();

                                        } catch (Exception ex) {

                                        }
                                    }
                                };
                                try {
                                    // Call the parse method
                                    new ParserDelegator().parse(readerSTR, callback, false);

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                // After everything is done, you can dispose the jframe
                                authFrame.dispose();

                    }
                }
            }
        });

        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        authFrame.add(webBrowserPanel);
        authFrame.setSize(900, 700);
        authFrame.setVisible(true);
        authFrame.setLocationRelativeTo(null);

            }

            @Override
            public void mousePressed(MouseEvent e) {
               
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        //Logup Group Panel
        final GroupPanel LogupPanel = new GroupPanel(GroupingType.fillFirst, 5,false,GroupPNP,GroupEmVm,GroupPaVp,new WhiteSpace(8),GroupMaFe, checkBoxG,GroupTFM,
                                                        new WhiteSpace(), new GroupPanel ( GroupingType.fillMiddle, 5, fbLabel, new WhiteSpace (), logup, cancel )
                                                        
                                                    );
        
        LogupPanel.setMargin(20, 20, 20, 20);
       
        // Logup Panel Confeguration 
        panel.setRound ( StyleConstants.largeRound );
        panel.setAutoscrolls(false);
        panel.setPreferredSize ( new Dimension ( 400, 400 ) );
        panel.setMaximumSize(new Dimension(400, 400));
        
        panel.add ( LogupPanel ) ;

        return panel;
    }
    public static void openLogupFrame(){
        logupFramr =new JFrame();
        logupFramr.setSize(500, 540);
        logupFramr.setResizable(false);
        
        logupFramr.setTitle("Sing Up");
        logupFramr.setLayout(new GridLayout(1, 1, 20, 20));
        JPanel f = new JPanel();
//        f.setSize(500, 500);
//        f.setLocation(300,200);
        f.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        f.setLayout(new GridLayout(1 , 1 , 5 , 5));
        f.add(createLogupPanel()); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        logupFramr.setLocation((dim.width/2)-250, 150);
        logupFramr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logupFramr.add(f);
        logupFramr.setVisible(true);
    }
}
