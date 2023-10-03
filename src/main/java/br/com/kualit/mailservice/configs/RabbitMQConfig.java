package br.com.kualit.mailservice.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    //First Exchange and first queue
    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchange);
    }
    @Value("${spring.rabbitmq.queue}")
    private String queue;

    //Using a map to put some arguments before create the queue
    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "mail-message-dlq");
        //args.put("x-dead-letter-routing-key", "dead-letter-queue");

        return new Queue(queue, true, false, false, args);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    //Exchange and queue DLQ
    @Bean FanoutExchange fanoutExchangeDLQ() {
        return new FanoutExchange("mail-message-dlq");
    }
    @Bean
    public Queue queueDLQ() {
        return new Queue("dead-letter-queue", true);
    }

    @Bean
    public Binding bindingDLQ() {
        return BindingBuilder.bind(queueDLQ()).to(fanoutExchangeDLQ());
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connection){
        return new RabbitAdmin(connection);
    }

    @Bean
    public  Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
