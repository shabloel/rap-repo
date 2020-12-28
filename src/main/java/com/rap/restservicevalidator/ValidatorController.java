package com.rap.restservicevalidator;

import com.rap.berichten.responseberichten.AdministratieveEenheid;
import com.rap.berichten.responseberichten.Bericht;
import com.rap.berichten.responseberichten.PensioenAangifteResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/api")
public class ValidatorController {

    private static final String statement = "Bericht arrived";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping(value="/upa", produces = MediaType.APPLICATION_XML_VALUE)
    public PensioenAangifteResponse messageXML(){
        return xmlMessage();
    }

    private PensioenAangifteResponse xmlMessage(){
        Bericht bericht = new Bericht("VALID", counter.getAndIncrement());
        AdministratieveEenheid administratieveEenheid = new AdministratieveEenheid("012345678L01", LocalDateTime.of(2021,03,15,10,52,54), ZonedDateTime.now());
        PensioenAangifteResponse pensioenAangifteResponse = new PensioenAangifteResponse(bericht, administratieveEenheid);

        return pensioenAangifteResponse;
    }
}
