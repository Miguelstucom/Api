package service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	
	public EmailService(JavaMailSender mailSender) {this.mailSender = mailSender;}
	
	public void sendEmail(String to, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("miguel.stucom@gmail.com");
		message.setTo("miguel.stucom@gmail.com");
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
	}
	
	public void reservationEmail(String to, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("miguel.stucom@gmail.com");
		message.setTo("foodieguardcontact@gmail.com");
		message.setSubject(subject);
		message.setText(body);
		
		mailSender.send(message);
	}
	
	

}
