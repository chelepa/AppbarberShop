package br.com.barberShop.controller;

import br.com.barberShop.dto.authenticate.AuthenticateRequestDTO;
import br.com.barberShop.dto.authenticate.AuthenticateResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Autowired
    private AppService appService;

    @PostMapping(value = "/v1/Authentication")
    public ResponseEntity<AuthenticateResponseDTO> createAuthenticationToken(HttpServletRequest request, @RequestBody AuthenticateRequestDTO authenticate) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.authenticate(request, authenticate));
    }
}
