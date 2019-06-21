/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educacionit.delivery.mail;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import org.apache.log4j.Logger;
/**
 *
 * @author u585720
 */
public class MailSender {
    
private static String from = "asdfakeasdfake@gmail.com";
private static String password = "Pirulin01";
private static final Logger logger = Logger.getLogger (MailSender.class);

public void send(String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   

          Session session;    
    session = Session.getDefaultInstance(props,    
new javax.mail.Authenticator() {    
@Override
protected PasswordAuthentication getPasswordAuthentication() {    
return new PasswordAuthentication(from,password);  
}    
});
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           logger.info (String.format ("Sending confirmation message to %s",to));
           Transport.send(message);    
           logger.debug (String.format ("message sent successfully"));
           
          } catch (MessagingException e) {
              logger.error("Error " + e.getMessage());
              throw new RuntimeException(e);
          }    
             
    }  
}  
//public class SendMailSSL{    
 //public static void main(String[] args) {    
     //from,password,to,subject,message  
     //Mailer.send("from@gmail.com","xxxxx","to@gmail.com","hello javatpoint","How r u?");  
     //change from, password and to  
 //}    
   
//}
