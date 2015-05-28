package Technique;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Elyes
 *
 */
public class SendMail implements Runnable{
    private String dest;
    private String body;
////////////////////////////////////////////////////////////////////////////////

    private SendMail() {
    }
////////////////////////////////////////////////////////////////////////////////

    public SendMail(String dest, String body) {
        this.dest = dest;
        this.body = body;
    }
////////////////////////////////////////////////////////////////////////////////

    private void generateAndSendEmail() throws MessagingException {

        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
//Step1		
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

//Step2		
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
        generateMailMessage.setSubject("Nouveau message");
        String emailBody = body + "<br><br> cordialement, <br>Admin";
        generateMailMessage.setContent(emailBody, "text/html");

//Step3		
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "stackoverflow.agenceimmob", "esprit3a19");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    @Override
    public void run() {
        try {
            generateAndSendEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
