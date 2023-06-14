package br.com.barberShop.dto.password;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequestDTO {

    private String newPassword;
    private String confirmPassword;
    private String token;
}
