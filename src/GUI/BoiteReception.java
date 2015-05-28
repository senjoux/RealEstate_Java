package GUI;

import DAO.BoiteMessageDAO;
import DAO.UtilisateurDAO;
import Entity.BoiteMessages;
import Entity.Message;
import Entity.Utilisateur;
import com.alee.extended.breadcrumb.WebBreadcrumb;
import com.alee.extended.breadcrumb.WebBreadcrumbPanel;
import com.alee.extended.layout.HorizontalFlowLayout;
import com.alee.extended.panel.*;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;
import com.alee.utils.SwingUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Elyes
 */
public class BoiteReception extends WebPanel {

    private int userID;
    private boolean refreshing;
    private int currentPage;
    private long currentId;
    private int currentExpd;
    private WebPanel messagesPanel;
    private WebPanel statusPanel;
    private WebBreadcrumb breadcrumb;
    private WebLabel nbr;
    private WebBreadcrumbPanel radioPanel;
    private WebCheckBox checkBox;
    private WebButton replyButton;
    private WebButton deleteButton;
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

    private WebScrollPane createCustomContent(final int w, final int h, final String content) {
        // Content text area
        final WebTextArea textArea = new WebTextArea(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // Text area scroll
        final WebScrollPane scrollPane = new WebScrollPane(textArea, false);
        scrollPane.setPreferredSize(new Dimension(w, h));

        return scrollPane;
    }
////////////////////////////////////////////////////////////////////////////////

    private WebScrollPane createCustomHorContent(String content) {
        return createCustomContent(150, 100, content);
    }
////////////////////////////////////////////////////////////////////////////////

    private String getMessageTitle(Message m) {
        Utilisateur U = new UtilisateurDAO().getUtilisateur(m.getId_expediteur());
        return U.getNom() + "      " + m.getTemps_envoi().substring(0, 16);
    }
////////////////////////////////////////////////////////////////////////////////

    private ArrayList<Message> getMessages(boolean unreadOnly, int page) {
        BoiteMessages B = new BoiteMessageDAO().getBoiteMessages(userID, unreadOnly);
        ArrayList<Message> L = new ArrayList<Message>();
        for (int i = 10 * (page - 1); i < 10 * page; i++) {
            if (i < B.box.size()) {
                L.add(B.box.get(i));
            } else {
                break;
            }
        }
        return L;
    }
////////////////////////////////////////////////////////////////////////////////

    private ImageIcon getMessageIcon(short vu) {
        if (vu == 1) {
            return loadIcon("read_message.png");
        } else {
            return loadIcon("unread_message.png");
        }
    }
////////////////////////////////////////////////////////////////////////////////

    private void addMessages(final WebAccordion list, boolean unreadOnly, int page) {
        currentExpd = -1;
        currentId = -1;
        final ArrayList<Message> messagesList = getMessages(unreadOnly, page);
        if (messagesList.size() > 0) {
            for (int i = 0; i < messagesList.size(); i++) {
                if(i == 0){
                    currentExpd = messagesList.get(0).getId_expediteur();
                    currentId = messagesList.get(0).getId();
                }
                final int index = i;
                list.addPane(getMessageIcon(messagesList.get(i).getVu()), (i + 1) + "- " + getMessageTitle(messagesList.get(i)), createCustomHorContent(messagesList.get(i).getContenu()));
                list.getPane(i).addCollapsiblePaneListener(new CollapsiblePaneListener() {

                    @Override
                    public void expanding(WebCollapsiblePane wcp) {
                    }

                    @Override
                    public void expanded(WebCollapsiblePane wcp) {
                        new BoiteMessageDAO().setVu(messagesList.get(index).getId());
                        list.getPane(index).setIcon(loadIcon("read_message.png"));
                        currentId = messagesList.get(index).getId();
                        currentExpd = messagesList.get(index).getId_expediteur();
                    }

                    @Override
                    public void collapsing(WebCollapsiblePane wcp) {
                    }

                    @Override
                    public void collapsed(WebCollapsiblePane wcp) {
                    }
                });
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////

    private void addMessagesComponents(boolean unreadOnly, int page) {
        // Clear panel
        messagesPanel.removeAll();
        // Simple pane
        WebAccordion list = new WebAccordion(WebAccordionStyle.accordionStyle);
        list.setMultiplySelectionAllowed(false);
        //initiate panel
        addMessages(list, unreadOnly, page);
        //Add result
        messagesPanel.add(new GroupPanel(0, list));
        messagesPanel.revalidate();
        messagesPanel.repaint();
    }
////////////////////////////////////////////////////////////////////////////////

    private void addPages(int messageCount) {
        ActionListener pageActionListener;
        pageActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (refreshing == false) {
                    refreshing = true;
                    WebRadioButton b = (WebRadioButton) e.getSource();
                    int reqPage = Integer.parseInt(b.getText());
                    if (reqPage != currentPage) {
                        addMessagesComponents(false, reqPage);
                        currentPage = reqPage;
                    }
                    refreshing = false;
                }
            }
        };
        if (messageCount > 0) {
            for (int i = 1; i <= (messageCount / 10) + 1; i++) {
                WebRadioButton b = new WebRadioButton("" + i);
                b.addActionListener(pageActionListener);
                if (i == 1) {
                    b.setSelected(true);
                }
                radioPanel.add(b);
            }
        } else {
            radioPanel.add(new WebRadioButton("Boite vide"));
        }
        SwingUtils.groupButtons(radioPanel);
    }
////////////////////////////////////////////////////////////////////////////////

    private void addStatusComponents(boolean unreadOnly) {
        // Clear panel
        statusPanel.removeAll();
        // MessageCount
        int messageCount = new BoiteMessageDAO().getBoiteMessages(userID, unreadOnly).box.size();
        // Decorated breadcrumb
        breadcrumb = new WebBreadcrumb(true);
        // Nombre total des messages
        nbr = new WebLabel("Total: " + messageCount);
        breadcrumb.add(new WebBreadcrumbPanel(nbr));
        // Radio buttons panel
        radioPanel = new WebBreadcrumbPanel(new HorizontalFlowLayout(4));
        addPages(messageCount);
        breadcrumb.add(radioPanel);
        // Reply Button 
        replyButton = new WebButton();
        replyButton.setIcon(loadIcon("reply.png"));
        replyButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentExpd != -1){
                    new EnvoyerMessage(userID,currentExpd).setLocationRelativeTo(messagesPanel);
                }
            }
        });
        breadcrumb.add(new WebBreadcrumbPanel(replyButton));
        // Delete Button
        deleteButton = new WebButton();
        deleteButton.setIcon(loadIcon("delete.png"));
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentId != -1){
                    new BoiteMessageDAO().supprimerMessage(currentId);
                    addStatusComponents(checkBox.isSelected());
                    addMessagesComponents(checkBox.isSelected(), 1);
                }
            }
        });
        breadcrumb.add(new WebBreadcrumbPanel(deleteButton));
        // Check box panel
        breadcrumb.add(new WebBreadcrumbPanel(checkBox));
        // Adding Panel
        statusPanel.add(breadcrumb);
        statusPanel.revalidate();
        statusPanel.repaint();
    }
////////////////////////////////////////////////////////////////////////////////

    public BoiteReception() {
        // Attributs
        currentPage = 1;
        refreshing = false;
        // Panels
        messagesPanel = new WebPanel(new FlowLayout());
        statusPanel = new WebPanel(new FlowLayout());
        // CheckBox
        checkBox = new WebCheckBox("Seulement non lus");
        checkBox.setSelected(false);
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addStatusComponents(checkBox.isSelected());
                addMessagesComponents(checkBox.isSelected(), 1);
            }
        });
    }
////////////////////////////////////////////////////////////////////////////////

    public BoiteReception(int id) {
        this();
        userID = id;
        // Center Panel
        addMessagesComponents(false, 1);
        // South Panel
        addStatusComponents(false);
        // Adding Panels
        this.setLayout(new BorderLayout());
        this.add(messagesPanel, BorderLayout.CENTER);
        this.add(statusPanel, BorderLayout.SOUTH);
        this.setPreferredWidth(500);
    }
////////////////////////////////////////////////////////////////////////////////
}
