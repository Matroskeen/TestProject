package com.matroskeen.helpful;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private static String SMTP_HOST = "smtp.gmail.com";  
    private static String FROM_ADDRESS = "LutskCyberSport@gmail.com";  
    private static String PASSWORD = "fksufnjh28";  
    private static String FROM_NAME = "LutskCyberSport.com";  
	
	public boolean sendRegistrationToken(String userEmail, String link) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.ssl.enable", "true");
        
        Session session = Session.getInstance(props, new SocialAuth()); 

        String msgBody = "Вітаємо на нашому ресурсі! "
        		+ "Для підтвердження реєстрації перейдіть за посиланням: "
        		+ link;
        
        try {
            Message msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress(FROM_ADDRESS, FROM_NAME));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail, "User"));
            
            msg.setSubject("Підтвердження реєстрації на " + FROM_NAME);
            msg.setText(msgBody);
            
            Transport.send(msg);

        } catch (MessagingException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return false;
        } 
        return true;
	}
	
	class SocialAuth extends Authenticator {  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);  
  
        }  
    } 

}
