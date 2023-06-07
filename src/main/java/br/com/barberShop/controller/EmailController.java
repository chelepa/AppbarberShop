package br.com.barberShop.controller;

import br.com.barberShop.dto.EmailRequestDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private AppService appService;

    @PostMapping(value = "/send/email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(appService.sendEmail(request));
    }

}
