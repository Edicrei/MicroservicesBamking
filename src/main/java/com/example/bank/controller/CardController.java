package com.bankservice.bank.controller;

import com.bankservice.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(value = "activate_card", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBankAccounts(@RequestParam(value = "cardNumber") String cardNumber){
        HashMap <String, String> response = new HashMap <>();
        if(cardService.activateCard(cardNumber)){
            response.put("message", "card activated");
            return new ResponseEntity(response, HttpStatus.OK);
        }
         response.put("message", "while processing your request");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
