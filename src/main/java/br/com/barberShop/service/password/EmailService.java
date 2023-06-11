package br.com.barberShop.service.password;

import br.com.barberShop.config.ConsulConfig;
import br.com.barberShop.constants.ErrorCodes;
import br.com.barberShop.dto.EmailRequestDTO;
import br.com.barberShop.exceptions.LoginNullException;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Service
public class EmailService extends BaseService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConsulConfig config;

    public String sendEmail(HttpServletRequest httpServletRequest, EmailRequestDTO request) {
        log.info("EmailService.SendEmail - Start - EmailRequestDTO: [{}]", request);
        var user = this.searchCustomerByEmail(request.getEmail());
        if (user == null) {
            throw new LoginNullException(ErrorCodes.LOGIN_NULL.getMessage());
        }
        String token = UUID.randomUUID().toString();
        String URL = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        var response = this.createPasswordResetTokenForUser(user, token);
        String url_token = URL + "/v1/Customer/changePassword?token=" + token;

        mailSender.send(constructEmail(config.getTitle(), url_token, request.getEmail()));
        log.info("EmailService.SendEmail - Start - EmailRequestDTO: [{}], Response : [{}]", request, response);
        return "E-mail Enviado";
    }

    private SimpleMailMessage constructEmail(String subject, String body, String emailTo) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo("cl.chelepa@gmail.com");
        email.setFrom(config.getEmailFrom());
        return email;
    }
}
