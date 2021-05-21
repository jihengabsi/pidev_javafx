/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import static validation1.Constants.projectPath;

/**
 * FXML Controller class
 *
 * @author monia
 */
public class AddDemandeController implements Initializable {

    @FXML
    private JFXTextField commentTF;
    @FXML
    private JFXTextField nomTF;
    @FXML
    private JFXTextField prenomTF;
    @FXML
    private JFXTextField numTF;
int ids=0;
    @FXML
    private JFXTextField cvTF;
    
    File file = null;
    
    public static final String ACCOUNT_SID = "AC5f6fca0dd549706599c1c7618fc62997";
    public static final String AUTH_TOKEN = "3c30c3d97b30dcc0f66b53750147f317";
    
    FileChooser fileChooser = new FileChooser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void getID(int id){
        ids=id;
    }

    @FXML
    private void add(ActionEvent event) throws MessagingException {
        System.out.println(ids);
         ServiceDemande sd = new ServiceDemande();
        Demande d = new  Demande();
        d.setIDO(ids);
        d.setNom(nomTF.getText());
        d.setComment(commentTF.getText());
        d.setPrenom(prenomTF.getText());
        d.setNum(Integer.valueOf(numTF.getText()));
        
        d.setCV(cvTF.getText());
        
        File dest = new File(projectPath + "\\src\\validation1.CV\\" + file.getName());

                        try {
                                FileUtils.copyFile(file, dest);
                        } catch (IOException ex) {
                                Logger.getLogger(AddDemandeController.class.getName()).log(Level.SEVERE, null, ex);
                        }

        
           sd.AjouterDemande(d);
           String mail="marwen.khazri@esprit.tn";
           String text="hello";
           String sub="GG";
           sendMail(mail,text,sub);
           envoyerSMS();
    }

    @FXML
    private void AddFile(MouseEvent event) {
        
        Stage stage2 = null;

                file = fileChooser.showOpenDialog(stage2);

                cvTF.setText(file.getName());
        
    }
    
    
    private static Message prepareMessage(Session session, String mail, String destinataire,String txt,String sub) {
        Message msg = new MimeMessage(session);
        try {

            msg.setFrom(new InternetAddress(mail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
            msg.setSubject(sub);
            msg.setText(txt);

        } catch (Exception ex) {
            Logger.getLogger(AddDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public static void sendMail(String destinataire,String txt,String sub) throws MessagingException{
        System.out.println("MAIL CHARGING ***");
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        String mail ="employini@gmail.com";
        String pwd="12345ESPRIT";
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, pwd);
            }
        });
        Message msg = prepareMessage(session,mail,destinataire,txt,sub);
        System.out.println(msg);
        Transport.send(msg);
        System.out.println("MAIL ENVOYEE");
    }
    
    public void envoyerSMS() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator( //preparation d'un nouveau msg
                new com.twilio.type.PhoneNumber("+216" + "53589990"), // num reception
                new com.twilio.type.PhoneNumber("+12193352686"), // num d'envoie
                "new demande has been made on offre") // le msg
                .create(); //creation
        System.out.println("sms envoyé");
        System.out.println(message.getSid());
    }
    
}
