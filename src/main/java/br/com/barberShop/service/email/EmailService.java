package br.com.barberShop.service.email;

import br.com.barberShop.config.ConsulConfig;
import br.com.barberShop.dto.EmailRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConsulConfig config;

    public String sendEmail(EmailRequestDTO request) {
        var response = "";
        log.info("EmailService.SendEmail - Start - EmailRequestDTO: [{}]", request);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setFrom(config.getEmailFrom());
        message.setSubject(config.getTitle());
        message.setText(config.getText());
        mailSender.send(message);

        try {
            response = "Email enviado com sucesso!";
        } catch (Exception e) {
            response = "Erro ao enviar email.";
        }

        log.info("EmailService.SendEmail - Start - EmailRequestDTO: [{}], Response : [{}]", request, response);
        return response;
    }
}
