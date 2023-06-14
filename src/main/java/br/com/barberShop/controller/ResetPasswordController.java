package br.com.barberShop.controller;

import br.com.barberShop.dto.password.EmailRequestDTO;
import br.com.barberShop.dto.password.ResetPasswordRequestDTO;
import br.com.barberShop.dto.password.ResetPasswordResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResetPasswordController {

    @Autowired
    private AppService appService;

    @PostMapping(value = "/v1/Customer/resetPassword")
    public ResponseEntity<String> sendEmail(HttpServletRequest httpServletRequest, @RequestBody EmailRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(appService.sendEmail(httpServletRequest, request));
    }

    @GetMapping("/v1/Customer/changePassword")
    public ModelAndView showChangePasswordPage(@RequestParam("token") String token) {
        return appService.showChangePasswordPage(token);
    }

    @PostMapping(value = "/v1/Customer/updatePassword")
    public ResponseEntity<ResetPasswordResponseDTO> updatePassword(@RequestBody ResetPasswordRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(appService.updatePassword(request));
    }
}
