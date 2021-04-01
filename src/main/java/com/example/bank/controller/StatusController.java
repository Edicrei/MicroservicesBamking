package com.bankservice.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class StatusController {

    /**
     * Check if the service is available
     *
     * @return
     */
    @GetMapping("/status")
    public ResponseEntity status() {
        Map <String, String> response = new HashMap <>();
        response.put("message", "service ready");
        return new ResponseEntity <>(response, HttpStatus.OK);
    }
}