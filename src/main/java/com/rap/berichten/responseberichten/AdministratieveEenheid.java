package com.rap.berichten.responseberichten;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class AdministratieveEenheid {

    private String lhNr;
    private LocalDateTime DatTdAanmAEH;
    private ZonedDateTime DatTdAanmResp;

    public AdministratieveEenheid(String lhNr, LocalDateTime DatTdAanmAEH, ZonedDateTime DatTdAanmResp){
        this.lhNr = lhNr;
        this.DatTdAanmAEH = DatTdAanmAEH;
        this.DatTdAanmResp = DatTdAanmResp;
    }

    public String getLhNr() {
        return lhNr;
    }

    public void setLhNr(String lhNr) {
        this.lhNr = lhNr;
    }

    public LocalDateTime getDatTdAanmAEH() {
        return DatTdAanmAEH;
    }

    public void setDatTdAanmAEH(LocalDateTime datTdAanmAEH) {
        DatTdAanmAEH = datTdAanmAEH;
    }

    public ZonedDateTime getDatTdAanmResp() {
        return DatTdAanmResp;
    }

    public void setDatTdAanmResp(ZonedDateTime datTdAanmResp) {
        DatTdAanmResp = datTdAanmResp;
    }
}
