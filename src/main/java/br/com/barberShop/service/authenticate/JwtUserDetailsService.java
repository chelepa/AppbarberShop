package br.com.barberShop.service.authenticate;

import br.com.barberShop.constants.ErrorCodes;
import br.com.barberShop.dto.customer.CustomerDetails;
import br.com.barberShop.exceptions.CustomerException;
import br.com.barberShop.exceptions.LoginNullException;
import br.com.barberShop.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AppService service;

    @Override
    public CustomerDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (StringUtils.isBlank(email)){
            throw new LoginNullException(ErrorCodes.LOGIN_NULL.getMessage());
        }
        CustomerDetails user = service.customerFindByLogin(email);
        if (user == null) {
            throw new CustomerException(ErrorCodes.LOGIN_OR_PASSWORD_IS_INVALID.getMessage());
        }
        return user;
    }
}
