package br.com.barberShop.dto.authenticate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateJwtDTO {
    private String login;
    private String JWT;
}
