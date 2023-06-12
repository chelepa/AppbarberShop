package br.com.barberShop.service.password;

import br.com.barberShop.constants.DateUtilsConstants;
import br.com.barberShop.dto.password.ResetPasswordRequestDTO;
import br.com.barberShop.dto.password.ResetPasswordResponseDTO;
import br.com.barberShop.entity.PasswordResetEntity;
import br.com.barberShop.entity.UsersEntity;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
public class PasswordResetService extends BaseService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ModelAndView showChangePasswordPage(String token) {
        var responseToken = this.getTokenPasswordReset(token);
        var response = responseToken.map(this::checkExpiration).orElse("/tokenNotValid.html");
        return new ModelAndView(response);
    }

    private String checkExpiration(PasswordResetEntity passwordResetEntity) {
        var response = this.checkExpiryDate(passwordResetEntity.getExpiryDate());
        return (response) ? "/updatePassword.html" : "/tokenExpired.html";
    }

    public ResetPasswordResponseDTO updatePassword(ResetPasswordRequestDTO request) {
        ResetPasswordResponseDTO response = new ResetPasswordResponseDTO();
        var responseToken = this.getTokenPasswordReset(request.getToken());
        if(responseToken.isPresent()){
            var checkExpiryDate = this.checkExpiryDate(responseToken.get().getExpiryDate());
            var usersEntity = (checkExpiryDate) ? this.searchCustomerById(responseToken.get().getId_users().getId()) : null;
            response = (usersEntity != null) ? passwordUpdate(usersEntity, request, responseToken.get()) : new ResetPasswordResponseDTO("Erro Ao Atualizar as Credenciais");
        } else{
            response = new ResetPasswordResponseDTO("Token Invalido ou expirado");
        }
        return response;
    }

    private ResetPasswordResponseDTO passwordUpdate(UsersEntity usersEntity, ResetPasswordRequestDTO request, PasswordResetEntity passwordResetEntity) {
        ResetPasswordResponseDTO response = new ResetPasswordResponseDTO("Erro Ao Atualizar as Credenciais");
        if (StringUtils.equalsIgnoreCase(request.getNewPassword(), request.getConfirmPassword())){
            usersEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
            this.createCustomer(usersEntity);
            response.setMsg("Credenciais Altetradas Com Sucesso");
            passwordResetEntity.setExpiryDate(LocalDateTime.now(ZoneId.of(DateUtilsConstants.TIME_ZONE)));
            this.createPasswordReset(passwordResetEntity);
        }
        return response;
    }

    private Boolean checkExpiryDate(LocalDateTime expiryDate) {
        LocalDateTime currentDate = LocalDateTime.now(ZoneId.of(DateUtilsConstants.TIME_ZONE));
        var response = currentDate.compareTo(expiryDate);
        return (response <= 0) ? Boolean.TRUE : Boolean.FALSE;
    }
}
