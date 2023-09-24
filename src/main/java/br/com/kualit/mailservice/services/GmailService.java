package br.com.kualit.mailservice.services;

import br.com.kualit.mailservice.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static br.com.kualit.mailservice.enums.StatusEmail.ERROR;
import static br.com.kualit.mailservice.enums.StatusEmail.SENT;
import static java.time.LocalDateTime.now;

@Component
public class GmailService implements IEmailSender {
    @Autowired
    private JavaMailSender sender;

    @Override
    public EmailModel sendEmail(EmailModel emailModel) {
        var message = new SimpleMailMessage();

        emailModel.setSendDateEmail(now());

        try {
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            sender.send(message);
            emailModel.setStatusEmail(SENT);

        } catch (MailException e) {
            emailModel.setStatusEmail(ERROR);
        }

        return emailModel;
    }
}
