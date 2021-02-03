package com.rap.restservicevalidator.serviceimpl;

import com.rap.restservicevalidator.model.Bericht;
import com.rap.restservicevalidator.model.AdministratieveEenheid;
import com.rap.restservicevalidator.model.PensioenAangifteResponse;
import com.rap.restservicevalidator.service.ResponseCreatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author chris.griffioen
 */
@Service
public class ResponseCreatorImplPensioenAangifte implements ResponseCreatorService<PensioenAangifteResponse> {

    @Override
    public PensioenAangifteResponse createXMLResponse(String respStr) {
        AtomicLong idBer = new AtomicLong();
        Bericht bericht = new Bericht(respStr, idBer.getAndIncrement());
        AdministratieveEenheid administratieveEenheid = new AdministratieveEenheid("012345678L01", LocalDateTime.of(2021, 03, 15, 10, 52, 54), ZonedDateTime.now());
        PensioenAangifteResponse pensioenAangifteResponse = new PensioenAangifteResponse(bericht, administratieveEenheid);
        return pensioenAangifteResponse;
    }
}
