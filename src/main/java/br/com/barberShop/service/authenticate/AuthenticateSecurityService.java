package br.com.barberShop.service.authenticate;

import br.com.barberShop.component.JwtTokenUtilComponent;
import br.com.barberShop.constants.ErrorCodes;
import br.com.barberShop.dto.authenticate.AuthenticateRequestDTO;
import br.com.barberShop.dto.authenticate.AuthenticateResponseDTO;
import br.com.barberShop.dto.customer.CustomerDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthenticateSecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtilComponent jwtTokenUtilComponent;

    @Autowired
    private CreateAuthenticationSecurityService authenticationSecurityService;

    public AuthenticateResponseDTO authenticate(HttpServletRequest request, AuthenticateRequestDTO authenticate) {
        try {
            Authentication authenticationResponse = new UsernamePasswordAuthenticationToken(authenticate.getLogin(), authenticate.getPassword());
            var authentication = authenticationManager.authenticate(authenticationResponse);
            var customerDetails = (CustomerDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtilComponent.generateToken(customerDetails);
            authenticationSecurityService.createAuthentication(customerDetails, request);
            return responseAuthenticate(Boolean.TRUE, jwt);
        } catch (DisabledException e) {
            log.info("authenticate.DisabledException - Error: [{}]", e.getMessage());
            return responseAuthenticate(Boolean.FALSE, e.getMessage());
        } catch (AuthenticationException ex) {
            log.info("authenticate.AuthenticationException - Error: [{}]", ex.getMessage());
            return responseAuthenticate(Boolean.FALSE, ErrorCodes.LOGIN_OR_PASSWORD_IS_INVALID.getMessage());
        }
    }

    private AuthenticateResponseDTO responseAuthenticate(Boolean authenticate, String response) {
        return new AuthenticateResponseDTO(authenticate, response);
    }
}
