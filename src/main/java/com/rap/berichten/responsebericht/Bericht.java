package com.rap.berichten.responsebericht;


public class Bericht {

    private String respStr;
    private long IdBer;

    public Bericht(String respStr, long IdBer) {
        this.respStr = respStr;
        this.IdBer = IdBer;
    }

    public String getRespStr() {
        return respStr;
    }

    public long getIdBer() {
        return IdBer;
    }

    public void setRespStr(String respStr) {
        this.respStr = respStr;
    }

    public void setIdBer(long idBer) {
        IdBer = idBer;
    }
}
