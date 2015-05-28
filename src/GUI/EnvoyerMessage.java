package GUI;

import DAO.BoiteMessageDAO;
import DAO.UtilisateurDAO;
import Entity.Utilisateur;
import Technique.SendMail;
import com.alee.extended.image.WebImage;
import com.alee.extended.label.WebStepLabel;
import com.alee.extended.layout.TableLayout;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.panel.WebButtonGroup;
import com.alee.extended.progress.WebProgressOverlay;
import com.alee.extended.window.WebPopOver;
import com.alee.global.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Elyes
 */
public class EnvoyerMessage extends WebFrame{
    private int id_expediteur;
    private int id_destinataire;
    private String adresseDest;
    private String nomExped;
////////////////////////////////////////////////////////////////////////////////
    public ImageIcon loadIcon(String name) {
        String path = "/images/" + name;
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        if (icon.getIconWidth() != -1) {
            return icon;
        } else {
            return new ImageIcon(getClass().getResource("/images/notFound.png"));
        }
    }
////////////////////////////////////////////////////////////////////////////////
    private String getUserLabel() {
        Utilisateur Uexp = new UtilisateurDAO().getUtilisateur(id_expediteur);
        nomExped = Uexp.getNom();
        Utilisateur Udest = new UtilisateurDAO().getUtilisateur(id_destinataire);
        adresseDest = Udest.getMail();
        String name = Udest.getNom();
        String style = "<h4 style=''>";
        String styleEnd = "</h4>";
        String html = "<html><body>" + style + name + styleEnd + "</body></html>";
        return html;
    }
////////////////////////////////////////////////////////////////////////////////    
    private void setupPanel(WebPanel panel) {
        // Decoration settings
        panel.setUndecorated ( false );
        panel.setMargin ( new Insets ( 3, 3, 3, 3 ) );
        panel.setRound ( StyleConstants.largeRound );
    }
////////////////////////////////////////////////////////////////////////////////
    private boolean envoyer(String contenu){
        if(contenu.length() > 0 && contenu.length() <= 500){
            boolean test = new BoiteMessageDAO().envoyerMessage(id_expediteur, id_destinataire, contenu);
            if(test == true){
                String body = "Vous avez reçu un nouveau message de " + nomExped + "";
                Runnable envoi = new SendMail(adresseDest, body);
                Thread th = new Thread(envoi);
                th.start();
                while(th.isAlive()){
                }
            }
            return test;
        }
        else
        {
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////
    private void afficherInfo(Component owner,String message){
        WebOptionPane.showMessageDialog ( owner, message, "Information", WebOptionPane.INFORMATION_MESSAGE );
    }
////////////////////////////////////////////////////////////////////////////////
    private void afficherErreur(Component owner,String message){
                WebOptionPane.showMessageDialog ( owner, message, "Erreur", WebOptionPane.ERROR_MESSAGE );
    }
////////////////////////////////////////////////////////////////////////////////            
    private void initialise(){
        // <editor-fold defaultstate="collapsed" desc="Main Panel">
        WebPanel mainPanel = new WebPanel(new BorderLayout());
        
        final WebPanel panel = new WebPanel ();
        panel.setUndecorated ( false );
        panel.setLayout ( new TableLayout ( new double[][]{ { TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED },
                { TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED } } ) );
        panel.setWebColoredBackground ( false );

        final WebPanel northPanel = new WebPanel ();
        northPanel.setPaintSides ( false, false, true, true );
        setupPanel ( northPanel);
        
        final WebPanel southPanel = new WebPanel ();
        southPanel.setPaintSides ( true, true, false, false );
        setupPanel ( southPanel);
        
        final WebPanel trailingPanel = new WebPanel ();
        trailingPanel.setPaintSides ( false, true, true, false );
        setupPanel ( trailingPanel);

        final WebPanel centerPanel = new WebPanel ();
        setupPanel (centerPanel);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="North components">
            //Panel
            WebPanel destPanel = new WebPanel(new FlowLayout(FlowLayout.LEFT));
            destPanel.setOpaque(false);
            //Destinataire Label
            WebLabel destinataireLabel = new WebLabel ( "Destinataire: ", loadIcon ( "destinataire.png" ) );
            //Utilisateur Label
            WebLabel userLabel = new WebLabel (getUserLabel());
            //Adding Label
            destPanel.add(destinataireLabel);
            destPanel.add(userLabel);
            northPanel.add(destPanel);
            northPanel.setMargin ( new Insets ( -5, 0, -5, 0 ) );
        // </editor-fold>
            
        // <editor-fold defaultstate="collapsed" desc="Center components">
            //Panel
            WebPanel textPanel = new WebPanel(new BorderLayout());
            textPanel.setOpaque(false);
            //Message limit
            final WebStepLabel limit = new WebStepLabel ( "500" );
            limit.setPreferredSize(new Dimension(60,60));
            TooltipManager.setTooltip ( limit, "nombre des cractéres restantes", TooltipWay.down, 0 );
            //TextArea
            final WebTextArea textArea = new WebTextArea ();
            textArea.setLineWrap ( true );
            textArea.setWrapStyleWord ( true );
            textArea.setRows(2);
            textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int length = textArea.getText().length();
                if(length <= 500){
                 limit.setText("" + (500-length));   
                }
                else{
                    textArea.setText(textArea.getText().substring(0, 499));
                    limit.setText("0");
                }
                if(limit.getText().equals("0")){
                    limit.setBorderColor(Color.RED);                    
                }
                else{
                    limit.setBorderColor(Color.GRAY);
                }
            }
        });
            WebScrollPane areaScroll = new WebScrollPane ( textArea );
            areaScroll.setPreferredSize ( new Dimension ( 450, 200 ) );
            //Adding
            textPanel.add(areaScroll,BorderLayout.CENTER);
            textPanel.add(limit,BorderLayout.SOUTH);
            centerPanel.add(textPanel);            
        // </editor-fold>
            
        // <editor-fold defaultstate="collapsed" desc="South components">
            //Buttons panel
            WebPanel buttonsPanel = new WebPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
            buttonsPanel.setOpaque(false);
            //Cancel button
            WebButton cancelButton = new WebButton("Annuler", loadIcon("cancel.png"));
            cancelButton.setPreferredSize(new Dimension(90, 35));
            cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
            //Send button
            final WebButton sendButton = new WebButton("Envoyer", loadIcon("send.png"));
            sendButton.setPreferredSize(new Dimension(90, 35));
            // Progress overlay
            final WebProgressOverlay progressOverlay = new WebProgressOverlay (sendButton);
            progressOverlay.setConsumeEvents ( false );
            final EnvoyerMessage owner = this;
            sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setEnabled(false);
                sendButton.setEnabled(false);
                progressOverlay.setShowLoad(true);
                //Sending... message
                final WebPopOver popOver = new WebPopOver ( owner );
                popOver.setCloseOnFocusLoss ( false );
                popOver.setMargin ( 10 );
                popOver.setLayout ( new VerticalFlowLayout () );
                popOver.add ( new WebLabel ( "Sending..." ) );
                popOver.show ( owner );
                //Envoyer message
                boolean test = envoyer(textArea.getText());
                if(test == true){
                    afficherInfo(owner,"Votre message a été envoyé avec succès");
                    dispose();
                }
                else{
                    afficherErreur(owner,"Impossible d'envoyer le message");
                    popOver.dispose();
                    textArea.setEnabled(true);
                    progressOverlay.setShowLoad(false);
                    sendButton.setEnabled(true);             
                }
            }
        });
            //Font size
            WebImage fontImage = new WebImage ( loadIcon ( "font.png" ) );
            //Options
            // 1st line buttons
            final WebToggleButton b1 = new WebToggleButton ( "1" );
            final WebToggleButton b2 = new WebToggleButton ( "2" );
            final WebToggleButton b3 = new WebToggleButton ( "3" );
            WebButtonGroup g1 = new WebButtonGroup ( b1, b2, b3 );

            // 2nd line buttons
            final WebToggleButton b4 = new WebToggleButton ( "4" );
            final WebToggleButton b5 = new WebToggleButton ( "5" );
            final WebToggleButton b6 = new WebToggleButton ( "6" );
            WebButtonGroup g2 = new WebButtonGroup ( b4, b5, b6 );

            // Grouping lines together
            WebButtonGroup group = new WebButtonGroup ( WebButtonGroup.VERTICAL, true, g1, g2 );
            group.setButtonsDrawFocus ( false );
            ActionListener fontSizeListner;
            fontSizeListner = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b1){
                    textArea.setFontSize(12);
                }
                else if(e.getSource() == b2){
                    textArea.setFontSize(15);
                }
                else if(e.getSource() == b3){
                    textArea.setFontSize(20);
                }
                else if(e.getSource() == b4){
                    textArea.setFontSize(25);
                }
                else if(e.getSource() == b5){
                    textArea.setFontSize(30);
                }
                else if(e.getSource() == b6){
                    textArea.setFontSize(35);
                }
            }
        };
            b1.addActionListener(fontSizeListner);
            b2.addActionListener(fontSizeListner);
            b3.addActionListener(fontSizeListner);
            b4.addActionListener(fontSizeListner);
            b5.addActionListener(fontSizeListner);
            b6.addActionListener(fontSizeListner);
            //Adding buttons and buttonsPanel
            buttonsPanel.add(fontImage);
            buttonsPanel.add(group.setMargin(0, 0, 0, 255));
            buttonsPanel.add(progressOverlay);
            buttonsPanel.add(cancelButton);
            southPanel.add(buttonsPanel);
        // </editor-fold>
        
        //Adding components
        panel.add ( northPanel, "0,0,1,0" );
        panel.add ( southPanel, "1,2,2,2" );
        panel.add ( centerPanel, "1,1" );
        mainPanel.add(panel, BorderLayout.CENTER);
        //settings
        setContentPane(mainPanel);
    }
////////////////////////////////////////////////////////////////////////////////
    private void start(){
        this.setShowWindowButtons(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////
    public EnvoyerMessage() {
        //Window settings
        super("Envoyer message");
        WebLookAndFeel.install();
        WebLookAndFeel.setDecorateAllWindows(true);
        setPreferredSize(new Dimension(650,400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setRound(10);
        setShowResizeCorner(false);
        //Window icon
        this.setIconImage( loadIcon("message.png").getImage() );
    }
////////////////////////////////////////////////////////////////////////////////
    public EnvoyerMessage(int id_expediteur,int id_destinataire){
        this();
        this.id_expediteur = id_expediteur;
        this.id_destinataire = id_destinataire;
        initialise();
        //Window
        start();
    }
////////////////////////////////////////////////////////////////////////////////
}
