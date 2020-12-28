package com.rap.berichten.responseberichten;

public class PensioenAangifteResponse {

    Bericht bericht;
    AdministratieveEenheid administratieveEenheid;

    public PensioenAangifteResponse(Bericht bericht, AdministratieveEenheid administratieveEenheid) {
        this.bericht = bericht;
        this.administratieveEenheid = administratieveEenheid;
    }

    public void setBericht(Bericht bericht) {
        this.bericht = bericht;
    }

    public void setAdministratieveEenheid(AdministratieveEenheid administratieveEenheid) {
        this.administratieveEenheid = administratieveEenheid;
    }

    public Bericht getBericht() {
        return bericht;
    }

    public AdministratieveEenheid getAdministratieveEenheid() {
        return administratieveEenheid;
    }
}
