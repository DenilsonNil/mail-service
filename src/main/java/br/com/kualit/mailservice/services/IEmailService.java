package br.com.kualit.mailservice.services;

import br.com.kualit.mailservice.enums.SMTP;
import br.com.kualit.mailservice.models.EmailModel;

public interface IEmailService {
    EmailModel sendEmail(EmailModel emailModel, SMTP emailService);
}
