package br.com.kualit.mailservice.factories;

import br.com.kualit.mailservice.enums.SMTP;
import br.com.kualit.mailservice.services.GmailService;
import br.com.kualit.mailservice.services.IEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFactory {
    @Autowired
    private GmailService gmailService;

    public IEmailSender newEmailService(SMTP service) {
        return gmailService;
    }
}
