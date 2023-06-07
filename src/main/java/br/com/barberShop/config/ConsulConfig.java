package br.com.barberShop.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConsulConfig {

    private final String emailFrom;
    private final String title;
    private final String text;

    public ConsulConfig(@Value("${e-mail-sender.emailFrom}") String emailFrom,
                        @Value("${e-mail-sender.title}") String title,
                        @Value("${e-mail-sender.text}") String text){

        this.emailFrom = emailFrom;
        this.title = title;
        this.text = text;
    }
}
