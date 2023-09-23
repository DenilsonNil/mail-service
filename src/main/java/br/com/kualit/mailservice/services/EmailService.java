package br.com.kualit.mailservice.services;

import br.com.kualit.mailservice.enums.SMTP;
import br.com.kualit.mailservice.models.EmailModel;
import br.com.kualit.mailservice.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static br.com.kualit.mailservice.factories.EmailServiceFactory.newEmailService;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public EmailModel sendEmail(EmailModel emailModel, SMTP smtp) {
        var sender =  newEmailService(smtp, javaMailSender);
        sender.sendEmail(emailModel);
        return emailRepository.save(emailModel);
    }
}
