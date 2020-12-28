package com.rap.berichten.responseberichten;


public class Bericht {

    private final String respSrt;
    private final long IdBer;

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
}
