package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.EmailService;

@RestController
public class EmailController {
	
	private final EmailService emailService;
	
	public EmailController(EmailService emailService) {this.emailService = emailService;}
	
    @RequestMapping("/email")
    public String sendEmail(@RequestParam("recipient") String recipient,
                            @RequestParam("subject") String subject) {

        emailService.sendEmail("miguel.stucom@gmail.com",recipient, subject);
        return "Email test sent";
    }

}
