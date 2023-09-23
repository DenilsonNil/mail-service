package br.com.kualit.mailservice.controllers;

import br.com.kualit.mailservice.dtos.EmailDTO;
import br.com.kualit.mailservice.models.EmailModel;
import br.com.kualit.mailservice.services.IEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.kualit.mailservice.enums.SMTP.GMAIL;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/notification")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDTO dto) {
        var emailModel = new EmailModel();
        copyProperties(dto, emailModel);

        emailService.sendEmail(emailModel, GMAIL);

        return new ResponseEntity<>(emailModel, CREATED);
    }
}
