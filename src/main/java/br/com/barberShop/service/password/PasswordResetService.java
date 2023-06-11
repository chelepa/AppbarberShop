package br.com.barberShop.service.password;

import br.com.barberShop.constants.DateUtilsConstants;
import br.com.barberShop.entity.PasswordResetEntity;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
public class PasswordResetService extends BaseService {

    public ModelAndView showChangePasswordPage(String token) {
        var responseToken = this.getTokenPasswordReset(token);
        var response = responseToken.map(this::checkAspiration).orElse("/tokenNotValid.html");
        return new ModelAndView(response);
    }

    private String checkAspiration(PasswordResetEntity passwordResetEntity) {
        LocalDateTime currentDate = LocalDateTime.now(ZoneId.of(DateUtilsConstants.TIME_ZONE));
        var response = currentDate.compareTo(passwordResetEntity.getExpiryDate());
        return (response <= 0) ? "/updatePassword.html" : "/tokenExpired.html";
    }
}
