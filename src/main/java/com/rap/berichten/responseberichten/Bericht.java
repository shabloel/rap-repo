package com.rap.berichten.responseberichten;


public class Bericht {

    private String respSrt;
    private long IdBer;

    public Bericht(String respStr, long IdBer) {
        this.respSrt = respStr;
        this.IdBer = IdBer;
    }

    public String getRespSrt() {
        return respSrt;
    }

    public long getIdBer() {
        return IdBer;
    }

    public void setRespSrt(String respSrt) {
        this.respSrt = respSrt;
    }

    public void setIdBer(long idBer) {
        IdBer = idBer;
    }
}
