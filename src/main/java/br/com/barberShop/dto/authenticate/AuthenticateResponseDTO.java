package br.com.barberShop.dto.authenticate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResponseDTO {

    private Boolean auth;
    private String token;
}
