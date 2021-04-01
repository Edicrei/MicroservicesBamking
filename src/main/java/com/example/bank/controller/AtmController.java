package com.bankservice.bank.controller;

import com.bankservice.bank.model.BankAccount;
import com.bankservice.bank.service.BankAccountService;
import com.bankservice.bank.utils.Utils;
import com.bankservice.bank.validator.BankValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AtmController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankValidators bankValidators;

    @GetMapping(value = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBankAccounts(
            @RequestParam(value = "cardNumber") String cardNumber,
            @RequestParam(value = "pin") String pin) {
        try {
            if (bankValidators.validatePin(cardNumber, pin)) {
                List <BankAccount> result = bankAccountService.getBankAccountsFromCardNumber(cardNumber);

                if (!result.isEmpty())
                    return new ResponseEntity(Utils.toBankAccountDAO(result), HttpStatus.OK);
            }
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "activity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getActivity(
            @RequestParam(value = "cardNumber") String cardNumber,
            @RequestParam(value = "pin") String pin) {

        if (bankValidators.validatePin(cardNumber, pin)) {
            return new ResponseEntity(Utils.toActivityDAO(bankAccountService.getActivity(cardNumber)), HttpStatus.OK);
        }

        return new ResponseEntity("", HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "getCash", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCash(
            @RequestParam(value = "cardNumber") String cardNumber,
            @RequestParam(value = "pin") String pin,
            @RequestParam(value = "quantity") Double quantity) {
        HashMap <String, String> response = new HashMap <>();
        if (bankValidators.validatePin(cardNumber, pin) && bankAccountService.getCash(cardNumber,quantity)) {
            response.put("message", String.format("Succesfully operation, get %s €", quantity));
            return new ResponseEntity(response, HttpStatus.OK);
        }
        response.put("message", "error while processing operation");

        return new ResponseEntity("", HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "putCash", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity putCash(
            @RequestParam(value = "cardNumber") String cardNumber,
            @RequestParam(value = "pin") String pin,
            @RequestParam(value = "quantity") Double quantity,
            @RequestParam(value = "atmId") Long atmId) {
        HashMap <String, String> response = new HashMap <>();
        if (bankValidators.validatePin(cardNumber, pin) && bankAccountService.putCash(cardNumber,quantity, atmId)) {
            response.put("message", String.format("Succesfully operation, put %s €", quantity));
            return new ResponseEntity(response, HttpStatus.OK);
        }
        response.put("message", "error while processing operation");

        return new ResponseEntity("", HttpStatus.NOT_FOUND);

    }

}
