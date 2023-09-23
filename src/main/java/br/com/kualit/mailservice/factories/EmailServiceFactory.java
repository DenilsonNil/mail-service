package br.com.kualit.mailservice.factories;

import br.com.kualit.mailservice.enums.SMTP;
import br.com.kualit.mailservice.services.GmailService;
import br.com.kualit.mailservice.services.IEmailSender;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceFactory {

    public static IEmailSender newEmailService(SMTP service, JavaMailSender sender) {
        return new GmailService(sender);
    }
}
