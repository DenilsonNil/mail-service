package br.com.kualit.mailservice.services;

import br.com.kualit.mailservice.enums.SMTP;
import br.com.kualit.mailservice.factories.EmailServiceFactory;
import br.com.kualit.mailservice.models.EmailModel;
import br.com.kualit.mailservice.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements IEmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EmailServiceFactory factory;

    public EmailModel sendEmail(EmailModel emailModel, SMTP smtp) {
        var sender =  factory.newEmailService(smtp);
        sender.sendEmail(emailModel);
        return emailRepository.save(emailModel);
    }
}
