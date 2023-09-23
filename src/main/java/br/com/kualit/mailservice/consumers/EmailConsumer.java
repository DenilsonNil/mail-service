package br.com.kualit.mailservice.consumers;

import br.com.kualit.mailservice.dtos.EmailDTO;
import br.com.kualit.mailservice.models.EmailModel;
import br.com.kualit.mailservice.services.IEmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static br.com.kualit.mailservice.enums.SMTP.GMAIL;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class EmailConsumer {

    @Autowired
    private IEmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        var emailModel = new EmailModel();
        copyProperties(emailDTO, emailModel);

        emailService.sendEmail(emailModel, GMAIL);
    }
}
