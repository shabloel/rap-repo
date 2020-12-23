package com.rap.restservicevalidator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ValidatorController {

    private static final String template = "OK";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/upa")
    public void okayNotOkay(){ }


}
