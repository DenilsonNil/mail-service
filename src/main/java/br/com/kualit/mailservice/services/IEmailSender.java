package br.com.kualit.mailservice.services;

import br.com.kualit.mailservice.models.EmailModel;

public interface IEmailSender {
    EmailModel sendEmail(EmailModel emailModel);
}
