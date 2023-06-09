package br.com.barberShop.component;

import br.com.barberShop.constants.ConstantesAutentication;
import br.com.barberShop.constants.ErrorCodes;
import br.com.barberShop.dto.authenticate.ValidateJwtDTO;
import br.com.barberShop.dto.customer.CustomerDetails;
import br.com.barberShop.exceptions.ExpiredJWTException;
import br.com.barberShop.exceptions.UnableJWTException;
import br.com.barberShop.service.authenticate.CreateAuthenticationSecurityService;
import br.com.barberShop.service.authenticate.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilterComponent extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtilComponent jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private CreateAuthenticationSecurityService authenticationSecurityService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader(ConstantesAutentication.AUTHORIZATION.getMessage());
        var validateJWT = this.CheckJWT(requestTokenHeader, request);
        this.Authentication(validateJWT, request);
        filterChain.doFilter(request, response);
    }

    private void Authentication(ValidateJwtDTO validateJWT, HttpServletRequest request) {
        if (validateJWT.getLogin() != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            CustomerDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(validateJWT.getLogin());

            if (jwtTokenUtil.validateToken(validateJWT.getJWT(), userDetails)) {
                authenticationSecurityService.createAuthentication(userDetails, request);
            }
        }
    }

    private ValidateJwtDTO CheckJWT(String requestTokenHeader, HttpServletRequest request) {
        String login = null;
        String jwtToken = null;

        if(StringUtils.isNotEmpty(requestTokenHeader) && requestTokenHeader.startsWith(ConstantesAutentication.BEARER.getMessage())){
            jwtToken = requestTokenHeader.substring(7);
            try {
                login = jwtTokenUtil.getLoginFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new UnableJWTException(ErrorCodes.UNABLE_TO_GET_JWT.getMessage());
            } catch (ExpiredJwtException e) {
                throw new ExpiredJWTException(ErrorCodes.JWT_EXPIRED.getMessage());
            }
        } else {
            logger.warn(ErrorCodes.JWT_TOKEN_NOT_BEARER_STRING.getMessage());
        }

        return ValidateJwtDTO.builder()
                .login(login)
                .JWT(jwtToken)
                .build();
    }
}
